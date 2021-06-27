package com.example.placeholder.ui.search;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.placeholder.data.api.SearchRepository;
import com.example.placeholder.data.model.Person;

import org.jetbrains.annotations.NotNull;

public class SearchViewModel extends ViewModel {
    SearchRepository searchRepository;

    private LiveData<Person[]> personArrayObservable;

    public SearchViewModel() {
        searchRepository = new SearchRepository();
    }

    public LiveData<Person[]> getSearchedPeople() {
        personArrayObservable = searchRepository.getSearchedPeople();

        return personArrayObservable;
    }

    public void searchPeople(String searchString) {
        searchRepository.searchPeople(searchString);
    }
}

