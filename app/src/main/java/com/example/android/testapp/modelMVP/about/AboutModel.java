package com.example.android.testapp.modelMVP.about;

import android.content.Intent;

public class AboutModel implements AboutContract.Model {

    @Override
    public void getData(Intent intent, OnDataSetting onDataSetting) {
        String name = intent.getStringExtra("name");
        String gender = intent.getStringExtra("gender");
        String email = intent.getStringExtra("email");
        String date = intent.getStringExtra("registered");
        String image = intent.getStringExtra("image");

        onDataSetting.setData(name, gender, email, date, image);
    }
}
