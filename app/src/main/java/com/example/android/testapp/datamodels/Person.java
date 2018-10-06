package com.example.android.testapp.datamodels;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

@Entity
public class Person {
    @SerializedName("gender")
    private String gender;

    @Embedded
    @SerializedName("name")
    private Name name;

    @Embedded
    @SerializedName("picture")
    private Picture picture;

    @NonNull
    @PrimaryKey
    @SerializedName("email")
    private String email;

    @Embedded
    @SerializedName("registered")
    private Registered registered;

    //getters and setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Registered getRegistered() {
        return registered;
    }

    public void setRegistered(Registered registered) {
        this.registered = registered;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }
}