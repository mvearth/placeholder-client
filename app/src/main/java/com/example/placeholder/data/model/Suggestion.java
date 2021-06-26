package com.example.placeholder.data.model;

import android.media.Image;

import java.util.Date;
import java.util.List;

public abstract class Suggestion {
    private int suggestionId;

    private int persionCreatorId;

    private String title;

    private String description;

    private List<Image> images;

    private Date suggestionDate;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    private Person person;

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
