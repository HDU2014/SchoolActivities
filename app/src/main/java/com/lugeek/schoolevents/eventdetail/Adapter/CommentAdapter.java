package com.lugeek.schoolevents.eventdetail.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lugeek.schoolevents.R;

import com.lugeek.schoolevents.eventdetail.model.Comment;
import com.lugeek.schoolevents.eventdetail.presenter.EventDetailPresenter;
import com.lugeek.schoolevents.officeevents.bean.Event;
import com.lugeek.schoolevents.ui.activity.SecondActivity;
import com.lugeek.schoolevents.utils.mylog.mylog;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lugeek on 2015/8/12.
 */
public class CommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    private List<Comment> comments;
    private Event event;
    private EventDetailPresenter eventDetailPresenter;

    public static enum ITEM_TYPE {
        ITEM_TYPE_HEAD, ITEM_TYPE_CONTENT
    }

    public CommentAdapter(Context context, List<Comment> comments, Event event, EventDetailPresenter eventDetailPresenter) {
        this.context = context;
        this.comments = comments;
        this.event = event;
        this.eventDetailPresenter = eventDetailPresenter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==ITEM_TYPE.ITEM_TYPE_HEAD.ordinal()){
            return  new HeadViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_comment_header, parent, false));
        }else{
            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_comment_card, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeadViewHolder){
            Picasso.with(context).load(event.getImageUrl()).into(((HeadViewHolder)holder).detail_image);
            ((HeadViewHolder)holder).detail_title.setText(event.getTitle());
            ((HeadViewHolder)holder).detail_fans.setText(event.getFans()+"");
            if (event.isNeedSignup()){
                ((HeadViewHolder)holder).detail_participants.setText(event.getParticipants()+"人已报名/限额"+event.getAllNum()+"人");
            }else{
                ((HeadViewHolder)holder).detail_participants.setText("无需报名");
            }
            ((HeadViewHolder)holder).detail_organizer.setText(event.getOrganizer());
            ((HeadViewHolder)holder).detail_time.setText(event.getTime());
            ((HeadViewHolder)holder).detail_address.setText(event.getAddress());
            ((HeadViewHolder)holder).detail_describe.setText(event.getDescribe());
        }else{
            //多了headview，数据向前移位
            position=position-1;
            ((MyViewHolder)holder).commentImage.setImageResource(R.drawable.clear);
            if (!comments.get(position).getReply().equals("")){
                ((MyViewHolder)holder).commentName.setText(comments.get(position).getCommenter()+"回复"+comments.get(position).getReply());
            }else {
                ((MyViewHolder)holder).commentName.setText(comments.get(position).getCommenter());
            }
            ((MyViewHolder)holder).commentTime.setText(comments.get(position).getTimestamp());
            ((MyViewHolder)holder).commentContent.setText(comments.get(position).getContent());
        }
    }

    @Override
    public int getItemCount() {
        //多了headview
        return comments.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        return position==0?ITEM_TYPE.ITEM_TYPE_HEAD.ordinal():ITEM_TYPE.ITEM_TYPE_CONTENT.ordinal();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @Bind(R.id.comment_image)
        ImageView commentImage;
        @Bind(R.id.comment_name)
        TextView commentName;
        @Bind(R.id.comment_time) TextView commentTime;
        @Bind(R.id.comment_content) TextView commentContent;
        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onClick(View v) {
            mylog.toast(context, "click"+getLayoutPosition());
        }
    }

    class HeadViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.detail_image)
        ImageView detail_image;
        @Bind(R.id.detail_title)
        TextView detail_title;
        @Bind(R.id.detail_fans)
        TextView detail_fans;
        @Bind(R.id.detail_participants)
        TextView detail_participants;
        @Bind(R.id.detail_organizer)
        TextView detail_organizer;
        @Bind(R.id.detail_time)
        TextView detail_time;
        @Bind(R.id.detail_address)
        TextView detail_address;
        @Bind(R.id.detail_describe)
        TextView detail_describe;
        @Bind(R.id.comment_edittext)
        EditText commentEdit;
        @Bind(R.id.comment_deliver)
        ImageView commentDeliver;
        @OnClick(R.id.comment_deliver) void deliver(){
            if ("".equals(commentEdit.getText().toString())){
                return;
            }
            Comment comment = new Comment();
            comment.setCommenter("晴子");
            comment.setContent(commentEdit.getText().toString());
            DateFormat df = new SimpleDateFormat("HH:mm");
            df.format(new Date());
            comment.setTimestamp("12:12");
            comment.setReply("");
            commentEdit.setText("");
            eventDetailPresenter.deliverComment(comment);
        }
        public HeadViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
