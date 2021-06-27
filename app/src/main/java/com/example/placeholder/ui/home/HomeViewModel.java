package com.example.placeholder.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.placeholder.data.api.SuggestionRepository;
import com.example.placeholder.data.model.Feed;
import com.example.placeholder.data.model.Suggestion;

public class HomeViewModel extends ViewModel {
    SuggestionRepository suggestionRepository;

    private LiveData<Suggestion[]> suggestionArrayObservable;

    public HomeViewModel(){
        suggestionRepository = new SuggestionRepository();
    }

    public LiveData<Suggestion[]> getSuggestions(String nickname){
        suggestionArrayObservable = suggestionRepository.getFollowingSuggestions(nickname);

        return suggestionArrayObservable;
    }
}