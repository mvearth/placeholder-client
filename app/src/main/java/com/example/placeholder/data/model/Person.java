package com.example.placeholder.data.model;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Person implements Serializable {
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("nickname")
    @Expose
    private String nickname;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("photo")
    @Expose
    private Bitmap icon;

    private Person[] followers;
    private Person[] following;

    public Person() {}

    public Person(String name, String nickname, String email, String password){
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Bitmap getIcon() {
        return icon;
    }

    public void setIcon(Bitmap icon) {
        this.icon = icon;
    }

    public Person[] getFollowers() {
        return followers;
    }

    public void setFollowers(Person[] followers) {
        this.followers = followers;
    }

    public Person[] getFollowings() {
        return following;
    }

    public void setFollowings(Person[] following) {
        this.following = following;
    }

    public long getFollowersCount() {
        if (followers == null)
            return 0;

        return followers.length;
    }

    public long getFollowingsCount() {
        if (following == null)
            return 0;

        return following.length;
    }
}
