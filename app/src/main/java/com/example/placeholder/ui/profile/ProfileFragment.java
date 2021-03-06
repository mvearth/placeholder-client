package com.example.placeholder.ui.profile;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.placeholder.R;
import com.example.placeholder.data.model.Person;
import com.example.placeholder.data.model.Suggestion;
import com.example.placeholder.databinding.ActivityLoginBinding;
import com.example.placeholder.databinding.ProfileFragmentBinding;
import com.example.placeholder.ui.adapters.SuggestionAdapter;
import com.example.placeholder.ui.home.HomeViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Objects;

public class ProfileFragment extends Fragment {

    private ProfileViewModel mViewModel;
    private ProfileFragmentBinding binding;
    private SuggestionAdapter suggestionAdapter;
    private ImageView personIcon;
    private TextView personNameTextView;
    private TextView personNicknameTextView;
    private TextView followersTextView;
    private TextView followingTextView;

    private ActivityResultLauncher<String> getGalleryImage;
    private ActivityResultLauncher<String> requestPermissionResultLauncher;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);

        getGalleryImage = registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
            if (uri != null) {
                personIcon.setImageURI(uri);

                Drawable drawable = personIcon.getDrawable();
                Bitmap image = null;

                if (drawable != null) {
                    image = ((BitmapDrawable) drawable).getBitmap();
                    mViewModel.updatePersonIcon(image);
                }
            }
        });

        requestPermissionResultLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
            if (isGranted) {
                this.changeProfileIcon();
            } else {
                Toast.makeText(getContext(), R.string.storage_permission_not_granted, Toast.LENGTH_SHORT).show();
            }
        });

        personIcon = (ImageButton) view.findViewById(R.id.imgButton_personIcon);
        personNameTextView = (TextView) view.findViewById(R.id.textView_personName);
        personNicknameTextView = (TextView) view.findViewById(R.id.textView_personNickname);
        followersTextView = (TextView) view.findViewById(R.id.textView_followers);
        followingTextView = (TextView) view.findViewById(R.id.textView_following);

        personIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermissionResultLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);
            }
        });

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
        viewModel.getSuggestions(mViewModel.getLoggedPerson().getValue().getEmail()).observe(getViewLifecycleOwner(), new Observer<LinkedList<Suggestion>>() {
            @Override
            public void onChanged(@Nullable LinkedList<Suggestion> suggestions) {
                if (suggestions != null) {

                    for (Suggestion sugg : suggestions)
                        sugg.setPerson(mViewModel.getLoggedPerson().getValue());

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

        viewModel.getLoggedPerson().observe(getViewLifecycleOwner(), new Observer<Person>() {
            @Override
            public void onChanged(@Nullable Person person) {
                if (person != null) {
                    Bitmap personImageBitmap = person.getIcon();

                    if (personImageBitmap == null)
                        personIcon.setImageResource(R.drawable.ic_person_24dp);
                    else
                        personIcon.setImageBitmap(personImageBitmap);

                    personNameTextView.setText(person.getName());
                    personNicknameTextView.setText(person.getNickname());
                    followersTextView.setText(Long.toString(person.getFollowersCount()));
                    followingTextView.setText(Long.toString(person.getFollowingsCount()));
                }
            }
        });
    }

    private void changeProfileIcon() {
        getGalleryImage.launch("image/*");
    }
}