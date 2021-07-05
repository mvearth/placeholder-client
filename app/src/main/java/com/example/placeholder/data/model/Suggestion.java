package com.example.placeholder.data.model;

import android.graphics.Bitmap;
import android.media.Image;

import com.example.placeholder.ui.suggestion.SuggestionFragment;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.DateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;

public abstract class Suggestion {
    private int id;

    @Expose
    private String title;

    @Expose
    private String description;

    @SerializedName("image")
    @Expose
    private String base64Image;

    @SerializedName("created_at")
    @Expose
    private String suggestionDate;

    private Person person;

    @Expose
    private String email;

    @SerializedName("suggestion_type")
    @Expose
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

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
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

    public String getSuggestionDate() {
        return suggestionDate;
    }

    public void setSuggestionDate(String suggestionDate) {
        this.suggestionDate = suggestionDate;
    }

    public SuggestionType getSuggestionType() {
        return this.suggestionType;
    }
}
