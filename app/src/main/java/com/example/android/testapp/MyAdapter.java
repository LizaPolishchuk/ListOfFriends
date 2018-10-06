package com.example.android.testapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.testapp.activities.AboutActivity;
import com.example.android.testapp.datamodels.Person;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    private List<Person> personList;

     public MyAdapter(Context context, List<Person> personList) {
        this.context = context;
        this.personList = personList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Person person = personList.get(position);

        //Getting the data from Retrofit or Database
        final String name = person.getName().getFirst() + " " + person.getName().getLast();
        final String gender = person.getGender();
        final String image = person.getPicture().getLarge();
        final String email = person.getEmail();
        final String date = person.getRegistered().getDate();

        //Adding data to Intent for start AboutActivity to describe the selected person
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AboutActivity.class);

                intent.putExtra("gender", gender);
                intent.putExtra("name", name);
                intent.putExtra("image", image);
                intent.putExtra("email", email);
                intent.putExtra("registered", date);
                context.startActivity(intent);
            }
        });

        //Adding values to views activity_main
        holder.genderPerson.setText(gender);
        holder.namePerson.setText(name);
        String url = person.getPicture().getMedium();
        Picasso.get().load(url).into(holder.imagePerson);
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView namePerson;
        TextView genderPerson;
        ImageView imagePerson;
        Bundle bundle;

        MyViewHolder(View itemView) {
            super(itemView);
            namePerson = (TextView) itemView.findViewById(R.id.tv_name_person);
            genderPerson = (TextView) itemView.findViewById(R.id.tv_gender_person);
            imagePerson = (ImageView) itemView.findViewById(R.id.image_person);
            bundle = new Bundle();
        }
    }

}
