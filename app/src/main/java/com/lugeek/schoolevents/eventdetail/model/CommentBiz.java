package com.lugeek.schoolevents.eventdetail.model;

import com.lugeek.schoolevents.officeevents.bean.Event;

/**
 * Created by lugeek on 2015/8/12.
 */
public class CommentBiz implements ICommentBiz {

    @Override
    public void refreshComments(Event event, final ICommentsGot iCommentsGot) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Comments.getInstance().getComments().add(new Comment("lugeek", "15:20", "看上去不错!", ""));
                Comments.getInstance().getComments().add(new Comment("Jack", "17:50", "顶！", ""));
                Comments.getInstance().getComments().add(new Comment("Lucy", "18:23", "一起啊!", "lugeek"));
                iCommentsGot.commentsSuccess();
            }
        }).start();
    }

    @Override
    public void deliverComment(final Comment comment, final ICommentsGot iCommentsGot) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Comments.getInstance().getComments().add(0, comment);
                iCommentsGot.commentsSuccess();
            }
        }).start();
    }
}
