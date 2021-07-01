package com.example.placeholder.data.api.services;

import com.example.placeholder.data.model.Person;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PersonService {
    @GET("/books/v1/volumes")
    Call<Person> login(@Query("email") String email, @Query("password") String password);

    @POST("/person")
    Call<Person> signUp(@Body Person person);

    @GET("/person/followers/{nickname}")
    Call<List<Person>> getFollowers(@Path("nickname") String nickname);

    @GET("/person/following/{nickname}")
    Call<List<Person>> getFollowing(@Path("nickname") String nickname);
}
