package com.lugeek.schoolevents.officeevents.bean;

/**
 * Created by lugeek on 2015/8/9.
 */
public class Event {
    private String imageUrl;
    private String title;
    private String organizer;
    private String time;
    private String address;
    private int fans;
    private int participants;
    private String describe;
    private boolean needSignup;
    private boolean isJoin;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getFans() {
        return fans;
    }

    public void setFans(int fans) {
        this.fans = fans;
    }

    public int getParticipants() {
        return participants;
    }

    public void setParticipants(int participants) {
        this.participants = participants;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public boolean isNeedSignup() {
        return needSignup;
    }

    public void setNeedSignup(boolean needSignup) {
        this.needSignup = needSignup;
    }

    public boolean isJoin() {
        return isJoin;
    }

    public void setJoin(boolean isJoin) {
        this.isJoin = isJoin;
    }
}
