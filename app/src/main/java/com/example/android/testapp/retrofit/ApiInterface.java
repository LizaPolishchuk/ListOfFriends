package com.example.android.testapp.retrofit;

import com.example.android.testapp.datamodels.Results;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("api/?results=10&nat=ES")
    Call<Results> results();
}
