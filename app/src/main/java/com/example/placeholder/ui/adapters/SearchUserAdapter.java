package com.example.placeholder.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.placeholder.R;
import com.example.placeholder.data.model.Person;
import com.example.placeholder.data.model.Suggestion;
import com.example.placeholder.ui.search.SearchViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;

public class SearchUserAdapter extends RecyclerView.Adapter<SearchUserAdapter.ViewHolder> {

    private LinkedList<Person> localDataSet;
    private SearchViewModel viewModel;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView personIcon;
        private final TextView personNickname;
        private final TextView personName;
        private final Button followOrUnfollowButton;
        private Person selectedPerson;
        private SearchViewModel viewModel;

        public ViewHolder(View view) {
            super(view);
            personIcon = (ImageView) view.findViewById(R.id.imgView_personIcon);
            personNickname = (TextView) view.findViewById(R.id.txtView_personNickname);
            personName = (TextView) view.findViewById((R.id.txtView_personName));
            followOrUnfollowButton = (Button) view.findViewById(R.id.button_followORUnfollowPerson);

            followOrUnfollowButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (selectedPerson != null) {
                        viewModel.updateFollowInfo(selectedPerson);
                    }
                }
            });
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

        public Button getFollowOrUnfollowButton() {
            return followOrUnfollowButton;
        }

        public void setViewModel(SearchViewModel viewModel) {
            this.viewModel = viewModel;
        }

        public void setSelectedPerson(Person person) {
            selectedPerson = person;
        }
    }

    public void setLocalDataSet(LinkedList<Person> dataSet, SearchViewModel searchViewModel) {
        this.localDataSet = dataSet;
        this.viewModel = searchViewModel;
        notifyDataSetChanged();
    }

    @NotNull
    @Override
    public SearchUserAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.search_user, viewGroup, false);

        SearchUserAdapter.ViewHolder viewHolder = new SearchUserAdapter.ViewHolder(view);
        viewHolder.setViewModel(viewModel);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SearchUserAdapter.ViewHolder viewHolder, final int position) {
        if (localDataSet == null)
            return;

        Person currentPerson = localDataSet.get(position);

        viewHolder.setSelectedPerson(currentPerson);

        viewHolder.getPersonIcon().setImageBitmap(currentPerson.getIcon());
        viewHolder.getPersonNickname().setText(currentPerson.getNickname());
        viewHolder.getPersonName().setText(currentPerson.getName());

        if (viewModel.getLoggedPerson().getValue().equals(currentPerson))
            viewHolder.getFollowOrUnfollowButton().setVisibility(View.GONE);
        else {
            if (viewModel.isFollowing(currentPerson))
                viewHolder.getFollowOrUnfollowButton().setText(R.string.unfollow_person_text);
            else {
                viewHolder.getFollowOrUnfollowButton().setText(R.string.follow_person_text);
            }
        }
    }

    @Override
    public int getItemCount() {
        if (localDataSet == null)
            return 0;

        return localDataSet.size();
    }
}
