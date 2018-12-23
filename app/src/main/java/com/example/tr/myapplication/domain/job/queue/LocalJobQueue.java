package com.example.tr.myapplication.domain.job.queue;

import com.birbit.android.jobqueue.JobManager;
import com.birbit.android.jobqueue.config.Configuration;
import com.example.tr.myapplication.MyApplication;

public class LocalJobQueue extends JobManager {
    public LocalJobQueue(){
        super(new Configuration.Builder(MyApplication.getInstance().getApplicationContext())
                .id("localQueue")
                .minConsumerCount(1)
                .maxConsumerCount(1)
                .build());
    }
}
