package com.example.android.testapp;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

@Database(entities = {Person.class}, version = 1, exportSchema = false)
@TypeConverters(MyConverter.class)
public abstract class DatabaseHelper extends RoomDatabase {
    abstract DaoPerson getDaoPerson();
}
