package com.example.tr.myapplication.view.mvp;

import com.example.tr.myapplication.MyApplication;
import com.example.tr.myapplication.domain.event.ReadyEvent;
import com.example.tr.myapplication.domain.job.local.DoCardTansactionJob;
import com.example.tr.myapplication.domain.job.queue.LocalJobQueue;
import com.example.tr.myapplication.utility.BusUtils;
import com.example.tr.myapplication.view.mvp.view.IMainActivityView;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;


public class MainActivityPresenter {

    @Inject
    LocalJobQueue _localJobQueue;

    private IMainActivityView view;

    public MainActivityPresenter() {
        MyApplication.getInstance().getAppComponent().inject(MainActivityPresenter.this);
    }

    public void setView(IMainActivityView view) {
        this.view = view;
    }

    public void start() {
        BusUtils.registerBusIfNotRegistered(this);
    }

    public void resume() {
    }

    public void pause() {
    }

    public void stop() {
        BusUtils.unregisterBusIfRegistered(this);
    }

    public void doPriQ() {
        _localJobQueue.addJobInBackground(new DoCardTansactionJob());
    }

    @Subscribe
    public void receiveReadyEvent(ReadyEvent event) {
        view.showResultsFromJob(event.getTime());
    }


}
