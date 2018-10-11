package com.example.android.testapp.modelMVP.about;

import android.content.Intent;

public class AboutPresenter implements AboutContract.Presenter, AboutModel.OnDataSetting {

    private AboutContract.Model model;
    private AboutContract.View view;

    AboutPresenter(AboutContract.View view) {
        this.view = view;
        model = new AboutModel();
    }

    @Override
    public void getDataFromIntent(Intent intent) {
        model.getData(intent, this);
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void setData(String name, String gender, String email, String date, String image) {
        if (view != null) {
            view.putData(name, gender, email, date, image);
        }
    }
}
