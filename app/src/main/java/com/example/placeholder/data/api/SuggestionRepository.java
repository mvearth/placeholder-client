package com.example.placeholder.data.api;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.placeholder.data.model.BookSuggestion;
import com.example.placeholder.data.model.MovieSuggestion;
import com.example.placeholder.data.model.OtherSuggestion;
import com.example.placeholder.data.model.Person;
import com.example.placeholder.data.model.SongSuggestion;
import com.example.placeholder.data.model.Suggestion;
import com.example.placeholder.data.model.SuggestionType;

public class SuggestionRepository {
    private MutableLiveData<Suggestion> randomSuggestion = new MutableLiveData<>();

    public LiveData<Suggestion[]> getFollowingSuggestions(String nickname) {
        final MutableLiveData<Suggestion[]> data = new MutableLiveData<>();

        final Person person = new Person();
        person.setNickname("nome");
        //person.setIcon(BitmapFactory.decodeFile("/documents/raw:/storage/emulated/0/Download/goku totosa.jpg"));

        final BookSuggestion bookSuggestion = new BookSuggestion();
        bookSuggestion.setDescription("meu livrin");
        bookSuggestion.setTitle("o libro fasdiofjadsp");
        bookSuggestion.setPerson(person);

        final Suggestion[] suggestions = new Suggestion[]{bookSuggestion, bookSuggestion, bookSuggestion, bookSuggestion, bookSuggestion, bookSuggestion, bookSuggestion, bookSuggestion};
        data.setValue(suggestions);

        return data;
    }

    public LiveData<Suggestion[]> getUserSuggestions(String nickname){
        final MutableLiveData<Suggestion[]> data = new MutableLiveData<>();

        final Person person = new Person();
        person.setNickname("nome");
        //person.setIcon(BitmapFactory.decodeFile("/documents/raw:/storage/emulated/0/Download/goku totosa.jpg"));

        final BookSuggestion bookSuggestion = new BookSuggestion();
        bookSuggestion.setDescription("meu livrin");
        bookSuggestion.setTitle("o libro fasdiofjadsp");
        bookSuggestion.setPerson(person);

        final Suggestion[] suggestions = new Suggestion[]{bookSuggestion, bookSuggestion, bookSuggestion, bookSuggestion, bookSuggestion, bookSuggestion, bookSuggestion, bookSuggestion};
        data.setValue(suggestions);

        return data;
    }

    public void updateRandomSuggestion(SuggestionType suggestionType){
        if (suggestionType == SuggestionType.BookSuggestion){
            BookSuggestion bookSuggestion = new BookSuggestion();
            bookSuggestion.setTitle("o livro lá");

            randomSuggestion.setValue(bookSuggestion);
        }

        if (suggestionType == SuggestionType.MovieSuggestion){
            MovieSuggestion bookSuggestion = new MovieSuggestion();
            bookSuggestion.setTitle("o filme lá");

            randomSuggestion.setValue(bookSuggestion);
        }

        if (suggestionType == SuggestionType.SongSuggestion){
            SongSuggestion bookSuggestion = new SongSuggestion();
            bookSuggestion.setTitle("o som lá");

            randomSuggestion.setValue(bookSuggestion);
        }

        if (suggestionType == SuggestionType.OtherSuggestion
            || suggestionType == SuggestionType.RandomSuggestion){
            OtherSuggestion bookSuggestion = new OtherSuggestion();
            bookSuggestion.setTitle("o aaaaaa lá");

            randomSuggestion.setValue(bookSuggestion);
        }
    }

    public LiveData<Suggestion> getRandomSuggestion(){
        return this.randomSuggestion;
    }
}
