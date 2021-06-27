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

        final Person person1 = new Person();
        person1.setNickname("nome");
        person1.setName("nome q n é apelido");

        final Person person2 = new Person();
        person2.setNickname("name");
        person2.setName("aaaaa");
        //person.setIcon(BitmapFactory.decodeFile("/documents/raw:/storage/emulated/0/Download/goku totosa.jpg"));

        final Person[] people = new Person[2];
        if (person1.getNickname().contains(searchString))
            people[0] = person1;

        if (person2.getNickname().contains(searchString))
            people[1] = person2;

        data.setValue(people);

        return data;
    }
}