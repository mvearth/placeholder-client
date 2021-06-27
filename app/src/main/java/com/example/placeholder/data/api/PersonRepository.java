package com.example.placeholder.data.api;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.placeholder.data.model.Person;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class PersonRepository {

    private static volatile PersonRepository instance;

    // If user credentials will be cached in local storage, it is recommended it be encrypted
    // @see https://developer.android.com/training/articles/keystore
    private MutableLiveData<Person> person = new MutableLiveData<>();

    // private constructor : singleton access
    private PersonRepository() {}

    public static PersonRepository getInstance() {
        if (instance == null) {
            instance = new PersonRepository();
        }
        return instance;
    }

    public boolean isLoggedIn() {
        return person != null;
    }

    public void logout() {
        person = null;
        //dataSource.logout();
    }

    private void setLoggedInUser(Person person) {
        this.person.setValue(person);
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }

    public LiveData<Person> login(String personName, String password) {
        // handle login
       // Result<Person> result = dataSource.login(username, password);
        //if (result instanceof Result.Success) {
       //     setLoggedInUser(((Result.Success<Person>) result).getData());
        //}
        //return result;

        this.person.setValue(this.getPerson("").getValue());

        return this.person;
    }

    public boolean checkEmailExists(String email) {
        //return dataSource.checkEmailExists(email);
        return true;
    }

    public boolean checkNicknameExists(String nickname) {
        //return dataSource.checkNicknameExists(nickname);
        return true;
    }

    public LiveData<Person> getLoggedPerson(){
        return this.person;
    }

    public LiveData<Person> getPerson(String nickname){
        MutableLiveData<Person> liveperson = new MutableLiveData<>();
        Person person = new Person();
        person.setName("putaria");
        person.setNickname("sexo kk");

        liveperson.setValue(person);

        return liveperson;
    }
}