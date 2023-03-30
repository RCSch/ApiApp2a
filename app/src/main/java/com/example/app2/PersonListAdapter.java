package com.example.app2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PersonListAdapter extends RecyclerView.Adapter<PersonListAdapter.PersonViewHolder> {

    private List<Person> mPersonList;

    public PersonListAdapter(List<Person> personList) {
        mPersonList = personList;
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_get_all_list, parent, false);
        return new PersonViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        Person person = mPersonList.get(position);
        holder.navnTextView.setText(person.getNavn());
        holder.descripTextView.setText(String.valueOf(person.getDescrip()));
    }

    @Override
    public int getItemCount() {
        return mPersonList.size();
    }

    static class PersonViewHolder extends RecyclerView.ViewHolder {
        TextView navnTextView;
        TextView descripTextView;

        PersonViewHolder(View itemView) {
            super(itemView);
            navnTextView = itemView.findViewById(R.id.navnTextView);
            descripTextView = itemView.findViewById(R.id.descripTextView);

        }
    }
}