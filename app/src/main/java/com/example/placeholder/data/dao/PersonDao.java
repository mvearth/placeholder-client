package com.example.placeholder.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.placeholder.data.model.Person;

@Dao
public interface PersonDao {
    @Query("SELECT * FROM person WHERE email LIKE :email LIMIT 1")
    Person getByEmail(String email);

    @Query("SELECT * FROM person WHERE nickName LIKE :nickName LIKE LIMIT 1")
    Person getByNickname(String nickName);

    @Insert
    void insertAll(Person... persons);

    @Delete
    void delete(Person person);
}
