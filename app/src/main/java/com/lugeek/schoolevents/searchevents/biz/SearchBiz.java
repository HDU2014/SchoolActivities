package com.lugeek.schoolevents.searchevents.biz;

import com.lugeek.schoolevents.officeevents.bean.Event;
import com.lugeek.schoolevents.officeevents.bean.Events;
import com.lugeek.schoolevents.searchevents.bean.SearchEvents;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lugeek on 2015/8/11.
 */
public class SearchBiz {

    public void searchEvents(String content, final ISearchResult iSearchResult){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                List<Event> newEvents = new ArrayList<Event>();
                for (int i=0;i<3;i++){
                    Event event = new Event();
                    event.setImageUrl("http://7xiq48.com1.z0.glb.clouddn.com/article/drjh.jpg");
                    event.setTitle("搜到的活动"+i);
                    event.setTime("时间：8月30日1:11");
                    event.setAddress("地点：地球");
                    event.setParticipants(100);
                    event.setNeedSignup(false);
                    newEvents.add(event);
                }
                SearchEvents.getInstance().addList(newEvents);
                iSearchResult.searchSuccess();
            }
        }).start();
    }
}
