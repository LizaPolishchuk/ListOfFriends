package com.example.android.testapp.paging;

import android.arch.paging.PositionalDataSource;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.android.testapp.data.Person;
import com.example.android.testapp.data.otherPersonData.Results;
import com.example.android.testapp.database.MyDatabase;
import com.example.android.testapp.network.MyRetrofit;
import com.example.android.testapp.utils.CheckingConnection;
import com.example.android.testapp.utils.OnHideProgress;
import com.example.android.testapp.utils.WorkWithDatabase;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainDataSource extends PositionalDataSource<Person> {

    private static List<Person> personList = new ArrayList<>();
    private MyRetrofit retrofit;
    private MyDatabase database;
    private OnHideProgress onHideProgress;

    public MainDataSource(MyRetrofit retrofit, MyDatabase database, OnHideProgress onHideProgress) {
        this.retrofit = retrofit;
        this.database = database;
        this.onHideProgress = onHideProgress;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull final LoadInitialCallback<Person> callback) {
        getData(params.requestedLoadSize, params.requestedStartPosition, callback, null);
    }

    @Override
    public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback<Person> callback) {
        getData(params.loadSize, params.startPosition, null, callback);
    }

    /**
     * Getting the data from Retrofit or Database
     */
    private void getData(int count, final int position, final LoadInitialCallback<Person> callback, final LoadRangeCallback<Person> callbackRange) {
        if (CheckingConnection.hasConnection()) {

            retrofit.getApiPerson().results(count).enqueue(new Callback<Results>() {
                @Override
                public void onResponse(Call<Results> call, Response<Results> response) {
                    assert response.body() != null;
                    personList.addAll(response.body().getPersonList());
                    WorkWithDatabase.putData(database, personList);

                    if (callbackRange == null) {
                        callback.onResult(personList, position);
                    } else callbackRange.onResult(personList);

                    onHideProgress.hideProgress();
                }

                @Override
                public void onFailure(Call<Results> call, Throwable t) {
                    Log.d("mainData", "onFailure" + t);
                }
            });
        } else {
            onHideProgress.hideProgress();
            if (callbackRange == null) {
                personList.addAll(WorkWithDatabase.getPersonList(database));
                callback.onResult(personList, position);
            } else {
                if (personList.size() < count + position) {
                    personList.clear();
                }
                callbackRange.onResult(personList);
            }
        }
    }

}
