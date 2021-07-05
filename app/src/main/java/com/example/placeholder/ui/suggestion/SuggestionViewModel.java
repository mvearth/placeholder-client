package com.example.placeholder.ui.suggestion;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.placeholder.data.repository.PersonRepository;
import com.example.placeholder.data.repository.SuggestionRepository;
import com.example.placeholder.data.model.Suggestion;
import com.example.placeholder.data.model.SuggestionType;

public class SuggestionViewModel extends ViewModel {
    SuggestionRepository suggestionRepository;
    PersonRepository personRepository;

    public SuggestionViewModel(){
        suggestionRepository = SuggestionRepository.getInstance();
        personRepository = PersonRepository.getInstance();
    }

    public LiveData<Suggestion> getRandomSuggestion() {
        return suggestionRepository.getRandomSuggestion();
    }

    public void updateRandomSuggestion(SuggestionType suggestionType) {
        suggestionRepository.updateRandomSuggestion(personRepository.getLoggedPerson().getValue().getEmail(), suggestionType);
    }
}