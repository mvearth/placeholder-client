package com.example.placeholder.data.api.services;

import com.example.placeholder.data.model.BookSuggestion;
import com.example.placeholder.data.model.MovieSuggestion;
import com.example.placeholder.data.model.OtherSuggestion;
import com.example.placeholder.data.model.Person;
import com.example.placeholder.data.model.SongSuggestion;
import com.example.placeholder.data.model.Suggestion;
import com.example.placeholder.data.model.SuggestionType;

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
    @POST("/publisher")
    Call<Object> postSuggestion(@Body Suggestion suggestion);

    @GET("/publisher")
    Call<BookSuggestion[]> getOwnBookSuggestions(@Query("email") String email, @Query("suggestion_type") String suggestionType);

    @GET("/publisher")
    Call<SongSuggestion[]> getOwnSongSuggestions(@Query("email") String email, @Query("suggestion_type") String suggestionType);

    @GET("/publisher")
    Call<MovieSuggestion[]> getOwnMovieSerieSuggestions(@Query("email") String email, @Query("suggestion_type") String suggestionType);

    @GET("/publisher")
    Call<OtherSuggestion[]> getOwnOtherSuggestions(@Query("email") String email, @Query("suggestion_type") String suggestionType);



    @GET("/publisher/followings")
    Call<BookSuggestion[]> getFeedBookSuggestions(@Query("email") String email, @Query("suggestion_type") String suggestionType);

    @GET("/publisher/followings")
    Call<SongSuggestion[]> getFeedSongSuggestions(@Query("email") String email, @Query("suggestion_type") String suggestionType);

    @GET("/publisher/followings")
    Call<MovieSuggestion[]> getFeedMovieSerieSuggestions(@Query("email") String email, @Query("suggestion_type") String suggestionType);

    @GET("/publisher/followings")
    Call<OtherSuggestion[]> getFeedOtherSuggestions(@Query("email") String email, @Query("suggestion_type") String suggestionType);

    @GET("/books/v1/volumes")
    Call<Suggestion> getRandomSuggestion();

    @POST("add/")
    Call<Suggestion> addSuggestion(@Body Suggestion user);

    @PUT("update/{id}")
    Call<Suggestion> updateSuggestion(@Path("id") int id, @Body Suggestion user);

    @DELETE("delete/{id}")
    Call<Suggestion> deleteUser(@Path("id") int id);
}
