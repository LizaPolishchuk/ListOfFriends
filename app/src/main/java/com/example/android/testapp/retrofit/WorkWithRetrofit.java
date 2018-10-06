package com.example.android.testapp.retrofit;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.android.testapp.App;
import com.example.android.testapp.MyAdapter;
import com.example.android.testapp.database.DatabaseHelper;
import com.example.android.testapp.datamodels.Person;
import com.example.android.testapp.datamodels.Results;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.CONNECTIVITY_SERVICE;

//In this class we check the Internet connection and depending on it run the following methods.

public class WorkWithRetrofit {
    private DatabaseHelper database = App.getInstance().getDatabase();
    private Call<Results> resultsCall = App.getInstance().getInterface().results();

    private List<Person> personList = new ArrayList<>();
    private MyAdapter myAdapter = new MyAdapter(App.getInstance().getContext(), personList);

    private TextView tvConnection;
    private RecyclerView recyclerView;

    private Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            if(hasConnection()){
                database.getDaoPerson().deleteAll(database.getDaoPerson().getAllPersons());
                database.getDaoPerson().insertInDbList(personList);
            } else {
                personList.addAll(database.getDaoPerson().getAllPersons());
            }
        }
    });
    public WorkWithRetrofit(RecyclerView recyclerView, TextView tvConnection) {
        this.recyclerView = recyclerView;
        this.tvConnection = tvConnection;
    }

    //Checking the Internet connection
    private static boolean hasConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) App.getInstance().getContext().getSystemService(CONNECTIVITY_SERVICE);

        NetworkInfo wifiInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiInfo != null && wifiInfo.isConnected()) {
            return true;
        }
        wifiInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (wifiInfo != null && wifiInfo.isConnected()) {
            return true;
        }
        wifiInfo = connectivityManager.getActiveNetworkInfo();
        if (wifiInfo != null && wifiInfo.isConnected()) {
            return true;
        }
        return false;
    }

    public MyAdapter getMyAdapter() {
        return myAdapter;
    }

    //Get the data
    public void getTheList() {
        //If we have Internet connection get the data from api by Retrofit
        if (hasConnection()) {
            recyclerView.setVisibility(View.VISIBLE);
            tvConnection.setVisibility(View.GONE);

            resultsCall.enqueue(new Callback<Results>() {
                @Override
                public void onResponse(Call<Results> call, Response<Results> response) {

                    personList.clear();
                    assert response.body() != null;

                    personList.addAll(response.body().personList);

                    Collections.sort(personList, new Comparator<Person>() {
                        @Override
                        public int compare(Person person, Person person1) {
                            return person.getName().getFirst().compareTo(person1.getName().getFirst());
                        }
                    });
                    thread.start();
                    myAdapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<Results> call, Throwable t) {
                    Log.d("myLogs", "onFailure " + t);
                }
            });

            //If we have not Internet connection get the data from database by Room
        } else {
            tvConnection.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            thread.start();
            myAdapter.notifyDataSetChanged();
        }
    }

}
