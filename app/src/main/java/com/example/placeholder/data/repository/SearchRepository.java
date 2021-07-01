package com.example.placeholder.data.repository;

import android.graphics.BitmapFactory;
import android.os.Environment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.placeholder.data.model.Person;

public class SearchRepository {

    MutableLiveData<Person[]> people;

    public SearchRepository() {
        people = new MutableLiveData<>(new Person[]{});
    }

    public LiveData<Person[]> getSearchedPeople() {
        return people;
    }

    public void searchPeople(String searchString) {

        Person person3 = new Person();
        person3.setNickname("lufi");
        person3.setName("Lufi");
        person3.setIcon(BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().toString() + "/Download" + "/3.jpg"));

        final Person[] peopleaaasa = new Person[]{person3};
        people.setValue(peopleaaasa);
    }
}