package com.example.android.testapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import static android.content.Context.CONNECTIVITY_SERVICE;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private DatabaseHelper database;
    private Context context;
    private List<Person> personList, personList1;

     MyAdapter(Context context, List<Person> personList) {
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

        Log.d("myLogs", "onBind");

        //получаем данные с personList, полученного от Retrofit или базы данных
        final String name;
        if (person.getName().getLast()!=null) {
          name  = person.getName().first + " " + person.getName().last;
        } else {
            name = person.getName().getFirst();
        }
        final String gender = person.getGender();
        final String image = person.getPicture().getLarge();
        final String email = person.getEmail();
        final String date = person.getRegistered().getDate();

        //Добавляем данные в Intent активность с описанием определенного человек
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AboutActivity.class);
                //android.text.format.DateFormat.format("yyyy/MM/dd", new Date(date));
                intent.putExtra("gender", gender);
                intent.putExtra("name", name);
                intent.putExtra("image", image);
                intent.putExtra("email", email);
                intent.putExtra("registered", date);
                context.startActivity(intent);
            }
        });

        //добавляем все значения в отдельном элементе списка
        holder.genderPerson.setText(gender);
        holder.namePerson.setText(name);
        String url = person.getPicture().getLarge();
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

            database = App.getInstance().getDatabase();
        }
    }

}
