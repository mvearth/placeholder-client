package com.example.placeholder.data.api.services;

import com.example.placeholder.data.model.Person;
import com.example.placeholder.data.model.Suggestion;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SuggestionService {
    @GET("/books/v1/volumes")
    Call<List<Suggestion>> getFeedSuggestions(@Path("id") int id);

    @GET("/books/v1/volumes")
    Call<List<Suggestion>> getOwnSuggestions(@Path("id") int id);

    @GET("/books/v1/volumes")
    Call<Suggestion> getRandomSuggestion();

    @POST("add/")
    Call<Suggestion> addSuggestion(@Body Suggestion user);

    @PUT("update/{id}")
    Call<Suggestion> updateSuggestion(@Path("id") int id, @Body Suggestion user);

    @DELETE("delete/{id}")
    Call<Suggestion> deleteUser(@Path("id") int id);
}
