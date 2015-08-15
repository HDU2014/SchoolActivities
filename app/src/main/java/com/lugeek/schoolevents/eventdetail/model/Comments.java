package com.lugeek.schoolevents.eventdetail.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lugeek on 2015/8/13.
 */
public class Comments {
    private static Comments instance;
    private List<Comment> comments;

    public static Comments getInstance() {
        if (instance==null){
            instance = new Comments();
            instance.comments = new ArrayList<Comment>();
        }
        return instance;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void addComment(Comment comment){
        this.comments.add(0, comment);
    }

    public void removeAll(){
        this.comments.removeAll(comments);
    }
}
