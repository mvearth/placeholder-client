package com.example.placeholder.data.repository;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.placeholder.data.api.ApiClient;
import com.example.placeholder.data.api.services.PersonService;
import com.example.placeholder.data.api.services.SuggestionService;
import com.example.placeholder.data.model.BookSuggestion;
import com.example.placeholder.data.model.MovieSuggestion;
import com.example.placeholder.data.model.OtherSuggestion;
import com.example.placeholder.data.model.Person;
import com.example.placeholder.data.model.SongSuggestion;
import com.example.placeholder.data.model.Suggestion;
import com.example.placeholder.data.model.SuggestionType;

import java.util.Arrays;
import java.util.LinkedList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SuggestionRepository {
    private MutableLiveData<Suggestion> randomSuggestion = new MutableLiveData<>();

    final private SuggestionService suggestionService;

    private static volatile SuggestionRepository instance;

    public SuggestionRepository() {
        suggestionService = ApiClient.getClient().create(SuggestionService.class);
    }

    public static SuggestionRepository getInstance() {
        if (instance == null) {
            instance = new SuggestionRepository();
        }
        return instance;
    }

    public LiveData<LinkedList<Suggestion>> getFollowingSuggestions(String email) {
        final MutableLiveData<LinkedList<Suggestion>> suggestions = new MutableLiveData<>();
        suggestions.setValue(new LinkedList<>());

        final LinkedList<Suggestion> suggestionLinkedList = new LinkedList<>();

        Call<BookSuggestion[]> booksCall = suggestionService.getFeedBookSuggestions(email, SuggestionType.BookSuggestion.toString());
        booksCall.enqueue(new Callback<BookSuggestion[]>() {
            @Override
            public void onResponse(Call<BookSuggestion[]> booksCall, Response<BookSuggestion[]> response) {
                if (response.isSuccessful()) {
                    suggestionLinkedList.addAll(Arrays.asList(response.body()));
                    suggestions.setValue(suggestionLinkedList);
                }
            }

            @Override
            public void onFailure(Call<BookSuggestion[]> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });

        Call<SongSuggestion[]> songsCall = suggestionService.getFeedSongSuggestions(email, SuggestionType.SongSuggestion.toString());
        songsCall.enqueue(new Callback<SongSuggestion[]>() {
            @Override
            public void onResponse(Call<SongSuggestion[]> songsCall, Response<SongSuggestion[]> response) {
                if (response.isSuccessful()) {
                    suggestionLinkedList.addAll(Arrays.asList(response.body()));
                    suggestions.setValue(suggestionLinkedList);
                }
            }

            @Override
            public void onFailure(Call<SongSuggestion[]> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });

        Call<MovieSuggestion[]> moviesSeriesCall = suggestionService.getFeedMovieSerieSuggestions(email, SuggestionType.MovieSuggestion.toString());
        moviesSeriesCall.enqueue(new Callback<MovieSuggestion[]>() {
            @Override
            public void onResponse(Call<MovieSuggestion[]> moviesSeriesCall, Response<MovieSuggestion[]> response) {
                if (response.isSuccessful()) {
                    suggestionLinkedList.addAll(Arrays.asList(response.body()));
                    suggestions.setValue(suggestionLinkedList);
                }
            }

            @Override
            public void onFailure(Call<MovieSuggestion[]> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });

        Call<OtherSuggestion[]> othersCall = suggestionService.getFeedOtherSuggestions(email, SuggestionType.OtherSuggestion.toString());
        othersCall.enqueue(new Callback<OtherSuggestion[]>() {
            @Override
            public void onResponse(Call<OtherSuggestion[]> othersCall, Response<OtherSuggestion[]> response) {
                if (response.isSuccessful()) {
                    suggestionLinkedList.addAll(Arrays.asList(response.body()));
                    suggestions.setValue(suggestionLinkedList);
                }
            }

            @Override
            public void onFailure(Call<OtherSuggestion[]> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });

        return suggestions;
    }

    public LiveData<LinkedList<Suggestion>> getUserSuggestions(String email) {
        final MutableLiveData<LinkedList<Suggestion>> suggestions = new MutableLiveData<>();
        suggestions.setValue(new LinkedList<>());

        final LinkedList<Suggestion> suggestionLinkedList = new LinkedList<>();

        Call<BookSuggestion[]> booksCall = suggestionService.getOwnBookSuggestions(email, SuggestionType.BookSuggestion.toString());
        booksCall.enqueue(new Callback<BookSuggestion[]>() {
            @Override
            public void onResponse(Call<BookSuggestion[]> booksCall, Response<BookSuggestion[]> response) {
                if (response.isSuccessful()) {
                    suggestionLinkedList.addAll(Arrays.asList(response.body()));
                    suggestions.setValue(suggestionLinkedList);
                }
            }

            @Override
            public void onFailure(Call<BookSuggestion[]> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });

        Call<SongSuggestion[]> songsCall = suggestionService.getOwnSongSuggestions(email, SuggestionType.SongSuggestion.toString());
        songsCall.enqueue(new Callback<SongSuggestion[]>() {
            @Override
            public void onResponse(Call<SongSuggestion[]> songsCall, Response<SongSuggestion[]> response) {
                if (response.isSuccessful()) {
                    suggestionLinkedList.addAll(Arrays.asList(response.body()));
                    suggestions.setValue(suggestionLinkedList);
                }
            }

            @Override
            public void onFailure(Call<SongSuggestion[]> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });

        Call<MovieSuggestion[]> moviesSeriesCall = suggestionService.getOwnMovieSerieSuggestions(email, SuggestionType.MovieSuggestion.toString());
        moviesSeriesCall.enqueue(new Callback<MovieSuggestion[]>() {
            @Override
            public void onResponse(Call<MovieSuggestion[]> moviesSeriesCall, Response<MovieSuggestion[]> response) {
                if (response.isSuccessful()) {
                    suggestionLinkedList.addAll(Arrays.asList(response.body()));
                    suggestions.setValue(suggestionLinkedList);
                }
            }

            @Override
            public void onFailure(Call<MovieSuggestion[]> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });

        Call<OtherSuggestion[]> othersCall = suggestionService.getOwnOtherSuggestions(email, SuggestionType.OtherSuggestion.toString());
        othersCall.enqueue(new Callback<OtherSuggestion[]>() {
            @Override
            public void onResponse(Call<OtherSuggestion[]> othersCall, Response<OtherSuggestion[]> response) {
                if (response.isSuccessful()) {
                    suggestionLinkedList.addAll(Arrays.asList(response.body()));
                    suggestions.setValue(suggestionLinkedList);
                }
            }

            @Override
            public void onFailure(Call<OtherSuggestion[]> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });

        return suggestions;
    }

    public void updateRandomSuggestion(SuggestionType suggestionType) {
        if (suggestionType == SuggestionType.BookSuggestion) {
            Suggestion suggestion1 = new BookSuggestion();
            suggestion1.setTitle("Harry Potter");
            suggestion1.setDescription("Wizards, magic, cool moves. I liked it!");

            randomSuggestion.setValue(suggestion1);
        }

        if (suggestionType == SuggestionType.MovieSuggestion) {
            Suggestion suggestion3 = new MovieSuggestion();
            suggestion3.setTitle("Tenet");
            suggestion3.setDescription("Wtf, it doesn't make any sense! What the hell?");

            randomSuggestion.setValue(suggestion3);
        }

        if (suggestionType == SuggestionType.SongSuggestion) {
            SongSuggestion bookSuggestion = new SongSuggestion();
            bookSuggestion.setTitle("o som lá");

            randomSuggestion.setValue(bookSuggestion);
        }

        if (suggestionType == SuggestionType.OtherSuggestion
                || suggestionType == SuggestionType.RandomSuggestion) {
            OtherSuggestion bookSuggestion = new OtherSuggestion();
            bookSuggestion.setTitle("o aaaaaa lá");

            randomSuggestion.setValue(bookSuggestion);
        }
    }

    public LiveData<Suggestion> getRandomSuggestion() {
        return this.randomSuggestion;
    }

    public LiveData<Integer> postSuggestion(Suggestion suggestion) {
        final MutableLiveData<Integer> result = new MutableLiveData<Integer>();

        Call<Object> call = suggestionService.postSuggestion(suggestion);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                result.setValue(response.code());
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });

        return result;
    }
}
