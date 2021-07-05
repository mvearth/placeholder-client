package com.example.placeholder.ui.post;

import android.graphics.Bitmap;
import android.util.Patterns;

import androidx.annotation.StringRes;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.placeholder.R;
import com.example.placeholder.data.model.Factories.SuggestionFactory;
import com.example.placeholder.data.model.Helpers.BitmapHelper;
import com.example.placeholder.data.model.Suggestion;
import com.example.placeholder.data.model.SuggestionType;
import com.example.placeholder.data.repository.PersonRepository;
import com.example.placeholder.data.repository.SuggestionRepository;

public class PostViewModel extends ViewModel {
    final private SuggestionRepository suggestionRepository;
    final private PersonRepository personRepository;

    public PostViewModel() {
        suggestionRepository = SuggestionRepository.getInstance();
        personRepository = PersonRepository.getInstance();
    }

    public LiveData<Integer> postSuggestion(String title, String description, SuggestionType suggestionType, Bitmap bitmap) {
        Suggestion suggestion = SuggestionFactory.createSuggestion(suggestionType);
        suggestion.setPerson(personRepository.getLoggedPerson().getValue());
        suggestion.setPersonEmail(suggestion.getPerson().getEmail());
        suggestion.setTitle(title);
        suggestion.setDescription(description);

        if (bitmap != null)
            suggestion.setBase64Image(BitmapHelper.convertToBase64(bitmap));

        return suggestionRepository.postSuggestion(suggestion);
    }

    public @StringRes
    Integer validate(String title, SuggestionType suggestionType) {
        @StringRes Integer returnString = 0;

        if (title == null || title.trim().isEmpty())
            returnString = R.string.title_empty;

        if (suggestionType == null)
            returnString = R.string.suggestion_type_empty;

        return returnString;
    }
}