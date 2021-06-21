package com.example.placeholder.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.util.Patterns;

import com.example.placeholder.data.api.PersonRepository;
import com.example.placeholder.data.util.Result;
import com.example.placeholder.data.model.Person;

public class LoginViewModel extends ViewModel {

    private PersonRepository personRepository;
    private MutableLiveData<Person> mutablePerson = new MutableLiveData<>();

    LoginViewModel(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void login(String username, String password) {
        // can be launched in a separate asynchronous job
        Result<Person> result = personRepository.login(username, password);

        if (result instanceof Result.Success) {
            mutablePerson.setValue(((Result.Success<Person>) result).getData());
        }
        else {
            mutablePerson.setValue(null);
        }
    }

    public LiveData<Person> getPerson(){
        return mutablePerson;
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }
}