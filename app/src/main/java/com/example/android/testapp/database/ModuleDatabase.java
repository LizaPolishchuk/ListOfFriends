package com.example.android.testapp.database;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.android.testapp.dependency.ModuleContext;
import com.example.android.testapp.dependency.SingleScope;

import dagger.Module;
import dagger.Provides;

/**
 * Module to inject database
 */
@Module(includes = ModuleContext.class)
public class ModuleDatabase {
    @SingleScope
    @Provides
    public DatabaseHelper database(Context context) {
        return Room.databaseBuilder(context, DatabaseHelper.class, "myDatabase")
                .build();
    }
}
