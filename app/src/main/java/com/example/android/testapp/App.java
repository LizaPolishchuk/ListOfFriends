package com.example.android.testapp;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.android.testapp.databaseAndRetrofit.ApiInterface;
import com.example.android.testapp.databaseAndRetrofit.DatabaseHelper;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    private static App instance;
    private DatabaseHelper database;
    static final String baseUrl = "https://randomuser.me/";
    private Retrofit retrofit;
    private ApiInterface apiInterface;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        database = Room.databaseBuilder(this, DatabaseHelper.class, "myDatabase")
                .allowMainThreadQueries()
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiInterface = retrofit.create(ApiInterface.class);
    }

    public static App getInstance() {
        return instance;
    }

    public DatabaseHelper getDatabase() {
        return database;
    }

    public ApiInterface getInterface(){return apiInterface;}

    public Context getContext(){return getApplicationContext();}
}
