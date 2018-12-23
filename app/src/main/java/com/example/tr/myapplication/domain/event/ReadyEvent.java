package com.example.tr.myapplication.domain.event;

public class ReadyEvent {

    private long time;

    public ReadyEvent(long time) {
        this.time = time;
    }

    public long getTime() {
        return this.time;
    }
}
