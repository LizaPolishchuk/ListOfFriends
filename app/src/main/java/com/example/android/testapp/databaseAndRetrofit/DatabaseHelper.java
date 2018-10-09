package com.example.android.testapp.databaseAndRetrofit;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.android.testapp.datamodels.Person;

@Database(entities = {Person.class}, version = 1, exportSchema = false)
public abstract class DatabaseHelper extends RoomDatabase {
    public abstract DaoPerson getDaoPerson();
}
