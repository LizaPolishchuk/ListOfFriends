package com.example.android.testapp;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface DaoPerson {

    @Insert
    void  insertInDbList(List<Person> personList);

    @Delete
    void deleteAll(List<Person> personList);

    @Query("SELECT*FROM person")
    List<Person> getAllPersons();
}
