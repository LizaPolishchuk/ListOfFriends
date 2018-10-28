package com.example.android.testapp.retrofit;

import com.example.android.testapp.datamodels.otherPersonData.Results;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiPerson {
    @GET("api/?results=10&nat=ES")
    Call<Results> results();
}
