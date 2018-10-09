package com.example.android.testapp.modelMVP;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.android.testapp.App;
import com.example.android.testapp.databaseAndRetrofit.DatabaseHelper;
import com.example.android.testapp.datamodels.Person;
import com.example.android.testapp.datamodels.otherPersonData.Results;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.CONNECTIVITY_SERVICE;

public class ModelData implements Contract.Model {

    private DatabaseHelper database = App.getInstance().getDatabase();
    private Call<Results> resultsCall = App.getInstance().getInterface().results();
    private List<Person> personList = new ArrayList<>();

    private Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            if (hasConnection()) {
                database.getDaoPerson().deleteAll(database.getDaoPerson().getAllPersons());
                database.getDaoPerson().insertInDbList(personList);
            }
        }
    });

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

    //Get the data
    @Override
    public void getPersonList(final OnFinishedListener onFinishedListener) {

        //If we have Internet connection get the data from api by Retrofit
        if (hasConnection()) {
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
                    onFinishedListener.onFinish(personList);
                }

                @Override
                public void onFailure(Call<Results> call, Throwable t) {
                    onFinishedListener.onFailure(t);
                }
            });

            //If we have not Internet connection get the data from database by Room
        } else {
            personList.addAll(database.getDaoPerson().getAllPersons());
            onFinishedListener.onFinish(personList);
        }
    }
}

