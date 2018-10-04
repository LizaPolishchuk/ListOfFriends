package com.example.android.testapp;

import android.app.Application;
import android.arch.persistence.room.Room;

public class App extends Application {

    private static App instance;
    private DatabaseHelper database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        database = Room.databaseBuilder(this, DatabaseHelper.class, "myDatabase")
                .allowMainThreadQueries()
                .build();
    }

    public static App getInstance() {
        return instance;
    }

    public DatabaseHelper getDatabase() {
        return database;
    }
}
