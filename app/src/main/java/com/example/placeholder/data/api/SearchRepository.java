package com.example.placeholder.data.api;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.placeholder.data.model.BookSuggestion;
import com.example.placeholder.data.model.Person;
import com.example.placeholder.data.model.Suggestion;

public class SearchRepository {
    public LiveData<Person[]> getSearchedPeople(String searchString) {
        final MutableLiveData<Person[]> data = new MutableLiveData<>();

        final Person person = new Person();
        person.setNickname("nome");
        person.setName("nome q n é apelido");
        //person.setIcon(BitmapFactory.decodeFile("/documents/raw:/storage/emulated/0/Download/goku totosa.jpg"));

        final Person[] people = new Person[]{person, person, person, person, person, person};
        data.setValue(people);

        return data;
    }
}