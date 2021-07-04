package com.example.placeholder.data.model;

import android.graphics.Bitmap;
import android.media.Image;

import com.example.placeholder.ui.suggestion.SuggestionFragment;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public abstract class Suggestion {
    private int id;

    private String title;

    private String description;

    @Expose
    private byte[][] images;

    private Date suggestionDate;

    @Expose(serialize = false, deserialize = false)
    private Person person;

    private String email;

    protected SuggestionType suggestionType;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[][] getImages() {
        return images;
    }

    public void setImages(byte[][] images) {
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

    public String getPersonEmail() {
        return email;
    }

    public void setPersonEmail(String email) {
        this.email = email;
    }

    public Date getSuggestionDate() {
        return suggestionDate;
    }

    public void setSuggestionDate(Date suggestionDate) {
        this.suggestionDate = suggestionDate;
    }

    public SuggestionType getSuggestionType() {
        return this.suggestionType;
    }
}
