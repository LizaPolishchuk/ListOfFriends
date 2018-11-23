package com.example.android.testapp.utils;

import android.os.AsyncTask;

import com.example.android.testapp.data.Person;
import com.example.android.testapp.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


/**
 * Class for work with Room NOT in main thread using executors and asyncTasks
 */
public class WorkWithDatabase {

    private static List<Person> personList = new ArrayList<>();

    public static void putData(final DatabaseHelper database, final List<Person> personList) {
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                database.getDaoPerson().deleteAll(database.getDaoPerson().getAllPersons());
                database.getDaoPerson().insertInDbList(personList);
            }
        };
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(runnable);
    }

    public static List<Person> getPersonList(final DatabaseHelper database) {
        AsyncTask<Void, Void, List<Person>> task = new AsyncTask<Void, Void, List<Person>>() {
            @Override
            protected List<Person> doInBackground(Void... voids) {
                return database.getDaoPerson().getAllPersons();
            }
        };
        task.execute();
        try {
            return task.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return personList;
    }
}
