package com.example.tr.myapplication.di;

import com.example.tr.myapplication.domain.job.queue.LocalJobQueue;
import com.example.tr.myapplication.view.mvp.MainActivityPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    // Dagger will only look for methods annotated with @Provides
    @Provides
    @Singleton
    // Application reference must come from AppModule.class
    static MainActivityPresenter providesMainActivityPresenter() {
        return new MainActivityPresenter();
    }

    @Provides
    @Singleton
    // Application reference must come from AppModule.class
    static LocalJobQueue providesLocalJobQueue() {
        return new LocalJobQueue();
    }
}
