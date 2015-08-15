package com.lugeek.schoolevents.eventdetail.model;

import com.lugeek.schoolevents.officeevents.bean.Event;

/**
 * Created by lugeek on 2015/8/12.
 */
public interface ICommentBiz {
    void refreshComments(Event event, ICommentsGot iCommentsGot);
    void deliverComment(Comment comment, ICommentsGot iCommentsGot);

}
