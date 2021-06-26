package com.example.placeholder.ui.search;

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
import com.example.placeholder.ui.adapters.SearchUserAdapter;
import com.example.placeholder.ui.adapters.SuggestionAdapter;
import com.example.placeholder.ui.home.HomeViewModel;

public class SearchFragment extends Fragment {

    private SearchViewModel mViewModel;
    private SearchUserAdapter searchUserAdapter;

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.search_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SearchViewModel.class);

        searchUserAdapter = new SearchUserAdapter();
        RecyclerView suggestionsRecyclerView = getView().findViewById(R.id.search_user);
        suggestionsRecyclerView.setLayoutManager(new LinearLayoutManager(getView().getContext()));
        suggestionsRecyclerView.setHasFixedSize(true);
        suggestionsRecyclerView.setAdapter(searchUserAdapter);

        observeViewModel(mViewModel);
    }

    private void observeViewModel(SearchViewModel viewModel) {
        // Update the list when the data changes
        viewModel.get().observe(this, new Observer<Suggestion[]>() {
            @Override
            public void onChanged(@Nullable Suggestion[] suggestions) {
                if (suggestions != null) {
                    //…
                    suggestionAdapter.setLocalDataSet(suggestions);
                }
            }
        });
    }
}