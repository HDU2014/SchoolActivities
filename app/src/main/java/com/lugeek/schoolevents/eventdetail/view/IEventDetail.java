package com.lugeek.schoolevents.eventdetail.view;

import android.widget.EditText;

import com.lugeek.schoolevents.eventdetail.model.Comment;
import com.lugeek.schoolevents.officeevents.bean.Event;

/**
 * Created by lugeek on 2015/8/12.
 */
public interface IEventDetail {
    void loadComments(Event event);
    void deliverComment(EditText editText);
    void refreshUI();
}
