package com.lugeek.schoolevents.officeevents.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lugeek on 2015/8/9.
 */
public class Events {
    private static Events instance;
    private List<Event> events;

    public static Events getInstance(){
        if (instance == null){
            instance = new Events();
            instance.events = new ArrayList<Event>();
        }
        return instance;
    }

    public List<Event> getEvents(){
        return events;
    }
    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<Event> setTestEvents(){
        events.removeAll(events);
        Event event1 = new Event();
        event1.setImageUrl("http://7xiq48.com1.z0.glb.clouddn.com/article/drjh.jpg");
        event1.setTitle("活动1");
        event1.setTime("时间：8月10日13:00");
        event1.setAddress("地点：杭州电子科技大学");
        event1.setParticipants(10);
        event1.setNeedSignup(true);
        events.add(event1);
        Event event2 = new Event();
        event2.setImageUrl("http://7xiq48.com1.z0.glb.clouddn.com/article/drjh.jpg");
        event2.setTitle("活动2");
        event2.setTime("时间：8月15日13:00");
        event2.setAddress("地点：杭州");
        event2.setParticipants(16);
        event2.setNeedSignup(false);
        events.add(event2);
        events.add(event1);
        events.add(event2);
        events.add(event1);
        events.add(event2);
        events.add(event1);
        events.add(event2);
        return events;
    }

    public List<Event> addList(List<Event> newEvents){
        events.addAll(0, newEvents);
        return events;
    }
}
