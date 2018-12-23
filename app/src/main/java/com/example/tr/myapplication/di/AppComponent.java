package com.example.tr.myapplication.di;

import com.example.tr.myapplication.view.MainActivity;
import com.example.tr.myapplication.view.mvp.MainActivityPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(MainActivity activity);
    void inject(MainActivityPresenter activityPresenter);
}
