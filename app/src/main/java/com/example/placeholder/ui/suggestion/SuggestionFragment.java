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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.placeholder.R;
import com.example.placeholder.data.model.Helpers.BitmapHelper;
import com.example.placeholder.data.model.Person;
import com.example.placeholder.data.model.Suggestion;
import com.example.placeholder.data.model.SuggestionType;
import com.example.placeholder.ui.profile.ProfileViewModel;

public class SuggestionFragment extends Fragment {

    private SuggestionViewModel mViewModel;
    private RadioGroup suggestionRadioGroup;
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

        suggestionRadioGroup = (RadioGroup) view.findViewById(R.id.radioButton_selectedCategory);
        suggestionType = (TextView) view.findViewById(R.id.txtView_suggestionType);
        suggestionTitle = (TextView) view.findViewById(R.id.txtView_suggestionTitle);
        suggestionImage = (ImageView) view.findViewById(R.id.imgView_suggestionFirstImage);

        suggestionRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButton_song:
                        mViewModel.updateRandomSuggestion(SuggestionType.SongSuggestion);
                        break;
                    case R.id.radioButton_movie:
                        mViewModel.updateRandomSuggestion(SuggestionType.MovieSuggestion);
                        break;
                    case R.id.radioButton_book:
                        mViewModel.updateRandomSuggestion(SuggestionType.BookSuggestion);
                        break;
                    case R.id.radioButton_random:
                        mViewModel.updateRandomSuggestion(SuggestionType.RandomSuggestion);
                        break;
                    default:
                        mViewModel.updateRandomSuggestion(SuggestionType.OtherSuggestion);
                        break;
                }
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

                    if (suggestion.getBase64Image() != null){
                        suggestionImage.setImageBitmap(BitmapHelper.convertToBitmap(suggestion.getBase64Image()));
                        suggestionImage.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }
}