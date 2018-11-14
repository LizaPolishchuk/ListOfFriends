package com.example.android.testapp.paging;

import android.arch.paging.DataSource;

import com.example.android.testapp.data.Person;
import com.example.android.testapp.database.MyDatabase;
import com.example.android.testapp.network.MyRetrofit;
import com.example.android.testapp.utils.OnHideProgress;

public class MainDataFactory extends DataSource.Factory<Integer, Person> {

    private MyRetrofit retrofit;
    private MyDatabase database;
    private OnHideProgress onHideProgress;

    public MainDataFactory(MyRetrofit retrofit, MyDatabase database, OnHideProgress onHideProgress) {
        this.retrofit = retrofit;
        this.database = database;
        this.onHideProgress = onHideProgress;
    }

    @Override
    public DataSource<Integer, Person> create() {
        return new MainDataSource(retrofit, database, onHideProgress);
    }
}
