package com.example.android.testapp.network;

import com.example.android.testapp.dependency.SingleScope;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Module to inject retrofit
 */
@Module
public class ModuleRetrofit {

    private static final String baseUrl = "https://randomuser.me/";

    @SingleScope
    @Provides
    Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    public ApiPerson getApiPerson() {
        return getRetrofit().create(ApiPerson.class);
    }
}
