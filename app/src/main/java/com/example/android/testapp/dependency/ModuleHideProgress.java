package com.example.android.testapp.dependency;

import com.example.android.testapp.utils.OnHideProgress;

import dagger.Module;
import dagger.Provides;

/**
 * Module to inject interface OnHideProgress
 */
@Module
public class ModuleHideProgress {

    private OnHideProgress onHideProgress;

    public ModuleHideProgress(OnHideProgress getOnHideProgress) {
        this.onHideProgress = getOnHideProgress;
    }

    @Provides
    OnHideProgress provideOnHideProgress() {
        return onHideProgress;
    }
}
