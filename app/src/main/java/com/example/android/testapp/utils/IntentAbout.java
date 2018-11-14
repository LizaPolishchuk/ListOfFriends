package com.example.android.testapp.utils;

import android.content.Context;
import android.content.Intent;

import com.example.android.testapp.activity.AboutActivity;
import com.example.android.testapp.data.Person;

public class IntentAbout {

    public static final String STRING_NAME = "name";
    public static final String STRING_GENDER = "gender";
    public static final String STRING_IMAGE = "image";
    public static final String STRING_EMAIL = "email";
    public static final String STRING_REGISTERED = "registered";

    /**
     * Starting activity AboutActivity that describe the selected person
     */
    public static void startActivity(Context context, Person person) {
        Intent intent = new Intent(context, AboutActivity.class);

        intent.putExtra("gender", person.getGender());
        intent.putExtra("name", person.getName().getFirst() + " " + person.getName().getLast());
        intent.putExtra("image", person.getPicture().getLarge());
        intent.putExtra("email", person.getEmail());
        intent.putExtra("registered", person.getRegistered().getDate());
        context.startActivity(intent);
    }
}
