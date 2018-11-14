package com.example.android.testapp.network;

import com.example.android.testapp.data.otherPersonData.Results;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiPerson {
    @GET("api/?nat=ES")
    Call<Results> results(@Query("results") int count);
}
