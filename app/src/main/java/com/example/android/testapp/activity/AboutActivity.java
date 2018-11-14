package com.example.android.testapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.testapp.R;
import com.example.android.testapp.utils.IntentAbout;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Activity that describe the selected person
 */
public class AboutActivity extends AppCompatActivity {

    @BindView(R.id.image_about)
    ImageView imageView;
    @BindView(R.id.tv_about_name)
    TextView tvName;
    @BindView(R.id.tv_about_gender)
    TextView tvGender;
    @BindView(R.id.tv_about_email)
    TextView tvEmail;
    @BindView(R.id.tv_about_date)
    TextView tvDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_about);
        ButterKnife.bind(this);

        Intent intent = getIntent();

        tvName.setText(intent.getStringExtra(IntentAbout.STRING_NAME));
        tvGender.setText(intent.getStringExtra(IntentAbout.STRING_GENDER));
        tvEmail.setText(intent.getStringExtra(IntentAbout.STRING_EMAIL));
        tvDate.setText(intent.getStringExtra(IntentAbout.STRING_REGISTERED));
        Picasso.get().load(intent.getStringExtra(IntentAbout.STRING_IMAGE)).into(imageView);
    }
}
