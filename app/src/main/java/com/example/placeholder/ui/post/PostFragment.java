package com.example.placeholder.ui.post;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.StringRes;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
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
    private ImageButton addImageButton;
    private ImageButton postButton;
    private SuggestionType suggestionType;
    private MutableLiveData<Integer> postResult = new MutableLiveData<>();

    private ActivityResultLauncher<String> getGalleryImage;
    private ActivityResultLauncher<String> requestPermissionResultLauncher;

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
        addImageButton = (ImageButton) view.findViewById(R.id.imgButton_addImage);
        postButton = (ImageButton) view.findViewById(R.id.imgButton_post);

        getGalleryImage = registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
            if (uri != null)
                suggestionImageView.setImageURI(uri);
        });

        requestPermissionResultLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
            if (isGranted) {
                this.selectImage();
            } else {
                Toast.makeText(getContext(), R.string.storage_permission_not_granted, Toast.LENGTH_SHORT).show();
            }
        });

        suggestionRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButton_song:
                        suggestionType = SuggestionType.SongSuggestion;
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
                    postViewModel.postSuggestion(title, description, suggestionType, image);
                }
            }
        });

        postResult.observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer suggestionResult) {
                switch (suggestionResult) {
                    case 200: {
                        showMessage(R.string.suggestion_published);
                        suggestionRadioGroup.setSelected(false);
                        titleEditText.setText("");
                        descriptionEditText.setText("");
                        suggestionImageView.setImageBitmap(null);
                        break;
                    }
                    default: {
                        showMessage(R.string.suggestion_not_published);
                        break;
                    }
                }
            }
        });

        addImageButton.setOnClickListener(new View.OnClickListener() {
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

    private void selectImage() {
        getGalleryImage.launch("image/*");
    }
}