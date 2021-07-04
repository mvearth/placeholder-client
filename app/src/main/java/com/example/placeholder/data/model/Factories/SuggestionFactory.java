package com.example.placeholder.data.model.Factories;

import com.example.placeholder.data.model.BookSuggestion;
import com.example.placeholder.data.model.MovieSuggestion;
import com.example.placeholder.data.model.OtherSuggestion;
import com.example.placeholder.data.model.SongSuggestion;
import com.example.placeholder.data.model.Suggestion;
import com.example.placeholder.data.model.SuggestionType;

public class SuggestionFactory {

    public static Suggestion createSuggestion(SuggestionType suggestionType){
        switch (suggestionType){
            case BookSuggestion:
                return new BookSuggestion();
            case SongSuggestion:
                return new SongSuggestion();
            case MovieSuggestion:
                return new MovieSuggestion();
            default:
                return new OtherSuggestion();
        }
    }
}
