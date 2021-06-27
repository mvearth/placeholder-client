package com.example.placeholder.ui.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.placeholder.data.api.PersonRepository;
import com.example.placeholder.data.api.SuggestionRepository;
import com.example.placeholder.data.model.Person;
import com.example.placeholder.data.model.Suggestion;

public class ProfileViewModel extends ViewModel {
    SuggestionRepository suggestionRepository;
    PersonRepository personRepository;

    private LiveData<Suggestion[]> suggestionArrayObservable;

    public ProfileViewModel(){
        suggestionRepository = new SuggestionRepository();
        personRepository = PersonRepository.getInstance();
    }

    public LiveData<Suggestion[]> getSuggestions(String nickname){
        suggestionArrayObservable = suggestionRepository.getUserSuggestions(nickname);

        return suggestionArrayObservable;
    }

    public LiveData<Person> getLoggedPerson(){
        return personRepository.getLoggedPerson();
    }

    public LiveData<Person> getPerson(String nickname){
        return personRepository.getPerson(nickname);
    }
}