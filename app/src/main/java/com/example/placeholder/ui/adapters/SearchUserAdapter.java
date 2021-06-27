package com.example.placeholder.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.placeholder.R;
import com.example.placeholder.data.model.Person;
import com.example.placeholder.data.model.Suggestion;

import org.jetbrains.annotations.NotNull;

public class SearchUserAdapter extends RecyclerView.Adapter<SearchUserAdapter.ViewHolder> {

    private Person[] localDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView personIcon;
        private final TextView personNickname;
        private final TextView personName;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            personIcon = (ImageView) view.findViewById(R.id.imgView_personIcon);
            personNickname = (TextView) view.findViewById(R.id.txtView_personNickname);
            personName = (TextView) view.findViewById((R.id.txtView_personName));
        }

        public ImageView getPersonIcon() {
            return personIcon;
        }

        public TextView getPersonNickname() {
            return personNickname;
        }

        public TextView getPersonName() {
            return personName;
        }
    }

    public void setLocalDataSet(Person[] dataSet) {
        localDataSet = dataSet;
    }

    @NotNull
    @Override
    public SearchUserAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.search_user, viewGroup, false);

        return new SearchUserAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchUserAdapter.ViewHolder viewHolder, final int position) {
        //viewHolder.getPersonIcon().setImageBitmap(localDataSet[position].getPerson().getIcon());
        viewHolder.getPersonNickname().setText(localDataSet[position].getNickname());
        viewHolder.getPersonName().setText(localDataSet[position].getName());
    }

    @Override
    public int getItemCount() {
        return localDataSet.length;
    }
}
