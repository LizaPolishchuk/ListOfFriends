package com.example.android.testapp.activity;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.android.testapp.R;
import com.example.android.testapp.data.Person;
import com.example.android.testapp.dependency.DaggerMainComponent;
import com.example.android.testapp.dependency.MainComponent;
import com.example.android.testapp.dependency.ModuleContext;
import com.example.android.testapp.dependency.ModuleHideProgress;
import com.example.android.testapp.paging.MainDataFactory;
import com.example.android.testapp.paging.PagingAdapter;
import com.example.android.testapp.utils.OnHideProgress;

import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements OnHideProgress {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.progress_bar)
    ProgressBar progress;

    PagingAdapter adapter;
    MainDataFactory dataFactory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        MainComponent mainComponent = DaggerMainComponent.builder()
                .moduleHideProgress(new ModuleHideProgress(this))
                .moduleContext(new ModuleContext(this))
                .build();

        dataFactory = new MainDataFactory(mainComponent);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        /**PagedList with liveData*/
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(5)
                .setInitialLoadSizeHint(10)
                .build();


        LiveData<PagedList<Person>> pagedListLiveData = new LivePagedListBuilder<>(dataFactory, config)
                .setFetchExecutor(Executors.newSingleThreadExecutor())
                .build();


        adapter = new PagingAdapter(new DiffUtil.ItemCallback<Person>() {
            @Override
            public boolean areItemsTheSame(Person oldItem, Person newItem) {
                return (oldItem.getName().equals(newItem.getName()));
            }

            @Override
            public boolean areContentsTheSame(Person oldItem, Person newItem) {
                return (oldItem.getEmail().equals(newItem.getEmail())
                        && oldItem.getGender().equals(newItem.getGender())
                        && oldItem.getPicture().equals(newItem.getPicture())
                        && oldItem.getRegistered().equals(newItem.getRegistered()));
            }
        });

        pagedListLiveData.observe(this, new Observer<PagedList<Person>>() {
            @Override
            public void onChanged(@Nullable PagedList<Person> pagedList) {
                Log.d("myLogs", "Observe submit PagedList");
                adapter.submitList(pagedList);
            }
        });

        recyclerView.setAdapter(adapter);
    }

    /**
     * Hide progressBar when all data obtained from Retrofit
     */
    @Override
    public void hideProgress() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progress.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }
        });
        Log.d("myLogs", "hideProgress");

    }
}
