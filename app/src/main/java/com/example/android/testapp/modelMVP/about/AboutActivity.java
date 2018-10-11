package com.example.android.testapp.modelMVP.about;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.testapp.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Activity that describe the selected person
 */
public class AboutActivity extends AppCompatActivity implements AboutContract.View {

    @BindView(R.id.image_about)
    ImageView imageView;
    @BindView(R.id.tv_about_name)
    TextView tvName;
    @BindView(R.id.tv_about_gender)
    TextView tvGender;
    @BindView(R.id.tv_about_emile)
    TextView tvEmail;
    @BindView(R.id.tv_about_date)
    TextView tvDate;

    AboutContract.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_about);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        presenter = new AboutPresenter(this);

        presenter.getDataFromIntent(intent);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void putData(String name, String gender, String email, String date, String image) {
        tvName.setText(name);
        tvGender.setText(gender);
        tvEmail.setText(email);
        tvDate.setText(date);

        Picasso.get().load(image).into(imageView);
    }
}