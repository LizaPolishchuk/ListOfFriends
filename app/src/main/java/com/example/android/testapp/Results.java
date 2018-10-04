package com.example.android.testapp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Results {
    @SerializedName("results")
    public List<Person> personList;
}
