package com.example.app2;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView OutputTxt;
    EditText InputTxt;
    Button BtnToCreate;
    Button BtnCreate;
    Button BtnGetAll;
    Button BtnGetById;
    Button BtnToMain;
    RecyclerView GetAllRecyclerView;
    EditText nameInput;
    EditText descripInput;


    private static final String TAG = "MainActivity";
    private Connection connection = DatabaseConnection.getConnection();
    // Connection



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InputTxt = (EditText) findViewById(R.id.TxtInput);

        OutputTxt = (TextView) findViewById(R.id.TxtOutput);
        BtnToCreate = (Button) findViewById(R.id.BtnToCreate);
        BtnGetAll = (Button) findViewById(R.id.BtnGetAll);
        BtnGetById = (Button) findViewById(R.id.BtnGetById);
        GetAllRecyclerView = (RecyclerView) findViewById(R.id.getAllRecyclerView);
        GetAllRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //nameInput = (EditText)findViewById(R.id.nameInput); //Hører til på activity_create
        //descripInput = (EditText)findViewById(R.id.descripInput); //Hører til på activity_create
        //BtnCreate = (Button) findViewById(R.id.BtnCreate); //Hører til på activity_create
        //BtnToMain = (Button)findViewById(R.id.BtnToMain); //Hører til på activity_create



        BtnToCreate.setOnClickListener(new View.OnClickListener() { //Bruges til at navigere fra main til create-vinduet
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_create);
            }
        });

        BtnToMain.setOnClickListener(new View.OnClickListener() { //Bruges til at navigere fra create-vinduet til main
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_main);
            }
        });
        BtnCreate.setOnClickListener(new View.OnClickListener() { //Skal bruges til at oprette, og derefter sende brugeren tilbage til main
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_create);
            }
        });

        // Create an instance of MyDatabaseHelper and get the spinner options from the database
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(this);
        ArrayList<String> options = dbHelper.getSpinnerOptions();

        new ConnectToDatabaseTask().execute();
    }

    private class ConnectToDatabaseTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... voids) {
            // Attempt to establish the database connection
            connection = DatabaseConnection.getConnection();
            return connection != null;
        }


        @Override
        protected void onPostExecute(Boolean success) {
            if (success) {
                // Connection successful, perform database operations here
                Log.d(TAG, "Connection successful");

                // CREATE
                try {
                    String sql = "INSERT INTO Persons (PersonName, Descrip) VALUES (?, ?)";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, nameInput.getText().toString());
                    statement.setString(2, descripInput.getText().toString());
                    int rowsInserted = statement.executeUpdate();
                    if (rowsInserted > 0) {
                        Log.d(TAG, "En ny person er oprettet!");
                    } else {
                        Log.d(TAG, "Ingen person oprettet!");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                // READ - GET ALL
                try {
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM Persons");
                    while (resultSet.next()) {
                        // Do something with each row
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                // READ - GET BY ID


                try {
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM Persons where PersonID = 1");

                    if (resultSet.next()) {
                        String value = resultSet.getString("Navn");
                        Log.d(TAG, "Navn: " + value);
                        OutputTxt.setText(value);
                    }
                    //while (resultSet.next())
                    //{
                    //  OutputTxt.setText(resultSet.getString(columnIndex ));
                    //}
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                // UPDATE
                try {
                    String sql = "UPDATE Persons SET column1 = ? WHERE column2 = ?";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, "newvalue1");
                    statement.setString(2, "value2");
                    int rowsUpdated = statement.executeUpdate();
                    if (rowsUpdated > 0) {
                        Log.d(TAG, "Personen er opdateret.");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                // DELETE
                try {
                    String sql = "DELETE FROM Persons WHERE PersonID = ?";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, "value1");
                    int rowsDeleted = statement.executeUpdate();
                    if (rowsDeleted > 0) {
                        Log.d(TAG, "Personen er slettet.");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                // Til at lukke, når vi er færdige
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else {
                Log.d(TAG, "Connection failed. Fy for en skefuld!");
            }
        }
    }
}