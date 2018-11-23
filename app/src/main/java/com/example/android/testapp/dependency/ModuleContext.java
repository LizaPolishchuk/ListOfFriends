package com.example.android.testapp.dependency;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Module to inject context
 */
@Module
public class ModuleContext {
    private Context context;

    public ModuleContext(Context context) {
        this.context = context;
    }

    @SingleScope
    @Provides
    Context getContext() {
        return context.getApplicationContext();
    }
}
