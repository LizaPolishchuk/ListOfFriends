package com.example.android.testapp.modelMVP.main;

import com.example.android.testapp.datamodels.Person;

import java.util.List;

public class MainPresenter implements MainContract.Presenter, MainContract.Model.OnFinishedListener {

    private MainContract.Model model;
    private MainContract.View view;

    MainPresenter(MainContract.View view) {
        this.view = view;
        model = new MainModelData();
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
