package com.example.tr.myapplication.domain.job;

import android.support.annotation.NonNull;

import com.birbit.android.jobqueue.CancelReason;
import com.birbit.android.jobqueue.Job;
import com.birbit.android.jobqueue.Params;
import com.birbit.android.jobqueue.RetryConstraint;
import com.example.tr.myapplication.utility.LumberJack;


public abstract class BaseJob extends Job {

    protected BaseJob() {
        super(new Params(1));
    }

    protected BaseJob(Params params) {
        super(params);
    }

    @Override
    public void onAdded() {

    }

    @Override
    public void onRun() throws Throwable {

    }

    @Override
    protected void onCancel(@CancelReason int reason, Throwable t) {

    }

    @Override
    protected RetryConstraint shouldReRunOnThrowable(@NonNull Throwable throwable, int runCount, int maxRunCount) {
        LumberJack.logGeneric("Error in job: " + throwable.getLocalizedMessage());
        return RetryConstraint.CANCEL;
    }

}
