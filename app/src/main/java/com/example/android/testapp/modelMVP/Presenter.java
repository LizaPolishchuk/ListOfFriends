package com.example.android.testapp.modelMVP;

import com.example.android.testapp.datamodels.Person;

import java.util.List;

public class Presenter implements Contract.Presenter, Contract.Model.OnFinishedListener {

    private Contract.Model model;
    private Contract.View view;

    public Presenter(Contract.View view) {
        this.view = view;
        model = new ModelData();
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void getDataFromServer() {
        model.getPersonList(this);
    }

    @Override
    public void onFinish(List<Person> personList) {
        if (view != null) {
            view.setDataToRecyclerView(personList);
        }
    }

    @Override
    public void onFailure(Throwable throwable) {
        if (view != null) {
            view.onResponseFailure(throwable);
        }
    }
}
