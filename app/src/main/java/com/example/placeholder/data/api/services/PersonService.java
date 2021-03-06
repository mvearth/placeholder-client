package com.example.placeholder.data.api.services;

import com.example.placeholder.data.model.Follow;
import com.example.placeholder.data.model.Login;
import com.example.placeholder.data.model.Person;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PersonService {
    @POST("/login")
    Call<Person> login(@Body Login login);

    @POST("/person")
    Call<Object> signUp(@Body Person person);

    @PUT("/person/{id}")
    Call<Person> updatePerson(@Path("id")int id, @Body Person person);

    @GET("/person/search/{value}")
    Call<Person[]> searchPeople(@Path("value") String value);

    @GET("/followers/{email}")
    Call<Person[]> getFollowers(@Path("email") String email);

    @GET("/followings/{email}")
    Call<Person[]> getFollowings(@Path("email") String email);

    @POST("/follow")
    Call<Object> postFollowing(@Body Follow follow);

    @HTTP(method = "DELETE", path = "/follow", hasBody = true)
    Call<Object> delFollowing(@Body Follow follow);
}
