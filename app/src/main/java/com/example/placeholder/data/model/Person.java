package com.example.placeholder.data.model;

import android.graphics.Bitmap;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.example.placeholder.data.model.Helpers.BitmapHelper;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Person implements Serializable {

    @Expose
    private int id;

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
    private String base64Icon;

    private LinkedList<Person> followers;

    private LinkedList<Person> followings;

    public Person() {
    }

    public Person(String name, String nickname, String email, String password) {
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return BitmapHelper.convertToBitmap(base64Icon);
    }

    public void setIcon(Bitmap icon) {
        this.base64Icon = BitmapHelper.convertToBase64(icon);
    }

    public LinkedList<Person> getFollowers() {
        return followers;
    }

    public void setFollowers(LinkedList<Person> followers) {
        this.followers = followers;
    }

    public LinkedList<Person> getFollowings() {
        return followings;
    }

    public void setFollowings(LinkedList<Person> followings) {
        this.followings = followings;
    }

    public long getFollowersCount() {
        if (followers == null)
            return 0;

        return followers.size();
    }

    public long getFollowingsCount() {
        if (followings == null)
            return 0;

        return followings.size();
    }

    public boolean isFollowing(Person person) {
        return this.followings.contains(person);
    }

    public void followPerson(Person person) {
        this.followings.add(person);
    }

    public void unfollowPerson(Person person) {
        this.followings.remove(person);
    }

    public Person getFollowingPerson(String email) {
        for (Person person : this.followings) {
            if (person.email.equals(email))
                return person;
        }

        return null;
    }

    @Override
    public boolean equals(@Nullable @org.jetbrains.annotations.Nullable Object obj) {
        Person person = (Person) obj;

        if (person == null)
            return false;

        return this.nickname.equals(person.nickname)
                && this.email.equals(person.email);
    }
}
