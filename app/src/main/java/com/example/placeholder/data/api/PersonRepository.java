package com.example.placeholder.data.api;

import com.example.placeholder.data.util.Result;
import com.example.placeholder.data.model.Person;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class PersonRepository {

    private static volatile PersonRepository instance;

    private PersonDataSource dataSource;

    // If user credentials will be cached in local storage, it is recommended it be encrypted
    // @see https://developer.android.com/training/articles/keystore
    private Person user = null;

    // private constructor : singleton access
    private PersonRepository(PersonDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static PersonRepository getInstance(PersonDataSource dataSource) {
        if (instance == null) {
            instance = new PersonRepository(dataSource);
        }
        return instance;
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    public void logout() {
        user = null;
        dataSource.logout();
    }

    private void setLoggedInUser(Person user) {
        this.user = user;
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }

    public Result<Person> login(String username, String password) {
        // handle login
        Result<Person> result = dataSource.login(username, password);
        if (result instanceof Result.Success) {
            setLoggedInUser(((Result.Success<Person>) result).getData());
        }
        return result;
    }

    public boolean checkEmailExists(String email) {
        return dataSource.checkEmailExists(email);
    }

    public boolean checkNicknameExists(String nickname) {
        return dataSource.checkNicknameExists(nickname);
    }
}