package com.example.app2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView; //Lidt gået væk fra denne løsning

import android.content.Context;


import java.util.List;

public class PersonListAdapter extends BaseAdapter {
    private Context context;
    private List<Person> data;

    public PersonListAdapter(Context context, List<Person> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    //Get All
    public void updateDataGetAll(List<Person> newData) {
        if (newData == null) {
            return;
        }
        // Update the data and notify the adapter that the data has changed
        data.clear();
        data.addAll(newData);
        notifyDataSetChanged();
    }

    //Get By ID
    public void updateDataGetID(Person newData) {
        if (newData == null) {
            return;
        }
        // Update the data and notify the adapter that the data has changed
        data.clear();
        data.add(newData);
        notifyDataSetChanged(); //Knap så vigtig i sidste ende
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Inflate the list item layout if it hasn't been inflated already
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_layout, parent, false);
        }

        // Get references to the TextViews in the list item layout
        TextView nameTextView = convertView.findViewById(R.id.tvNavn);
        TextView addressTextView = convertView.findViewById(R.id.tvDescrib);
        TextView phoneTextView = convertView.findViewById(R.id.tvNationalitet);
        CheckBox favoriteCheckBox = convertView.findViewById(R.id.tvFavorite);

        // Get the Person object for this position in the data list
        Person person = data.get(position);

        // Set the text of the TextViews to the values from the Person object
        nameTextView.setText("Name: " + person.getNavn());
        addressTextView.setText("Beskrivelse: " +person.getDescrip());
        phoneTextView.setText("Nationalitet: " +person.getNationalitet());
        favoriteCheckBox.setChecked(person.Fav);

        // Return the inflated and updated list item layout
        return convertView;
    }



}