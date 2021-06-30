package com.example.placeholder.ui.profile;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.placeholder.R;
import com.example.placeholder.data.model.Person;
import com.example.placeholder.data.model.Suggestion;
import com.example.placeholder.databinding.ActivityLoginBinding;
import com.example.placeholder.databinding.ProfileFragmentBinding;
import com.example.placeholder.ui.adapters.SuggestionAdapter;
import com.example.placeholder.ui.home.HomeViewModel;

public class ProfileFragment extends Fragment {

    private ProfileViewModel mViewModel;
    private ProfileFragmentBinding binding;
    private SuggestionAdapter suggestionAdapter;
    private ImageView personIcon;
    private TextView personNameTextView;
    private TextView personNicknameTextView;
    private TextView followersTextView;
    private TextView followingTextView;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);

        personIcon = (ImageView) view.findViewById(R.id.imgView_personIcon);
        personNameTextView = (TextView) view.findViewById(R.id.textView_personName);
        personNicknameTextView = (TextView) view.findViewById(R.id.textView_personNickname);
        followersTextView = (TextView) view.findViewById(R.id.textView_followers);
        followingTextView = (TextView) view.findViewById(R.id.textView_following);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);

        suggestionAdapter = new SuggestionAdapter();
        RecyclerView suggestionsRecyclerView = getView().findViewById(R.id.suggestions_view);
        suggestionsRecyclerView.setLayoutManager(new LinearLayoutManager(getView().getContext()));
        suggestionsRecyclerView.setHasFixedSize(true);
        suggestionsRecyclerView.setAdapter(suggestionAdapter);

        observeViewModel(mViewModel);
    }

    private void observeViewModel(ProfileViewModel viewModel) {
        viewModel.getSuggestions("").observe(getViewLifecycleOwner(), new Observer<Suggestion[]>() {
            @Override
            public void onChanged(@Nullable Suggestion[] suggestions) {
                if (suggestions != null) {
                    suggestionAdapter.setLocalDataSet(suggestions);
                }
            }
        });

        viewModel.getLoggedPerson().observe(getViewLifecycleOwner(), new Observer<Person>() {
            @Override
            public void onChanged(@Nullable Person person) {
                if (person != null) {
                    personIcon.setImageBitmap(person.getIcon());
                    personNameTextView.setText(person.getName());
                    personNicknameTextView.setText(person.getNickname());
                    followersTextView.setText(Long.toString(person.getFollowers()));
                    followingTextView.setText(Long.toString(person.getFollowing()));
                }
            }
        });
    }
}