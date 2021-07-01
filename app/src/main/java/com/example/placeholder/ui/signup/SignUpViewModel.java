package com.example.placeholder.ui.signup;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import android.util.Patterns;

import com.example.placeholder.R;
import com.example.placeholder.data.repository.PersonRepository;
import com.example.placeholder.data.model.Person;

public class SignUpViewModel extends ViewModel {

    final private PersonRepository personRepository;
    final private MutableLiveData<Person> mutablePerson = new MutableLiveData<>();

    SignUpViewModel(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public LiveData<Integer> signUp(String name, String nickname, String email, String password) {
       return personRepository.signUp(name, nickname, email, password);
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

    public @StringRes Integer validateSignUp(String nickName, String email, String password, String passwordConfirm) {
        @StringRes Integer returnString = 0;

        if (!validatePasswords(password, passwordConfirm)){
            returnString = R.string.invalid_password;
        }

        if (!validateEmail(email)){
            returnString = R.string.invalid_email;
        }

        if (!validateNickname(nickName)){
            returnString = R.string.invalid_nickname;
        }

        return returnString;
    }
}