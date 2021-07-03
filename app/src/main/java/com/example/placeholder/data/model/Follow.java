package com.example.placeholder.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Follow {

    @SerializedName("email_following")
    @Expose
    private String followingEmail;

    @SerializedName("email_follower")
    @Expose
    private String followerEmail;

    public Follow(String followingEmail, String followerEmail) {
        this.followingEmail = followingEmail;
        this.followerEmail = followerEmail;
    }

    public String getFollowingEmail() {
        return followingEmail;
    }

    public void setFollowingEmail(String followingEmail) {
        this.followingEmail = followingEmail;
    }

    public String getFollowerEmail() {
        return followerEmail;
    }

    public void setFollowerEmail(String followerEmail) {
        this.followerEmail = followerEmail;
    }
}
