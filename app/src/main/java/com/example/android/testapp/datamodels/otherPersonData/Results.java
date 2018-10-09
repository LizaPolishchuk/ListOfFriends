package com.example.android.testapp.datamodels.otherPersonData;

import com.example.android.testapp.datamodels.Person;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Results {
    @SerializedName("results")
    public List<Person> personList;
}
