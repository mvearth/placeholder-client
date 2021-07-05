package com.example.placeholder.ui.login;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import android.util.Patterns;

import com.example.placeholder.data.model.Suggestion;
import com.example.placeholder.data.repository.PersonRepository;
import com.example.placeholder.data.model.Person;

public class LoginViewModel extends ViewModel {

    final private PersonRepository personRepository;

    LoginViewModel() {
        this.personRepository = PersonRepository.getInstance();
    }

    public void login(String username, String password) {
        personRepository.login(username, password);
    }

    public void getFollowsInfo(){
        personRepository.getFollowsInfo();
    }

    public LiveData<Person> getLoggedPerson(){
        return personRepository.getLoggedPerson();
    }

    private boolean isUserNameValid(String email) {
        if (email == null) {
            return false;
        }
        if (email.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches();
        } else {
            return !email.trim().isEmpty();
        }
    }

    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }
}