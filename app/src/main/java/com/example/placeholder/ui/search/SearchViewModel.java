package com.example.placeholder.ui.search;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.placeholder.data.repository.SearchRepository;
import com.example.placeholder.data.model.Person;

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

