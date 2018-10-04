package com.example.android.testapp;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("api/?results=10&nat=ES")
    Call<Results> results();
}
