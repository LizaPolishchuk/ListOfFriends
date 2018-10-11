package com.example.android.testapp.modelMVP.about;

import android.content.Intent;

public class AboutContract {

    public interface Model {

        void getData(Intent intent, OnDataSetting onDataSetting);

        interface OnDataSetting {
            void setData(String name, String gender, String email, String date, String image);
        }
    }

    public interface View {
        void putData(String name, String gender, String email, String date, String image);
    }

    public interface Presenter {
        void getDataFromIntent(Intent intent);

        void onDestroy();
    }
}
