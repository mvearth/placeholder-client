package com.example.placeholder.data.model;

import java.util.Date;

public class MovieSuggestion extends Suggestion{
    private Date releaseDate;

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public SuggestionType getSuggestionType() {
        return SuggestionType.MovieSuggestion;
    }
}
