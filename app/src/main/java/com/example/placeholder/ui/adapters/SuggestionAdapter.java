package com.example.placeholder.ui.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.placeholder.R;
import com.example.placeholder.data.model.Suggestion;

import org.jetbrains.annotations.NotNull;

public class SuggestionAdapter extends RecyclerView.Adapter<SuggestionAdapter.ViewHolder> {

    private Suggestion[] localDataSet;

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

    public void setLocalDataSet(Suggestion[] dataSet){
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
        viewHolder.getPersonIcon().setImageBitmap(localDataSet[position].getPerson().getIcon());
        viewHolder.getPersonNickname().setText(localDataSet[position].getPerson().getNickname());
        viewHolder.getSuggestionTitle().setText(
                localDataSet[position].getSuggestionType().toString() +
                " - " +
                localDataSet[position].getTitle());
        viewHolder.getSuggestionDescription().setText(localDataSet[position].getDescription());
        viewHolder.getSuggestionFirstImage().setImageBitmap(localDataSet[position].getImages()[0]);
    }

    @Override
    public int getItemCount() {
        return localDataSet.length;
    }
}
