package com.example.placeholder.ui.suggestion;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.placeholder.data.repository.SuggestionRepository;
import com.example.placeholder.data.model.Suggestion;
import com.example.placeholder.data.model.SuggestionType;

public class SuggestionViewModel extends ViewModel {
    SuggestionRepository suggestionRepository;

    public SuggestionViewModel(){
        suggestionRepository = new SuggestionRepository();
    }

    public LiveData<Suggestion> getRandomSuggestion() {
        return suggestionRepository.getRandomSuggestion();
    }

    public void updateRandomSuggestion(SuggestionType suggestionType) {
        suggestionRepository.updateRandomSuggestion(suggestionType);
    }
}