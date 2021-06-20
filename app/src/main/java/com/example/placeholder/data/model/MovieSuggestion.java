package com.example.placeholder.data.model;

import androidx.room.Entity;

import java.util.Date;

@Entity(tableName = "moovie_suggestion")
public class MovieSuggestion extends Suggestion{
    private Date releaseDate;

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
}
