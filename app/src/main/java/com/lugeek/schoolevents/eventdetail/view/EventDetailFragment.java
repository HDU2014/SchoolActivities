package com.lugeek.schoolevents.eventdetail.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lugeek.schoolevents.R;
import com.lugeek.schoolevents.eventdetail.Adapter.CommentAdapter;
import com.lugeek.schoolevents.eventdetail.model.Comment;
import com.lugeek.schoolevents.eventdetail.model.Comments;
import com.lugeek.schoolevents.eventdetail.presenter.EventDetailPresenter;
import com.lugeek.schoolevents.officeevents.adapter.MyAdapter;
import com.lugeek.schoolevents.officeevents.bean.Event;
import com.lugeek.schoolevents.officeevents.bean.Events;
import com.lugeek.schoolevents.utils.mylog.mylog;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventDetailFragment extends Fragment implements IEventDetail {

//    @Bind(R.id.detail_image)
//    ImageView detail_image;
//    @Bind(R.id.detail_title)
//    TextView detail_title;
//    @Bind(R.id.detail_fans)
//    TextView detail_fans;
//    @Bind(R.id.detail_participants)
//    TextView detail_participants;
//    @Bind(R.id.detail_organizer)
//    TextView detail_organizer;
//    @Bind(R.id.detail_time)
//    TextView detail_time;
//    @Bind(R.id.detail_address)
//    TextView detail_address;
//    @Bind(R.id.detail_describe)
//    TextView detail_describe;

//    @Bind(R.id.comment_edittext)
//    EditText commentEdit;
//    @Bind(R.id.comment_deliver)
//    ImageView commentDeliver;
//    @OnClick(R.id.comment_deliver) void deliver(){deliverComment(commentEdit);}

    @Bind(R.id.comment_recyclerview)
    RecyclerView recyclerView;

    private CommentAdapter commentAdapter;
    private EventDetailPresenter eventDetailPresenter = new EventDetailPresenter(this);

    public EventDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_detail, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Comments.getInstance().removeAll();
    }

    private void initView() {
        Event event = getArguments().getParcelable("event");
//        Picasso.with(getActivity()).load(event.getImageUrl()).into(detail_image);
//        detail_title.setText(event.getTitle());
//        detail_fans.setText(event.getFans() + "");
//        detail_participants.setText(event.getParticipants() + "人已报名");
//        detail_organizer.setText(event.getOrganizer());
//        detail_time.setText(event.getTime());
//        detail_address.setText(event.getAddress());
//        detail_describe.setText(event.getDescribe());

        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        commentAdapter = new CommentAdapter(getActivity(), Comments.getInstance().getComments(), event, eventDetailPresenter);
        recyclerView.setAdapter(commentAdapter);

        loadComments(event);
    }

    @Override
    public void loadComments(Event event) {
        eventDetailPresenter.loadComments(event);
    }

    @Override
    public void deliverComment(EditText editText) {
        if ("".equals(editText.getText().toString())){
            return;
        }
        Comment comment = new Comment();
        comment.setCommenter("晴子");
        comment.setContent(editText.getText().toString());
        DateFormat df = new SimpleDateFormat("HH:mm");
        df.format(new Date());
        comment.setTimestamp("12:12");
        comment.setReply("");
        editText.setText("");
        eventDetailPresenter.deliverComment(comment);
    }

    @Override
    public void refreshUI() {
        commentAdapter.notifyDataSetChanged();
    }
}
