package com.lugeek.schoolevents.officeevents.presenter;

import android.os.Handler;

import com.lugeek.schoolevents.officeevents.biz.EventsRefreshBiz;
import com.lugeek.schoolevents.officeevents.biz.IEventsGot;
import com.lugeek.schoolevents.officeevents.view.IEventsView;

import java.util.logging.LogRecord;

/**
 * Created by lugeek on 2015/8/9.
 */
public class EventsPresenter {
    private EventsRefreshBiz eventsRefreshBiz;
    private IEventsView eventsView;
    private Handler handler = new Handler();

    public EventsPresenter(IEventsView eventsView) {
        this.eventsView = eventsView;
        eventsRefreshBiz = new EventsRefreshBiz();
    }

    public void refreshEvents(){
        eventsView.showLoading();
        eventsRefreshBiz.refreshEvents(eventsView.getSchoolFilterName(), eventsView.getTypeFilterName(),
                eventsView.getTimeFilterName(), new IEventsGot() {

                    @Override
                    public void refreshSuccess() {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                eventsView.refreshEventUI();
                                eventsView.hideLoading();
                            }
                        });
                    }

                    @Override
                    public void refreshFail() {

                    }
                });
    }
}
