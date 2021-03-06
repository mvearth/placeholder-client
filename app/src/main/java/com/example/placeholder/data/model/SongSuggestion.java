package com.example.placeholder.data.model;

import java.util.Date;
import java.util.List;

public class SongSuggestion extends Suggestion{
    private List<String> artists;
    private String album;
    private Date releaseDate;

    public SongSuggestion() {
        suggestionType = SuggestionType.SongSuggestion;
    }

    public List<String> getArtists() {
        return artists;
    }

    public void setArtists(List<String> artists) {
        this.artists = artists;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
}
