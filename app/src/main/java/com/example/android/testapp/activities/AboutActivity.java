package com.example.android.testapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.testapp.R;
import com.squareup.picasso.Picasso;

//Activity that describe the selected person
public class AboutActivity extends AppCompatActivity {

    private String name, gender,  email, date;
    private String image;
    private ImageView imageView;
    private TextView tvName, tvGender, tvEmail, tvDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_about);

        Intent intent = getIntent();

        imageView = (ImageView) findViewById(R.id.image_about);

        tvName = (TextView) findViewById(R.id.tv_about_name);
        tvGender = (TextView) findViewById(R.id.tv_about_gender);
        tvEmail = (TextView) findViewById(R.id.tv_about_emile);
        tvDate = (TextView) findViewById(R.id.tv_about_date);

        name = intent.getStringExtra("name");
        gender = intent.getStringExtra("gender");
        email = intent.getStringExtra("email");
        date = intent.getStringExtra("registered");
        image = intent.getStringExtra("image");

        setTexts();
    }

    public void  setTexts(){
        tvName.setText(name);
        tvGender.setText(gender);
        tvEmail.setText(email);
        tvDate.setText(date);

        Picasso.get().load(image).into(imageView);
    }
}
