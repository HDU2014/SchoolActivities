package com.lugeek.schoolevents.eventdetail.model;

/**
 * Created by lugeek on 2015/8/12.
 */
public class Comment {
    private String commenter;
    private String timestamp;
    private String content;
    private String reply;

    public Comment(String commenter, String timestamp, String content, String reply) {
        this.commenter = commenter;
        this.timestamp = timestamp;
        this.content = content;
        this.reply = reply;
    }
    public Comment(){

    }

    public String getCommenter() {
        return commenter;
    }

    public void setCommenter(String commenter) {
        this.commenter = commenter;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }
}
