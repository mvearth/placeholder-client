package com.example.placeholder.ui.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.placeholder.R;
import com.example.placeholder.WholeAppActivity;
import com.example.placeholder.data.PersonDataSource;
import com.example.placeholder.data.PersonRepository;
import com.example.placeholder.data.model.Person;
import com.example.placeholder.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private ActivityLoginBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loginViewModel = new LoginViewModel(PersonRepository.getInstance(new PersonDataSource()));

        if (loginViewModel.getPerson() != null) {
            startWholeApp();
            setResult(Activity.RESULT_OK);
            finish();
        }

        final EditText usernameEditText = binding.username;
        final EditText passwordEditText = binding.password;
        final Button signInButton = binding.signIn;
        final Button signUpButton = binding.signUp;
        final ProgressBar loadingProgressBar = binding.loading;

        loginViewModel.getPerson().observe(this, new Observer<Person>() {
            @Override
            public void onChanged(@Nullable Person person) {
                loadingProgressBar.setVisibility(View.GONE);

                if (person == null) {
                    showLoginFailed(R.string.login_failed);
                    return;
                }
                startWholeApp();
                setResult(Activity.RESULT_OK);
                finish();
            }
        });

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                loginViewModel.login(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        });
    }

    private void startWholeApp(){
        Intent intent = new Intent(this, WholeAppActivity.class);
        startActivity(intent);
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}