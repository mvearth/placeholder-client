package com.example.placeholder.ui.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.placeholder.data.model.BookSuggestion;
import com.example.placeholder.data.model.MovieSuggestion;
import com.example.placeholder.data.model.OtherSuggestion;
import com.example.placeholder.data.model.SongSuggestion;
import com.example.placeholder.data.repository.PersonRepository;
import com.example.placeholder.data.repository.SuggestionRepository;
import com.example.placeholder.data.model.Person;
import com.example.placeholder.data.model.Suggestion;

import java.util.LinkedList;

public class ProfileViewModel extends ViewModel {
    SuggestionRepository suggestionRepository;
    PersonRepository personRepository;

    public ProfileViewModel() {
        suggestionRepository = new SuggestionRepository();
        personRepository = PersonRepository.getInstance();
    }

    public LiveData<LinkedList<Suggestion>> getSuggestions(String email) {
        return suggestionRepository.getUserSuggestions(email);
    }

    public LiveData<Person> getLoggedPerson() {
        return personRepository.getLoggedPerson();
    }
}