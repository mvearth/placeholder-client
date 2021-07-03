package com.example.placeholder.ui.post;

import android.util.Patterns;

import androidx.annotation.StringRes;
import androidx.lifecycle.ViewModel;

import com.example.placeholder.R;
import com.example.placeholder.data.model.Suggestion;
import com.example.placeholder.data.model.SuggestionType;
import com.example.placeholder.data.repository.SuggestionRepository;

public class PostViewModel extends ViewModel {
    final private SuggestionRepository suggestionRepository;

    public PostViewModel(){
        suggestionRepository = SuggestionRepository.getInstance();
    }

    public void postSuggestion(Suggestion suggestion){
        suggestionRepository.postSuggestion(suggestion);
    }

    public @StringRes
    Integer validate(String title, SuggestionType suggestionType) {
        @StringRes Integer returnString = 0;

        if (title == null || title.trim().isEmpty())
            returnString = R.string.title_empty;

        if(suggestionType == null)
            returnString = R.string.suggestion_type_empty;

        return returnString;
    }
}