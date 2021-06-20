package com.example.placeholder.data.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class PersonWithSuggestions {
    @Embedded
    public Person user;
    @Relation(
            parentColumn = "personId",
            entityColumn = "persionCreatorId"
    )
    public List<Suggestion> suggestions;
}
