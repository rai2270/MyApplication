package com.example.tr.myapplication.domain.job.local;


import com.example.tr.myapplication.domain.event.bus.MainThreadBus;
import com.example.tr.myapplication.domain.job.BaseJob;

import com.example.tr.myapplication.domain.event.ReadyEvent;

public class DoCardTansactionJob extends BaseJob {

    private long time;

    public DoCardTansactionJob() {
    }

    @Override
    public void onRun() throws Throwable {

        // db operation, etc...

        time = System.currentTimeMillis();


        MainThreadBus.getInstance().post(new ReadyEvent(time));

    }
}
