package com.lugeek.schoolevents.officeevents.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lugeek.schoolevents.R;
import com.lugeek.schoolevents.officeevents.bean.Event;
import com.lugeek.schoolevents.ui.activity.SecondActivity;
import com.lugeek.schoolevents.utils.mylog.mylog;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lugeek on 2015/8/10.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    private Context context;
    private List<Event> events;
    public MyAdapter(Context context, List<Event> events) {
        this.context = context;
        this.events = events;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_event_card, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        Picasso.with(context).load(events.get(i).getImageUrl()).into(myViewHolder.eventImage);
        myViewHolder.eventTitle.setText(events.get(i).getTitle());
        myViewHolder.eventTime.setText(events.get(i).getTime());
        myViewHolder.eventAddress.setText(events.get(i).getAddress());
        myViewHolder.eventPersons.setText(events.get(i).getParticipants()+"人已参加");
        myViewHolder.eventStatus.setText(events.get(i).isNeedSignup()?"去报名":"无需报名");
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @Bind(R.id.event_image) ImageView eventImage;
        @Bind(R.id.event_title) TextView eventTitle;
        @Bind(R.id.event_time) TextView eventTime;
        @Bind(R.id.event_address) TextView eventAddress;
        @Bind(R.id.event_persons) TextView eventPersons;
        @Bind(R.id.event_status) TextView eventStatus;
        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, SecondActivity.class);
            intent.putExtra("data", events.get(getLayoutPosition()));
            intent.putExtra("todo", "eventdetail");
            context.startActivity(intent);
        }
    }
}
