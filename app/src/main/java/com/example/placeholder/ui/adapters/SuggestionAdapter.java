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
        private final TextView personName;
        private final TextView suggestionTitle;
        private final TextView suggestionDescription;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            personIcon = (ImageView) view.findViewById(R.id.imgView_personIcon);
            personName = (TextView) view.findViewById(R.id.txtView_personName);
            suggestionTitle = (TextView) view.findViewById((R.id.txtView_suggestionTitle));
            suggestionDescription = (TextView) view.findViewById(R.id.txtView_suggestionDescription);
        }

        public ImageView getPersonIcon() {
            return personIcon;
        }

        public TextView getPersonName() {
            return personName;
        }

        public TextView getSuggestionTitle() {
            return suggestionTitle;
        }

        public TextView getSuggestionDescription() {
            return suggestionDescription;
        }
    }

    public void setLocalDataSet(Suggestion[] dataSet){
        localDataSet = dataSet;
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
        //viewHolder.getPersonIcon().setImageBitmap(localDataSet[position].getPerson().getIcon());
        viewHolder.getPersonName().setText(localDataSet[position].getPerson().getNickname());
        viewHolder.getSuggestionTitle().setText(
                localDataSet[position].getSuggestionType().toString() +
                " - " +
                localDataSet[position].getTitle());
        viewHolder.getSuggestionDescription().setText(localDataSet[position].getDescription());
    }

    @Override
    public int getItemCount() {
        return localDataSet.length;
    }
}
