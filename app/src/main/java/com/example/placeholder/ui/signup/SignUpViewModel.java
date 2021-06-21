package com.example.placeholder.ui.signup;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.util.Patterns;

import com.example.placeholder.R;
import com.example.placeholder.data.api.PersonRepository;
import com.example.placeholder.data.util.Result;
import com.example.placeholder.data.model.Person;

public class SignUpViewModel extends ViewModel {

    private PersonRepository personRepository;
    private MutableLiveData<Person> mutablePerson = new MutableLiveData<>();

    SignUpViewModel(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void signUp(String name, String nickName, String email, String password) {
        // can be launched in a separate asynchronous job
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

    private boolean validatePasswords(String password, String passwordConfirm) {
        return password != null
                && password.trim().length() > 5
                && password.equals(passwordConfirm);
    }

    private boolean validateEmail(String email) {
        return personRepository.checkEmailExists(email);
    }

    private boolean validateNickname(String nickname) {
        return personRepository.checkNicknameExists(nickname);
    }

    public Result validateSignUp(String nickName, String email, String password, String passwordConfirm) {
        if (!validatePasswords(password, passwordConfirm)){
            return new Result.Error(R.string.invalid_password);
        }

        if (!validateEmail(email)){
            return new Result.Error(R.string.invalid_email);
        }

        if (!validateNickname(nickName)){
            return new Result.Error(R.string.invalid_nickname);
        }

        return new Result.Success<>(true);
    }
}