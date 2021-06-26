package com.example.placeholder.ui.search;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.placeholder.data.api.SearchRepository;
import com.example.placeholder.data.model.Person;

public class SearchViewModel extends ViewModel {
    SearchRepository searchRepository;

    private final LiveData<Person[]> personArrayObservable;

    public SearchViewModel() {
        searchRepository = new SearchRepository();
        personArrayObservable = searchRepository.getSearchedPeople();
    }

    public LiveData<Person[]> getPeople() {
        return personArrayObservable;
    }
}