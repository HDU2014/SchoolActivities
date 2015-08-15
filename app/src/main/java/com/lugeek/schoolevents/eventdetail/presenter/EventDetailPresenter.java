package com.lugeek.schoolevents.eventdetail.presenter;

import android.os.Handler;

import com.lugeek.schoolevents.eventdetail.model.Comment;
import com.lugeek.schoolevents.eventdetail.model.CommentBiz;
import com.lugeek.schoolevents.eventdetail.model.ICommentsGot;
import com.lugeek.schoolevents.eventdetail.view.IEventDetail;
import com.lugeek.schoolevents.officeevents.bean.Event;

/**
 * Created by lugeek on 2015/8/12.
 */
public class EventDetailPresenter {
    private IEventDetail iEventDetail;
    private CommentBiz commentBiz;
    private Handler handler = new Handler();

    public EventDetailPresenter(IEventDetail iEventDetail) {
        this.iEventDetail = iEventDetail;
        commentBiz = new CommentBiz();
    }

    public void loadComments(Event event){
        commentBiz.refreshComments(event, new ICommentsGot() {
            @Override
            public void commentsSuccess() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iEventDetail.refreshUI();
                    }
                });
            }

            @Override
            public void commentsFail() {

            }
        });
    }

    public void deliverComment(Comment comment){
        commentBiz.deliverComment(comment, new ICommentsGot() {
            @Override
            public void commentsSuccess() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iEventDetail.refreshUI();
                    }
                });
            }

            @Override
            public void commentsFail() {

            }
        });
    }
}
