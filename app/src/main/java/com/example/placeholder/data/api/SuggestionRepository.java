package com.example.placeholder.data.api;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.placeholder.data.model.BookSuggestion;
import com.example.placeholder.data.model.Person;
import com.example.placeholder.data.model.Suggestion;

public class SuggestionRepository {
    public LiveData<Suggestion[]> getSuggestions() {
        final MutableLiveData<Suggestion[]> data = new MutableLiveData<>();

        final Person person = new Person();
        person.setNickname("nome");
        //person.setIcon(BitmapFactory.decodeFile("C:\\Users\\fabio\\Downloads\\goku totosa.jpg"));

        final BookSuggestion bookSuggestion = new BookSuggestion();
        bookSuggestion.setDescription("meu livrin");
        bookSuggestion.setPerson(person);

        final Suggestion[] suggestions = new Suggestion[]{bookSuggestion};
        data.setValue(suggestions);

        return data;
    }
}
