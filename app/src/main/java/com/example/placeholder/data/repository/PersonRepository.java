package com.example.placeholder.data.repository;

import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.placeholder.data.api.ApiClient;
import com.example.placeholder.data.api.services.PersonService;
import com.example.placeholder.data.model.Person;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonRepository {

    final PersonService personService;

    private static volatile PersonRepository instance;

    private MutableLiveData<Person> person = new MutableLiveData<>();

    // private constructor : singleton access
    private PersonRepository() {
        personService = ApiClient.getClient().create(PersonService.class);
    }

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
        this.person.setValue(this.getPerson("").getValue());

        return this.person;
    }

    public boolean checkEmailExists(String email) {
        return true;
    }

    public boolean checkNicknameExists(String nickname) {
        return true;
    }

    public LiveData<Person> getLoggedPerson(){
        return this.person;
    }

    public LiveData<Person> getPerson(String nickname){
        MutableLiveData<Person> liveperson = new MutableLiveData<>();
        Person person2 = new Person();
        person2.setNickname("subaru");
        person2.setName("Subaru loves Emilia");
        person2.setFollowing(3);
        person2.setFollowers(894654);
        person2.setIcon(BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().toString() + "/Download" + "/2.jpg"));

        liveperson.setValue(person2);

        return liveperson;
    }

    public LiveData<Integer> signUp(String name, String nickname, String email, String password) {
        Person person = new Person();
        person.setName(name);
        person.setNickname(nickname);
        person.setEmail(email);
        person.setPassword(password);

        final MutableLiveData<Integer> result = new MutableLiveData<Integer>();

        Call<Person> call = personService.signUp(person);
        call.enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                result.setValue(response.code());
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });

        return result;
    }
}