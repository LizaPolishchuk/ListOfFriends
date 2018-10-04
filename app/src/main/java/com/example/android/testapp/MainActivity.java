package com.example.android.testapp;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    final static String TAG = "myLogs";
    static final String baseUrl = "https://randomuser.me/";
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    List<Person> personList, personList1;
    DatabaseHelper database;
    TextView tvConnection;
    SwipeRefreshLayout swipeLayout;
    Call<Results> resultsCall;

    //Проверка подключения к интернету
    public static boolean hasConnection(final Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);

        NetworkInfo wifiInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiInfo!=null && wifiInfo.isConnected()){
            return true;
        }
        wifiInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (wifiInfo!=null && wifiInfo.isConnected()){
            return true;
        }
        wifiInfo = connectivityManager.getActiveNetworkInfo();
        if (wifiInfo!=null&&wifiInfo.isConnected()){
            return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        personList = new ArrayList<>();
        personList1 = new ArrayList<>();

        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        swipeLayout.setColorSchemeColors(Color.BLUE, Color.RED, Color.YELLOW);
        swipeLayout.setOnRefreshListener(this);

        tvConnection = (TextView) findViewById(R.id.tv_connection);

        myAdapter = new MyAdapter(this, personList);

        database = App.getInstance().getDatabase();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        resultsCall = apiInterface.results();

        getTheList();
    }

    //получение данных с помощью Retrofit или с базы данных (если нет подключения)
    public void getTheList(){
        //если есть подключение к интернету
        if(hasConnection(this)){
            recyclerView.setVisibility(View.VISIBLE);
            tvConnection.setVisibility(View.GONE);

            resultsCall.enqueue(new Callback<Results>() {
                @Override
                public void onResponse(Call<Results> call, Response<Results> response) {

                    database.getDaoPerson().deleteAll(database.getDaoPerson().getAllPersons());
                    personList.clear();
                    assert response.body() != null;

                    personList.addAll(response.body().personList);

                    Collections.sort(personList, new Comparator<Person>() {
                        @Override
                        public int compare(Person person, Person person1) {
                            return person.getName().first.compareTo(person1.getName().first);
                        }
                    });

                    database.getDaoPerson().insertInDbList(personList);
                    myAdapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<Results> call, Throwable t) {
                    Log.d(TAG, "onFailure " + t);
                }
            });
            //Если нет подключения к интернету
        } else {
            personList.addAll(database.getDaoPerson().getAllPersons());
            if (personList.size()==0){
                tvConnection.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            } else {
                tvConnection.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }
            myAdapter.notifyDataSetChanged();
        }
    }

  @Override
  public void onRefresh() {
        //swipeLayout.setRefreshing(true);
        this.recreate();
        swipeLayout.setRefreshing(false);
    }
}
