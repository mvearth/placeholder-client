package com.example.placeholder.ui.post;

import androidx.annotation.StringRes;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.placeholder.R;
import com.example.placeholder.data.model.SuggestionType;

public class PostFragment extends Fragment {

    private PostViewModel postViewModel;

    private RadioGroup suggestionRadioGroup;
    private EditText titleEditText;
    private EditText descriptionEditText;
    private ImageView suggestionImageView;
    private ImageButton postButton;

    private SuggestionType suggestionType;

    public static PostFragment newInstance() {
        return new PostFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.post_fragment, container, false);

        suggestionRadioGroup = (RadioGroup) view.findViewById(R.id.radioButton_selectedCategory);
        titleEditText = (EditText) view.findViewById(R.id.editText_title);
        descriptionEditText = (EditText) view.findViewById(R.id.editText_description);
        suggestionImageView = (ImageView) view.findViewById(R.id.imgView_selectedImage);
        postButton = (ImageButton) view.findViewById(R.id.imgButton_post);

        suggestionRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButton_song:
                        suggestionType = SuggestionType.SongSuggestion);
                        break;
                    case R.id.radioButton_movie:
                        suggestionType = SuggestionType.MovieSuggestion;
                        break;
                    case R.id.radioButton_book:
                        suggestionType = SuggestionType.BookSuggestion;
                        break;
                    case R.id.radioButton_other_and_more:
                        suggestionType = SuggestionType.OtherSuggestion;
                        break;
                    default:
                        suggestionType = null;
                        break;
                }
            }
        });

        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleEditText.getText().toString();
                String description = descriptionEditText.getText().toString();
                Bitmap image = ((BitmapDrawable) suggestionImageView.getDrawable()).getBitmap();

                if (validate(title, suggestionType)) {
                    signupResult = signUpViewModel.signUp(name, nickname, email, password);
                }
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        postViewModel = new ViewModelProvider(this).get(PostViewModel.class);


    }

    private boolean validate(String title, SuggestionType suggestionType) {
        @StringRes Integer result = postViewModel.validate(title, suggestionType);
        if (result != 0) {
            showMessage(result);
            return false;
        }

        return true;
    }

    private void showMessage(@StringRes Integer messageString) {
        Toast.makeText(getContext(), messageString, Toast.LENGTH_SHORT).show();
    }
}