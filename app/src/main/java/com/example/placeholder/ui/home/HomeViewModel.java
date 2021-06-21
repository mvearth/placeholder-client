package com.example.placeholder.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.placeholder.data.api.SuggestionRepository;
import com.example.placeholder.data.model.Feed;
import com.example.placeholder.data.model.Suggestion;

public class HomeViewModel extends ViewModel {
    LiveData<Feed> feed;
    SuggestionRepository suggestionRepository;


}