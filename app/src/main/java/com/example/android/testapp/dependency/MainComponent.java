package com.example.android.testapp.dependency;

import com.example.android.testapp.database.ModuleDatabase;
import com.example.android.testapp.network.ModuleRetrofit;
import com.example.android.testapp.paging.MainDataSource;

import dagger.Component;

/**
 * Component define from which modules dependencies are provided
 */
@SingleScope
@Component(modules = {ModuleHideProgress.class, ModuleDatabase.class, ModuleRetrofit.class, ModuleContext.class})
public interface MainComponent {
    MainDataSource getMainDataSource();
}

