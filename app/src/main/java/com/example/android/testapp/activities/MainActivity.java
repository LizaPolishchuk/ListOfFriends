package com.example.android.testapp.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.android.testapp.MyAdapter;
import com.example.android.testapp.R;
import com.example.android.testapp.retrofit.WorkWithRetrofit;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    TextView tvConnection;
    SwipeRefreshLayout swipeLayout;
    WorkWithRetrofit workWithRetrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        swipeLayout.setColorSchemeColors(Color.BLUE, Color.RED);
        swipeLayout.setOnRefreshListener(this);

        tvConnection = (TextView) findViewById(R.id.tv_connection);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        workWithRetrofit = new WorkWithRetrofit(recyclerView, tvConnection);
        myAdapter = workWithRetrofit.getMyAdapter();

        recyclerView.setAdapter(myAdapter);

        //Getting all data
        workWithRetrofit.getTheList();
    }

    //Swipe to refresh
  @Override
  public void onRefresh() {
        this.recreate();
        swipeLayout.setRefreshing(false);
    }
}
