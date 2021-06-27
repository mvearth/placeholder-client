package com.example.placeholder.ui.home;

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
import com.example.placeholder.data.model.Suggestion;
import com.example.placeholder.ui.adapters.SuggestionAdapter;

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
        viewModel.getSuggestions("").observe(getViewLifecycleOwner(), new Observer<Suggestion[]>() {
            @Override
            public void onChanged(@Nullable Suggestion[] suggestions) {
                if (suggestions != null) {
                    suggestionAdapter.setLocalDataSet(suggestions);
                }
            }
        });
    }
}