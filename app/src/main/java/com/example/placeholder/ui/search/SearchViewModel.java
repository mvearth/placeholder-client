package com.example.placeholder.ui.search;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.placeholder.data.model.Person;
import com.example.placeholder.data.repository.PersonRepository;

import java.util.LinkedList;

public class SearchViewModel extends ViewModel {
    PersonRepository personRepository;

    public SearchViewModel() {
        personRepository = PersonRepository.getInstance();
    }

    public LiveData<LinkedList<Person>> getSearchedPeople() {
        return personRepository.getSearchedPeople();
    }

    public LiveData<LinkedList<Person>> getFollowings() {
        return personRepository.getLoggedPersonFollowings();
    }

    public void searchPeople(String searchString) {
        personRepository.searchPeople(searchString);
    }

    public LiveData<Person> getLoggedPerson(){
        return personRepository.getLoggedPerson();
    }

    public void updateFollowInfo(Person person) {
        personRepository.updateFollowInfo(person);
    }

    public boolean isFollowing(Person person) {
        return personRepository.getLoggedPerson().getValue().isFollowing(person);
    }
}

