package com.example.app2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class MainActivity extends AppCompatActivity {

    private ListView myListView;
    private PersonListAdapter personListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myListView = findViewById(R.id.myListView);
        personListAdapter = new PersonListAdapter(this, new ArrayList<>());
        myListView.setAdapter(personListAdapter);
        fetchDataGetID();

    }

    private void fetchDataGetAll() {
        // Create a new instance of your PersonService
        PersonService personService = ServiceBuilder.buildService(PersonService.class);

        // Make a request to fetch data from your Tomcat server
        Call<List<Person>> request = personService.getAllPersoner();
        request.enqueue(new Callback<List<Person>>() {
            @Override
            public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {
                // Update your adapter with the data received from the server
                personListAdapter.updateDataGetAll(response.body());
            }

            @Override
            public void onFailure(Call<List<Person>> call, Throwable t) {
                // Handle errors here
            }
        });
    }

    private void fetchDataGetID() {
        // Create a new instance of your PersonService
        PersonService personService = ServiceBuilder.buildService(PersonService.class);

        // Make a request to fetch data from your Tomcat server
        Call<Person> request = personService.getPersonById(1);
        request.enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                // Update your adapter with the data received from the server
                personListAdapter.updateDataGetID(response.body());
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {

            }

        });
    }
}