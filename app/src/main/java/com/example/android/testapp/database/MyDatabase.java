package com.example.android.testapp.database;

import android.arch.persistence.room.Room;

import com.example.android.testapp.App;

public class MyDatabase {
    private DatabaseHelper database = Room.databaseBuilder(App.getInstance(), DatabaseHelper.class, "myDatabase")
            .allowMainThreadQueries()
            .build();
    private DaoPerson daoPerson = database.getDaoPerson();

    public DaoPerson getDaoPerson() {
        return daoPerson;
    }
}
