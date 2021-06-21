package com.example.placeholder.data.model;

import java.util.Date;
import java.util.List;

public class BookSuggestion extends Suggestion{
    private List<String> authors;

    private Date publicationDate;

    private String publisher;

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
