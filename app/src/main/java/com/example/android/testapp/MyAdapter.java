package com.example.android.testapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.testapp.datamodels.Person;
import com.example.android.testapp.utils.StartingActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Person> personList;

    public MyAdapter(List<Person> personList) {
        this.personList = personList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Person person = personList.get(position);

        /**Getting the data from Retrofit or Database*/
        final String name = person.getName().getFirst() + " " + person.getName().getLast();
        final String gender = person.getGender();
        final String image = person.getPicture().getLarge();
        final String email = person.getEmail();
        final String date = person.getRegistered().getDate();

        /**Adding data to method that start AboutActivity*/
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StartingActivity.startActivity(view.getContext(), gender, name, image, email, date);
            }
        });

        /**Adding values to views activity_main*/
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
        @BindView(R.id.tv_name_person)
        TextView namePerson;
        @BindView(R.id.tv_gender_person)
        TextView genderPerson;
        @BindView(R.id.image_person)
        ImageView imagePerson;

        Bundle bundle;

        MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            bundle = new Bundle();
        }
    }

}
