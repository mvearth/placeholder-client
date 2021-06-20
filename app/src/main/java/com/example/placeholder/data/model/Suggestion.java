package com.example.placeholder.data.model;

import android.media.Image;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.List;

public abstract class Suggestion {
    @PrimaryKey
    private int suggestionId;

    private int persionCreatorId;

    private String title;

    private String description;

    private List<Image> images;

    @ColumnInfo(name = "suggestion_date")
    private Date suggestionDate;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public Date getPublishDate() {
        return suggestionDate;
    }

    public void setPublishDate(Date suggestionDate) {
        this.suggestionDate = suggestionDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getSuggestionDate() {
        return suggestionDate;
    }

    public void setSuggestionDate(Date suggestionDate) {
        this.suggestionDate = suggestionDate;
    }
}
