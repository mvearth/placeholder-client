package com.example.placeholder.data;

import com.example.placeholder.data.model.Person;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class PersonDataSource {

    public Result<Person> login(String username, String password) {

        try {
            return new Result.Success<>(new Person());
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}