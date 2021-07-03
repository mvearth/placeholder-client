package com.example.placeholder.ui.search;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.placeholder.data.model.Person;
import com.example.placeholder.data.repository.PersonRepository;

public class SearchViewModel extends ViewModel {
    PersonRepository personRepository;

    public SearchViewModel() {
        personRepository = PersonRepository.getInstance();
    }

    public LiveData<Person[]> getSearchedPeople() {
        return personRepository.getSearchedPeople();
    }

    public void searchPeople(String searchString) {
        personRepository.searchPeople(searchString);
    }
}

