package com.example.placeholder.data.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String BASE_URL = "http://localhost:8000";

    private static Retrofit _retrofit = null;

    public static Retrofit getClient(){
        if (_retrofit == null) {
            _retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory((GsonConverterFactory.create()))
                    .build();
        }

        return _retrofit;
    }
}