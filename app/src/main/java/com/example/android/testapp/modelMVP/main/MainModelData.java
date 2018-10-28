package com.example.android.testapp.modelMVP.main;

import com.example.android.testapp.database.MyDatabase;
import com.example.android.testapp.datamodels.Person;
import com.example.android.testapp.datamodels.otherPersonData.Results;
import com.example.android.testapp.retrofit.MyRetrofit;
import com.example.android.testapp.utils.CheckingConnection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainModelData implements MainContract.Model {

    private List<Person> personList = new ArrayList<>();
    private MyRetrofit myRetrofit = new MyRetrofit();
    private MyDatabase myDatabase = new MyDatabase();

    private Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            myDatabase.getDaoPerson().deleteAll(myDatabase.getDaoPerson().getAllPersons());
            myDatabase.getDaoPerson().insertInDbList(personList);

        }
    });


    /**
     * Getting the data
     */
    @Override
    public void getPersonList(final OnFinishedListener onFinishedListener) {

        /**If we have Internet connection get the data from api by Retrofit*/
        if (CheckingConnection.hasConnection()) {

            myRetrofit.getApiPerson().results().enqueue(new Callback<Results>() {
                @Override
                public void onResponse(Call<Results> call, Response<Results> response) {

                    personList.clear();
                    assert response.body() != null;

                    personList.addAll(response.body().personList);

                    Collections.sort(personList, new Comparator<Person>() {
                        @Override
                        public int compare(Person person, Person person1) {
                            return person.getName().getFirst().compareTo(person1.getName().getFirst());
                        }
                    });
                    thread.start();
                    onFinishedListener.onFinish(personList);
                }

                @Override
                public void onFailure(Call<Results> call, Throwable t) {
                    onFinishedListener.onFailure(t);
                }
            });

            /** we have not Internet connection get the data from database by Room*/
        } else {
            personList.addAll(myDatabase.getDaoPerson().getAllPersons());
            onFinishedListener.onFinish(personList);
        }
    }
}

