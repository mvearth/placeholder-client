package com.example.placeholder.data.api;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.placeholder.data.model.BookSuggestion;
import com.example.placeholder.data.model.Person;
import com.example.placeholder.data.model.Suggestion;
import com.example.placeholder.ui.search.SearchViewModel;

public class SearchRepository {

    MutableLiveData<Person[]> people;

    public SearchRepository() {
        people = new MutableLiveData<>(new Person[]{});
    }

    public LiveData<Person[]> getSearchedPeople() {
        return people;
    }

    public void searchPeople(String searchString) {

        final Person person1 = new Person();
        person1.setNickname("nome");
        person1.setName("nome q n é apelido");

        final Person person2 = new Person();
        person2.setNickname("name");
        person2.setName("aaaaa");
        //person.setIcon(BitmapFactory.decodeFile("/documents/raw:/storage/emulated/0/Download/goku totosa.jpg"));

        final Person[] peopleaaasa = new Person[]{person1, person2};
        people.setValue(peopleaaasa);
    }
}