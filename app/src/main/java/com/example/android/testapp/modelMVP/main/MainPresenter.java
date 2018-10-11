package com.example.android.testapp.modelMVP.main;

import com.example.android.testapp.App;
import com.example.android.testapp.database.DatabaseHelper;
import com.example.android.testapp.datamodels.Person;
import com.example.android.testapp.datamodels.otherPersonData.Results;

import java.util.List;

import retrofit2.Call;

public class MainPresenter implements MainContract.Presenter, MainContract.Model.OnFinishedListener {

    private MainContract.Model model;
    private MainContract.View view;

    MainPresenter(MainContract.View view) {
        this.view = view;
        DatabaseHelper database = App.getInstance().getDatabase();
        Call<Results> resultsCall = App.getInstance().getInterface().results();
        model = new MainModelData(database, resultsCall);
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void getDataFromServer() {
        view.onShowProgressBar();
        model.getPersonList(this);
    }

    @Override
    public void onFinish(List<Person> personList) {
        view.onHideProgressBar();
        if (view != null) {
            view.setDataToRecyclerView(personList);
        }
    }

    @Override
    public void onFailure(Throwable throwable) {
        view.onHideProgressBar();
        if (view != null) {
            view.onResponseFailure(throwable);
        }
    }
}
