package com.lugeek.schoolevents.officeevents.biz;

import com.lugeek.schoolevents.officeevents.bean.Event;
import com.lugeek.schoolevents.officeevents.bean.Events;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lugeek on 2015/8/9.
 */
public class EventsRefreshBiz {

    public void refreshEvents(String school, String type, String time, final IEventsGot iEventsGot){
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                List<Event> newEvents = new ArrayList<Event>();
                for (int i=0;i<3;i++){
                    Event event = new Event();
                    event.setImageUrl("http://7xiq48.com1.z0.glb.clouddn.com/article/drjh.jpg");
                    event.setTitle("刷新的活动"+i);
                    event.setOrganizer("主办方：外星人");
                    event.setTime("时间：8月30日1:11");
                    event.setAddress("地点：地球");
                    event.setParticipants(100);
                    event.setFans(2);
                    event.setNeedSignup(false);
                    event.setDescribe("受死吧！地球人！" +"\n"+
                            "哈哈哈～～～～～");
                    newEvents.add(event);
                }
                Events.getInstance().addList(newEvents);
                iEventsGot.refreshSuccess();
            }
        }.start();
    }
}
