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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SuggestionRepository {
    private MutableLiveData<Suggestion> randomSuggestion = new MutableLiveData<>();

    final private SuggestionService suggestionService;

    private static volatile SuggestionRepository instance;

    public SuggestionRepository(){
        suggestionService = ApiClient.getClient().create(SuggestionService.class);
    }

    public static SuggestionRepository getInstance() {
        if (instance == null) {
            instance = new SuggestionRepository();
        }
        return instance;
    }

    public LiveData<Suggestion[]> getFollowingSuggestions(String nickname) {
        final MutableLiveData<Suggestion[]> data = new MutableLiveData<>();

        Person person1 = new Person();
        person1.setNickname("eren and Titan");
        person1.setName("Titan");
        person1.setIcon(BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().toString() + "/Download" + "/1.jpg"));

        Person person2 = new Person();
        person2.setNickname("subaru");
        person2.setName("Subaru loves Emilia");
        person2.setIcon(BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().toString() + "/Download" + "/2.jpg"));

        Person person3 = new Person();
        person3.setNickname("lufi");
        person3.setName("Lufi");
        person3.setIcon(BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().toString() + "/Download" + "/3.jpg"));

        Person person4 = new Person();
        person4.setNickname("person");
        person4.setName("Hi im human");
        person4.setIcon(BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().toString() + "/Download" + "/4.jpg"));

        Suggestion suggestion1 = new BookSuggestion();
        suggestion1.setTitle("Harry Potter");
        suggestion1.setDescription("Wizards, magic, cool moves. I liked it!");
        suggestion1.setPerson(person1);
        suggestion1.setImages(new Bitmap[]{
                BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().toString() + "/Download" + "/Hp.png")
        });

        Suggestion suggestion2 = new SongSuggestion();
        suggestion2.setTitle("Yonkers");
        suggestion2.setDescription("It's not a light song");
        suggestion2.setPerson(person2);
        suggestion2.setImages(new Bitmap[]{
                BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().toString() + "/Download" + "/TtheC.png")
        });

        Suggestion suggestion3 = new MovieSuggestion();
        suggestion3.setTitle("Tenet");
        suggestion3.setDescription("Wtf, it doesn't make any sense! What the hell?");
        suggestion3.setPerson(person3);
        suggestion3.setImages(new Bitmap[]{
                BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().toString() + "/Download" + "/Tenet.png")
        });

        Suggestion suggestion4 = new MovieSuggestion();
        suggestion4.setTitle("Flaked");
        suggestion4.setDescription("Hispters TV show");
        suggestion4.setPerson(person4);
        suggestion4.setImages(new Bitmap[]{
                BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().toString() + "/Download" + "/Flaked.jpg")
        });

        final Suggestion[] suggestions = new Suggestion[]{suggestion1, suggestion2, suggestion3, suggestion4};
        data.setValue(suggestions);

        return data;
    }

    public LiveData<Suggestion[]> getUserSuggestions(String nickname) {
        final MutableLiveData<Suggestion[]> data = new MutableLiveData<>();

        Person person2 = new Person();
        person2.setNickname("subaru");
        person2.setName("Subaru loves Emilia");
        person2.setIcon(BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().toString() + "/Download" + "/2.jpg"));

        Suggestion suggestion2 = new SongSuggestion();
        suggestion2.setTitle("Yonkers");
        suggestion2.setDescription("It's not a light song");
        suggestion2.setPerson(person2);
        suggestion2.setImages(new Bitmap[]{
                BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().toString() + "/Download" + "/TtheC.png")
        });

        final Suggestion[] suggestions = new Suggestion[]{suggestion2};
        data.setValue(suggestions);

        return data;
    }

    public void updateRandomSuggestion(SuggestionType suggestionType) {
        if (suggestionType == SuggestionType.BookSuggestion) {
            Suggestion suggestion1 = new BookSuggestion();
            suggestion1.setTitle("Harry Potter");
            suggestion1.setDescription("Wizards, magic, cool moves. I liked it!");
            suggestion1.setImages(new Bitmap[]{
                    BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().toString() + "/Download" + "/Hp.png")
            });

            randomSuggestion.setValue(suggestion1);
        }

        if (suggestionType == SuggestionType.MovieSuggestion) {
            Suggestion suggestion3 = new MovieSuggestion();
            suggestion3.setTitle("Tenet");
            suggestion3.setDescription("Wtf, it doesn't make any sense! What the hell?");
            suggestion3.setImages(new Bitmap[]{
                    BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().toString() + "/Download" + "/Tenet.png")
            });

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

    public void postSuggestion(Suggestion suggestion){
        Call call = suggestionService.postSuggestion(suggestion);
        call.enqueue(new Callback<Person[]>() {
            @Override
            public void onResponse(Call<Person[]> call, Response<Person[]> response) {
                if(response.isSuccessful()){
                    searchedPeople.setValue(response.body());
                    return;
                }

                searchedPeople.setValue(null);
            }

            @Override
            public void onFailure(Call<Person[]> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}
