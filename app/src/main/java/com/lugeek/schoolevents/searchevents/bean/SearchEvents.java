package com.lugeek.schoolevents.searchevents.bean;

import com.lugeek.schoolevents.officeevents.bean.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lugeek on 2015/8/13.
 */
public class SearchEvents {
    private static SearchEvents instance;
    private List<Event> events;

    public static SearchEvents getInstance() {
        if (instance == null){
            instance = new SearchEvents();
            instance.events = new ArrayList<Event>();
        }
        return instance;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public void addEvent(Event event){
        this.events.add(event);
    }

    public void removeAll(){
        this.events.removeAll(events);
    }

    public List<Event> addList(List<Event> newevents){
        instance.events.addAll(0, newevents);
        return instance.events;
    }

    public void none(){
        events = null;
        instance = null;
    }
}
