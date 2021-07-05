package com.example.placeholder.data.model;

import com.google.gson.annotations.SerializedName;

public enum SuggestionType {
    @SerializedName("BOOK")
    BookSuggestion,
    @SerializedName("SONG")
    SongSuggestion,
    @SerializedName("MOVIE_SERIE")
    MovieSuggestion,
    @SerializedName("OTHER")
    OtherSuggestion,
    RandomSuggestion;

    @Override
    public String toString() {
        switch (this) {
            case BookSuggestion:
                return "BOOK";
            case SongSuggestion:
                return "SONG";
            case MovieSuggestion:
                return "MOVIE_SERIE";
            default:
                return "OTHER";
        }
    }
}
