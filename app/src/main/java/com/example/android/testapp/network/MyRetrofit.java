package com.example.android.testapp.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyRetrofit {

    private static final String baseUrl = "https://randomuser.me/";
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private ApiPerson apiPerson = retrofit.create(ApiPerson.class);

    public ApiPerson getApiPerson() {
        return apiPerson;
    }
}
