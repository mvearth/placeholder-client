package com.example.placeholder.data.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String BASE_URL = "http://10.0.2.2:8000/";

    private static Retrofit _retrofit = null;

    public static Retrofit getClient(){
        if (_retrofit == null) {
            _retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory((GsonConverterFactory.create(getGson())))
                    .build();
        }

        return _retrofit;
    }

    private static Gson getGson() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }
}