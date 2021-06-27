package com.example.placeholder.ui.suggestion;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.placeholder.R;
import com.example.placeholder.data.model.Person;
import com.example.placeholder.data.model.Suggestion;
import com.example.placeholder.data.model.SuggestionType;
import com.example.placeholder.ui.profile.ProfileViewModel;

public class SuggestionFragment extends Fragment {

    private SuggestionViewModel mViewModel;
    private ImageButton songImageButton;
    private ImageButton movieImageButton;
    private ImageButton bookImageButton;
    private ImageButton otherImageButton;
    private ImageButton randomImageButton;
    private TextView suggestionType;
    private TextView suggestionTitle;
    private ImageView suggestionImage;

    public static SuggestionFragment newInstance() {
        return new SuggestionFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.suggestion_fragment, container, false);

        songImageButton = (ImageButton) view.findViewById(R.id.imgButton_song);
        movieImageButton = (ImageButton) view.findViewById(R.id.imgButton_movie);
        bookImageButton = (ImageButton) view.findViewById(R.id.imgButton_book);
        otherImageButton = (ImageButton) view.findViewById(R.id.imgButton_other_and_more);
        randomImageButton = (ImageButton) view.findViewById(R.id.imgButton_random);
        suggestionType = (TextView) view.findViewById(R.id.txtView_suggestionType);
        suggestionTitle = (TextView) view.findViewById(R.id.txtView_suggestionTitle);
        suggestionImage = (ImageView) view.findViewById(R.id.imgView_suggestionFirstImage);

        songImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.updateRandomSuggestion(SuggestionType.SongSuggestion);
            }
        });

        movieImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.updateRandomSuggestion(SuggestionType.MovieSuggestion);
            }
        });

        bookImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.updateRandomSuggestion(SuggestionType.BookSuggestion);
            }
        });

        otherImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.updateRandomSuggestion(SuggestionType.OtherSuggestion);
            }
        });

        randomImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.updateRandomSuggestion(SuggestionType.RandomSuggestion);
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SuggestionViewModel.class);

        observeViewModel(mViewModel);
    }

    private void observeViewModel(SuggestionViewModel viewModel) {
        viewModel.getRandomSuggestion().observe(getViewLifecycleOwner(), new Observer<Suggestion>() {
            @Override
            public void onChanged(@Nullable Suggestion suggestion) {
                if (suggestion != null) {
                    suggestionType.setText(suggestion.getSuggestionType().toString());
                    suggestionTitle.setText(suggestion.getTitle());
                    //suggestionImage.setImageBitmap(suggestion.getImages().get(0));
                }
            }
        });
    }
}