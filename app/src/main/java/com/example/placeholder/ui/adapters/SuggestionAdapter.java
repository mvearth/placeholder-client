package com.example.placeholder.ui.adapters;


import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.placeholder.R;
import com.example.placeholder.data.model.Helpers.BitmapHelper;
import com.example.placeholder.data.model.Suggestion;

import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;

public class SuggestionAdapter extends RecyclerView.Adapter<SuggestionAdapter.ViewHolder> {

    private LinkedList<Suggestion> localDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView personIcon;
        private final TextView personNickname;
        private final TextView suggestionTitle;
        private final TextView suggestionDescription;
        private final ImageView suggestionImage;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            personIcon = (ImageView) view.findViewById(R.id.imgView_personIcon);
            personNickname = (TextView) view.findViewById(R.id.txtView_personNickname);
            suggestionTitle = (TextView) view.findViewById((R.id.txtView_suggestionTitle));
            suggestionDescription = (TextView) view.findViewById(R.id.txtView_suggestionDescription);
            suggestionImage = (ImageView) view.findViewById(R.id.imgView_suggestionFirstImage);
        }

        public ImageView getPersonIcon() {
            return personIcon;
        }

        public TextView getPersonNickname() {
            return personNickname;
        }

        public TextView getSuggestionTitle() {
            return suggestionTitle;
        }

        public TextView getSuggestionDescription() {
            return suggestionDescription;
        }

        public ImageView getSuggestionFirstImage() {
            return suggestionImage;
        }
    }

    public void setLocalDataSet(LinkedList<Suggestion> dataSet) {
        localDataSet = dataSet;
        notifyDataSetChanged();
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.suggestion, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Suggestion suggestion = localDataSet.get(position);

        Bitmap personImageBitmap = suggestion.getPerson().getIcon();

        if (personImageBitmap == null)
            viewHolder.getPersonIcon().setImageResource(R.drawable.ic_person_24dp);
        else
            viewHolder.getPersonIcon().setImageBitmap(personImageBitmap);

        viewHolder.getPersonNickname().setText(suggestion.getPerson().getNickname());
        viewHolder.getSuggestionTitle().setText(
                suggestion.getSuggestionType().toString() +
                        " - " +
                        suggestion.getTitle());
        viewHolder.getSuggestionDescription().setText(suggestion.getDescription());

        if (suggestion.getBase64Image() != null)
            viewHolder.getSuggestionFirstImage().setImageBitmap(BitmapHelper.convertToBitmap(suggestion.getBase64Image()));
    }

    @Override
    public int getItemCount() {
        if (localDataSet == null)
            return 0;

        return localDataSet.size();
    }
}
