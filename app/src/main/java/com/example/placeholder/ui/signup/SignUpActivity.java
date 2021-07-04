package com.example.placeholder.ui.signup;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.placeholder.R;
import com.example.placeholder.data.model.Person;
import com.example.placeholder.data.repository.PersonRepository;
import com.example.placeholder.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {

    private SignUpViewModel signUpViewModel;
    private ActivitySignUpBinding binding;
    private MutableLiveData<Integer> signupResult = new MutableLiveData<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        signUpViewModel = new SignUpViewModel(PersonRepository.getInstance());

        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final EditText nameEditText = binding.name;
        final EditText nicknameEditText = binding.nickname;
        final EditText emailEditText = binding.email;
        final EditText passwordEditText = binding.password;
        final EditText passwordConfirmEditText = binding.passwordConfirm;
        final Button signUpButton = binding.signUpButton;

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                String nickname = nicknameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String passwordConfirm = passwordConfirmEditText.getText().toString();

                if (validate(name, email, password, passwordConfirm)) {
                    signupResult.setValue(signUpViewModel.signUp(name, nickname, email, password));
                }
            }
        });

        signupResult.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer signupResult) {
                switch (signupResult) {
                    case 200: {
                        showMessage(R.string.signup_successful);
                        break;
                    }
                    case 409: {
                        showMessage(R.string.user_already_exists);
                        break;
                    }
                    default: {
                        showMessage(R.string.cannot_signup);
                        break;
                    }
                }
            }
        });
    }

    private boolean validate(String nickName, String email, String password, String passwordConfirm) {
        @StringRes Integer result = signUpViewModel.validateSignUp(nickName, email, password, passwordConfirm);
        if (result != 0) {
            showMessage(result);
            return false;
        }

        return true;
    }

    private void showMessage(@StringRes Integer messageString) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), messageString, Toast.LENGTH_SHORT).show();
            }
        });
    }
}