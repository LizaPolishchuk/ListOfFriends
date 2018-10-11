package com.example.android.testapp.modelMVP.main;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.testapp.MyAdapter;
import com.example.android.testapp.R;
import com.example.android.testapp.datamodels.Person;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, MainContract.View {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.tv_connection)
    TextView tvConnection;
    @BindView(R.id.swipe_container)
    SwipeRefreshLayout swipeLayout;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    MainContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        swipeLayout.setColorSchemeColors(Color.BLUE, Color.RED);
        swipeLayout.setOnRefreshListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        presenter = new MainPresenter(this);
        presenter.getDataFromServer();
    }

    /**
     * Setting adapter with data to RecyclerView
     */
    @Override
    public void setDataToRecyclerView(List<Person> personList) {
        MyAdapter myAdapter = new MyAdapter(personList);
        recyclerView.setAdapter(myAdapter);
        if (personList.size() == 0) {
            tvConnection.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
    }

    /**Show toast when data received error*/
    @Override
    public void onResponseFailure(Throwable t) {
        Log.d("MainActivity", "onFailure: " + t);
        Toast.makeText(this, "Something went wrong: " + t, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onShowProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onHideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    /**Swipe to refresh*/
    @Override
    public void onRefresh() {
        this.recreate();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }
}
