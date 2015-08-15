package com.lugeek.schoolevents.officeevents.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by lugeek on 2015/8/9.
 */
public class Event implements Parcelable{
    private String imageUrl;
    private String title;
    private String organizer;
    private String time;
    private String address;
    private int fans;
    private int participants;
    private int allNum;
    private String describe;
    private boolean needSignup;



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

    public int getAllNum() {
        return allNum;
    }

    public void setAllNum(int allNum) {
        this.allNum = allNum;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        //按成员变量声明顺序
        dest.writeString(imageUrl);
        dest.writeString(title);
        dest.writeString(organizer);
        dest.writeString(time);
        dest.writeString(address);
        dest.writeInt(fans);
        dest.writeInt(participants);
        dest.writeInt(allNum);
        dest.writeString(describe);
        dest.writeInt(needSignup?1:0);
    }

    public static final Parcelable.Creator<Event> CREATOR = new Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel source) {
            Event e = new Event();
            e.setImageUrl(source.readString());
            e.setTitle(source.readString());
            e.setOrganizer(source.readString());
            e.setTime(source.readString());
            e.setAddress(source.readString());
            e.setFans(source.readInt());
            e.setParticipants(source.readInt());
            e.setAllNum(source.readInt());
            e.setDescribe(source.readString());
            e.setNeedSignup(source.readInt()==1);
            return e;
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };
}
