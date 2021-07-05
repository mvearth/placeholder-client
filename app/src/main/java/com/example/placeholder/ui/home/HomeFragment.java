package com.example.placeholder.ui.home;

import androidx.lifecycle.LiveData;
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

import com.example.placeholder.R;
import com.example.placeholder.data.model.Person;
import com.example.placeholder.data.model.Suggestion;
import com.example.placeholder.ui.adapters.SuggestionAdapter;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Objects;

public class HomeFragment extends Fragment {

    private HomeViewModel mViewModel;
    private SuggestionAdapter suggestionAdapter;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.home_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        suggestionAdapter = new SuggestionAdapter();
        RecyclerView suggestionsRecyclerView = getView().findViewById(R.id.suggestions_view);
        suggestionsRecyclerView.setLayoutManager(new LinearLayoutManager(getView().getContext()));
        suggestionsRecyclerView.setHasFixedSize(true);
        suggestionsRecyclerView.setAdapter(suggestionAdapter);

        observeViewModel(mViewModel);
    }

    private void observeViewModel(HomeViewModel viewModel) {
        viewModel.getFollowingsSuggestions().observe(getViewLifecycleOwner(), new Observer<LinkedList<Suggestion>>() {
            @Override
            public void onChanged(@Nullable LinkedList<Suggestion> suggestions) {
                if (suggestions != null) {

                    for (Suggestion sugg : suggestions)
                        sugg.setPerson(mViewModel.getLoggedPerson().getValue().getFollowingPerson(sugg.getPersonEmail()));

                    Collections.sort(suggestions, new Comparator<Suggestion>() {
                        @Override
                        public int compare(Suggestion o1, Suggestion o2) {
                            return o1.getSuggestionDate().compareTo(o2.getSuggestionDate()) * -1;
                        }
                    });

                    suggestionAdapter.setLocalDataSet(suggestions);
                }
            }
        });
    }
}
