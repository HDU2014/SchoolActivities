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
        event1.setOrganizer("主办方：杭州电子科技大学");
        event1.setTime("时间：8月10日13:00");
        event1.setAddress("地点：杭州电子科技大学6教201");
        event1.setParticipants(10);
        event1.setAllNum(100);
        event1.setFans(20);
        event1.setNeedSignup(true);
        event1.setDescribe("如果你热爱音乐，需要舞台； \n" +
                "如果想认识新伙伴，擦出创作的火花； \n" +
                "如果你有想法，做些关于音乐的交流活动。 \n" +
                "欢迎，你们。");
        events.add(event1);
        Event event2 = new Event();
        event2.setImageUrl("http://7xiq48.com1.z0.glb.clouddn.com/article/drjh.jpg");
        event2.setTitle("活动2");
        event2.setOrganizer("主办方：通信学院");
        event2.setTime("时间：8月15日13:00");
        event2.setAddress("地点：科技馆");
        event2.setParticipants(16);
        event2.setAllNum(200);
        event2.setFans(52);
        event2.setNeedSignup(false);
        event2.setDescribe("针对于浙江大学法学硕士在职研究生课程班级" +
                "的开班，还有针对于各个专业的咨询情况。法学硕士将于9月12日" +
                "开班。其他课程开班具体情况做出简短的解答，针对于现阶段法学硕士" +
                "能够使用的范围，上课的课程内容，都可以到校做咨询。");
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
