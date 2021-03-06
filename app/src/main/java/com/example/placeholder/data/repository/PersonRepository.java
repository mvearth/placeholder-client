package com.example.placeholder.data.repository;

import android.graphics.Bitmap;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.placeholder.data.api.ApiClient;
import com.example.placeholder.data.api.services.PersonService;
import com.example.placeholder.data.model.Follow;
import com.example.placeholder.data.model.Login;
import com.example.placeholder.data.model.Person;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.LinkedList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonRepository {

    final private PersonService personService;

    private static volatile PersonRepository instance;

    private MutableLiveData<Person> loggedPerson = new MutableLiveData<>();
    private MutableLiveData<LinkedList<Person>> loggedPersonFollowings = new MutableLiveData<>();
    private MutableLiveData<LinkedList<Person>> loggedPersonFollowers = new MutableLiveData<>();

    private MutableLiveData<LinkedList<Person>> searchedPeople = new MutableLiveData<>();

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
        return loggedPerson != null;
    }

    public void logout() {
        loggedPerson = null;
        //dataSource.logout();
    }

    private void setLoggedInUser(Person person) {
        this.loggedPerson.setValue(person);
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }

    public void login(String email, String password) {
        Call<Person> call = personService.login(new Login(email, password));
        call.enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                if (response.isSuccessful()) {
                    loggedPerson.setValue(response.body());
                    return;
                }

                loggedPerson.setValue(null);
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void searchPeople(String value) {
        Call<Person[]> call = personService.searchPeople(value);
        call.enqueue(new Callback<Person[]>() {
            @Override
            public void onResponse(Call<Person[]> call, Response<Person[]> response) {
                if (response.isSuccessful()) {
                    searchedPeople.setValue(new LinkedList<Person>(Arrays.asList(response.body())));
                    return;
                }

                searchedPeople.setValue(null);
            }

            @Override
            public void onFailure(Call<Person[]> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }


    public boolean checkEmailExists(String email) {
        return true;
    }

    public boolean checkNicknameExists(String nickname) {
        return true;
    }

    public LiveData<Person> getLoggedPerson() {
        return this.loggedPerson;
    }

    public LiveData<LinkedList<Person>> getSearchedPeople() {
        return this.searchedPeople;
    }

    public LiveData<LinkedList<Person>> getLoggedPersonFollowings() {
        return this.loggedPersonFollowings;
    }

    public LiveData<Integer> signUp(String name, String nickname, String email, String password) {
        Person person = new Person();
        person.setName(name);
        person.setNickname(nickname);
        person.setEmail(email);
        person.setPassword(password);

        final MutableLiveData<Integer> result = new MutableLiveData<Integer>();

        Call<Object> call = personService.signUp(person);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                result.setValue(response.code());
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });

        return result;
    }

    public void getFollowsInfo() {
        if (loggedPerson.getValue() == null
                || loggedPerson.getValue().getEmail() == null)
            return;

        Call<Person[]> followersCall = personService.getFollowers(loggedPerson.getValue().getEmail());
        followersCall.enqueue(new Callback<Person[]>() {
            @Override
            public void onResponse(Call<Person[]> call, Response<Person[]> response) {
                if (response.isSuccessful()) {
                    loggedPerson.getValue().setFollowers(new LinkedList<Person>(Arrays.asList(response.body())));
                    loggedPersonFollowers.setValue(loggedPerson.getValue().getFollowers());
                    return;
                }

                loggedPerson.getValue().setFollowers(null);
            }

            @Override
            public void onFailure(Call<Person[]> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });

        Call<Person[]> followingsCall = personService.getFollowings(loggedPerson.getValue().getEmail());
        followingsCall.enqueue(new Callback<Person[]>() {
            @Override
            public void onResponse(Call<Person[]> call, Response<Person[]> response) {
                if (response.isSuccessful()) {
                    loggedPerson.getValue().setFollowings(new LinkedList<Person>(Arrays.asList(response.body())));
                    loggedPersonFollowers.setValue(loggedPerson.getValue().getFollowings());
                    return;
                }

                loggedPerson.getValue().setFollowings(null);
            }

            @Override
            public void onFailure(Call<Person[]> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void updateFollowInfo(Person person) {
        if (this.loggedPerson.getValue().isFollowing((person))) {
            this.unfollowPerson(person);
        } else {
            this.followPerson(person);
        }
    }

    public void followPerson(Person person) {
        Follow follow = new Follow(person.getEmail(), this.loggedPerson.getValue().getEmail());

        Call<Object> call = personService.postFollowing(follow);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.isSuccessful()) {
                    loggedPerson.getValue().followPerson(person);
                    loggedPersonFollowings.setValue(loggedPerson.getValue().getFollowings());
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void unfollowPerson(Person person) {
        Follow follow = new Follow(person.getEmail(), this.loggedPerson.getValue().getEmail());

        Call<Object> call = personService.delFollowing(follow);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.isSuccessful()) {
                    loggedPerson.getValue().unfollowPerson(person);
                    loggedPersonFollowings.setValue(loggedPerson.getValue().getFollowings());
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void updatePersonIcon(Bitmap image) {
        Person person = loggedPerson.getValue();
        person.setIcon(image);

        Call<Person> call = personService.updatePerson(person.getId(), person);
        call.enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                if (response.isSuccessful()) {
                    loggedPerson.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}