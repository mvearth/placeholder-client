package com.example.placeholder.data.model;

import java.util.List;

public class Feed {
    private Person person;

    private List<Suggestion> suggestions;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<Suggestion> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(List<Suggestion> suggestions) {
        this.suggestions = suggestions;
    }
}
