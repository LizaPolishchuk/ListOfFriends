package com.example.android.testapp.database;

import android.arch.persistence.room.Room;
import android.content.Context;

public class MyDatabase {

    private Context context;
    private DatabaseHelper database = Room.databaseBuilder(context, DatabaseHelper.class, "myDatabase")
            .build();

    public MyDatabase(Context context) {
        this.context = context;
    }
    private DaoPerson daoPerson = database.getDaoPerson();

    public DaoPerson getDaoPerson() {
        return daoPerson;
    }
}
