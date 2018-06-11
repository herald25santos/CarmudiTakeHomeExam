package com.santos.herald.carmuditakehomeexam.ui.base;

import io.reactivex.Scheduler;

public interface SchedulerProvider {

    Scheduler ui();

    Scheduler io();

    Scheduler computation();

}