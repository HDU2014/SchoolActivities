package com.lugeek.schoolevents.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.lugeek.schoolevents.R;
import com.lugeek.schoolevents.eventdetail.view.EventDetailFragment;
import com.lugeek.schoolevents.searchevents.view.SearchFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lugeek on 2015/8/11.
 */
public class SecondActivity extends ActionBarActivity{

    @Bind(R.id.second_toolbar)
    Toolbar toolbar;
    @Bind(R.id.toolbar_title)
    TextView titleTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);
        initToolbar();
        changeContainer(getIntent());
    }

    private void initToolbar(){
        //初始化Toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //titleTextView.setText("搜索");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavUtils.navigateUpFromSameTask(SecondActivity.this);
            }
        });

    }

    private void changeContainer(Intent intent){
        if ("search".equals(intent.getStringExtra("todo"))){
            titleTextView.setText("搜索");
            Fragment searchFragment = new SearchFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, searchFragment)
                    .commit();
        }else if ("eventdetail".equals(intent.getStringExtra("todo"))){
            titleTextView.setText("详情");
            Fragment eventDetailFragment = new EventDetailFragment();
            Bundle eventBundle = new Bundle();
            eventBundle.putParcelable("event", intent.getParcelableExtra("data"));
            eventDetailFragment.setArguments(eventBundle);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, eventDetailFragment)
                    .commit();
        }
    }

}
