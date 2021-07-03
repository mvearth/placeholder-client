package com.example.placeholder.ui.search;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.placeholder.R;
import com.example.placeholder.data.model.Person;
import com.example.placeholder.data.model.Suggestion;
import com.example.placeholder.databinding.ActivityLoginBinding;
import com.example.placeholder.ui.adapters.SearchUserAdapter;
import com.example.placeholder.ui.adapters.SuggestionAdapter;
import com.example.placeholder.ui.home.HomeViewModel;

import java.util.Objects;

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
        RecyclerView searchUserRecyclerView = getView().findViewById(R.id.search_user_view);
        searchUserRecyclerView.setLayoutManager(new LinearLayoutManager(getView().getContext()));
        searchUserRecyclerView.setHasFixedSize(true);
        searchUserRecyclerView.setAdapter(searchUserAdapter);

        EditText searchString = getView().findViewById(R.id.search_input);
        searchString.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null
                        && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER))
                            || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    mViewModel.searchPeople(searchString.getText().toString());
                }

                return false;
            }
        });

        observeViewModel(mViewModel);
    }

    private void observeViewModel(SearchViewModel viewModel) {
        viewModel.getSearchedPeople().observe(getViewLifecycleOwner(), new Observer<Person[]>() {
            @Override
            public void onChanged(@Nullable Person[] people) {
                if (people != null) {
                    searchUserAdapter.setLocalDataSet(people, mViewModel);
                }
            }
        });
    }
}