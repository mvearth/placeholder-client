package com.example.placeholder.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.placeholder.data.model.Person;
import com.example.placeholder.data.repository.PersonRepository;
import com.example.placeholder.data.repository.SuggestionRepository;
import com.example.placeholder.data.model.Suggestion;

import java.util.LinkedList;

public class HomeViewModel extends ViewModel {
    SuggestionRepository suggestionRepository;
    PersonRepository personRepository;

    public HomeViewModel(){
        suggestionRepository = SuggestionRepository.getInstance();
        personRepository = PersonRepository.getInstance();
    }

    public LiveData<Person> getLoggedPerson() {
        return personRepository.getLoggedPerson();
    }

    public LiveData<LinkedList<Person>> getFollowsInfo(){
        return personRepository.getLoggedPersonFollowings();
    }

    public LiveData<LinkedList<Suggestion>> getFollowingsSuggestions() {
        return suggestionRepository.getFollowingSuggestions(personRepository.getLoggedPerson().getValue().getEmail());
    }
}