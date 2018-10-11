package com.example.android.testapp.utils;

import android.content.Context;
import android.content.Intent;

import com.example.android.testapp.modelMVP.about.AboutActivity;

public class StartingActivity {

    /**
     * Starting activity AboutActivity that describe the selected person
     */
    public static void startActivity(Context context, String gender, String name, String image, String email, String date) {
        Intent intent = new Intent(context, AboutActivity.class);

        intent.putExtra("gender", gender);
        intent.putExtra("name", name);
        intent.putExtra("image", image);
        intent.putExtra("email", email);
        intent.putExtra("registered", date);
        context.startActivity(intent);
    }
}
