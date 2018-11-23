package com.example.android.testapp.paging;

import android.arch.paging.DataSource;

import com.example.android.testapp.data.Person;
import com.example.android.testapp.dependency.MainComponent;

public class MainDataFactory extends DataSource.Factory<Integer, Person> {

    private MainComponent component;

    public MainDataFactory(MainComponent component) {
        this.component = component;
    }

    @Override
    public DataSource<Integer, Person> create() {
        return component.getMainDataSource();
    }
}
