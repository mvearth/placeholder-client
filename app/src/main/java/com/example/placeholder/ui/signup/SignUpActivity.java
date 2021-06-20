package com.example.placeholder.ui.signup;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.placeholder.R;
import com.example.placeholder.data.PersonDataSource;
import com.example.placeholder.data.PersonRepository;
import com.example.placeholder.data.Result;
import com.example.placeholder.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {

    private SignUpViewModel signUpViewModel;
    private ActivitySignUpBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        signUpViewModel = new SignUpViewModel(PersonRepository.getInstance(new PersonDataSource()));

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
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String passwordConfirm = passwordConfirmEditText.getText().toString();

                if (validate(name, email, password, passwordConfirm)) {
                    signUpViewModel.signUp(name, email, password, passwordConfirm);
                }
            }
        });
    }

    private boolean validate(String nickName, String email, String password, String passwordConfirm) {
        Result result = signUpViewModel.validateSignUp(nickName, email, password, passwordConfirm);
        if (result instanceof Result.Error) {
            showSignUpFailed(((Result.Error) result).getErrorString());

            return false;
        }

        return true;
    }

    private void showSignUpFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}