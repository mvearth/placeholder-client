package com.example.placeholder.data.api;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.placeholder.data.model.BookSuggestion;
import com.example.placeholder.data.model.Person;
import com.example.placeholder.data.model.Suggestion;

public class SuggestionRepository {
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
}
