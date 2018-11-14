package com.example.android.testapp.data.otherPersonData;

import com.example.android.testapp.data.Person;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Results {
    @SerializedName("results")
    public List<Person> personList;

    public List<Person> getPersonList() {
        return personList;
    }
}
