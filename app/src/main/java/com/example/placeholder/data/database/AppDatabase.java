package com.example.placeholder.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.placeholder.data.dao.PersonDao;
import com.example.placeholder.data.dao.SuggestionDao;
import com.example.placeholder.data.model.Person;

@Database(entities = {Person.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PersonDao personDao();
    public abstract SuggestionDao suggestionDao();
}
