package com.lugeek.schoolevents.officeevents.view;


import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.lugeek.schoolevents.R;
import com.lugeek.schoolevents.officeevents.adapter.MyAdapter;
import com.lugeek.schoolevents.officeevents.bean.Events;
import com.lugeek.schoolevents.officeevents.presenter.EventsPresenter;
import com.lugeek.schoolevents.officeevents.view.IEventsView;
import com.lugeek.schoolevents.ui.activity.SecondActivity;
import com.lugeek.schoolevents.utils.mylog.mylog;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class OfficeFragment extends Fragment implements IEventsView {

    @OnClick(R.id.search_bar)
    void searchButton() {
        searchClick();
    }

    @Bind(R.id.school_name)
    TextView schoolTextView;
    @Bind(R.id.type_name)
    TextView typeTextView;
    @Bind(R.id.time_name)
    TextView timeTextView;
    @OnClick({R.id.school_filter, R.id.type_filter, R.id.time_filter})
    void filterClick(FrameLayout frameLayout) {
        filterTagClick(frameLayout, schoolScrollView, typeScrollView, timeScrollView);
    }

    @Bind(R.id.schools)
    HorizontalScrollView schoolScrollView;
    @OnClick({R.id.schoolall, R.id.hangdian, R.id.ligong, R.id.jiliang, R.id.hangshi, R.id.gongshang, R.id.chuanmei})
    void schoolClick(RadioButton rButton) {
        radioButtonClick("学校", rButton, schoolTextView, schoolScrollView);
    }

    @Bind(R.id.types)
    HorizontalScrollView typeScrollView;
    @OnClick({R.id.typeall, R.id.jiangzuo, R.id.zhaopin, R.id.yundong, R.id.huwai, R.id.shetuan, R.id.lianyi})
    void typeClick(RadioButton rButton) {
        radioButtonClick("类型", rButton, typeTextView, typeScrollView);
    }

    @Bind(R.id.times)
    HorizontalScrollView timeScrollView;
    @OnClick({R.id.timeall, R.id.today, R.id.tomorrow, R.id.days3, R.id.days7, R.id.days30})
    void timeClick(RadioButton rButton) {
        radioButtonClick("时间", rButton, timeTextView, timeScrollView);
    }

    @Bind(R.id.events_swipe)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.events_recyclerview)
    RecyclerView recyclerView;

    private MyAdapter myAdapter;
    private EventsPresenter eventsPresenter = new EventsPresenter(this);

    public OfficeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_office, container, false);
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
        ButterKnife.unbind(this);
    }

    private void initView() {

        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                eventsPresenter.refreshEvents();
            }
        });

        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        myAdapter = new MyAdapter(getActivity(), Events.getInstance().setTestEvents());
        recyclerView.setAdapter(myAdapter);
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (layoutManager.findFirstCompletelyVisibleItemPosition() == 0) {
                    swipeRefreshLayout.setEnabled(true);
                } else {
                    swipeRefreshLayout.setEnabled(false);
                }
            }
        });
    }

    @Override
    public void searchClick() {
        Intent intent = new Intent(getActivity(), SecondActivity.class);
        intent.putExtra("todo", "search");
        startActivity(intent);
    }

    @Override
    public void filterTagClick(FrameLayout frameLayout, HorizontalScrollView schoolScrollView, HorizontalScrollView typeScrollView, HorizontalScrollView timeScrollView) {
        switch (frameLayout.getId()) {
            case R.id.school_filter:
                if (schoolScrollView.getVisibility() == View.VISIBLE) {
                    schoolScrollView.setVisibility(View.GONE);
                } else {
                    if (typeScrollView.getVisibility() == View.VISIBLE)
                        typeScrollView.setVisibility(View.GONE);
                    if (timeScrollView.getVisibility() == View.VISIBLE)
                        timeScrollView.setVisibility(View.GONE);
                    schoolScrollView.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.type_filter:
                if (typeScrollView.getVisibility() == View.VISIBLE) {
                    typeScrollView.setVisibility(View.GONE);
                } else {
                    if (schoolScrollView.getVisibility() == View.VISIBLE)
                        schoolScrollView.setVisibility(View.GONE);
                    if (timeScrollView.getVisibility() == View.VISIBLE)
                        timeScrollView.setVisibility(View.GONE);
                    typeScrollView.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.time_filter:
                if (timeScrollView.getVisibility() == View.VISIBLE) {
                    timeScrollView.setVisibility(View.GONE);
                } else {
                    if (schoolScrollView.getVisibility() == View.VISIBLE)
                        schoolScrollView.setVisibility(View.GONE);
                    if (typeScrollView.getVisibility() == View.VISIBLE)
                        typeScrollView.setVisibility(View.GONE);
                    timeScrollView.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    @Override
    public void radioButtonClick(String filterName, RadioButton radioButton, TextView filterTextView, HorizontalScrollView scrollView) {
        filterTextView.setText(radioButton.getText().equals("不限") ? filterName : radioButton.getText().toString());
        scrollView.setVisibility(View.GONE);
    }

    @Override
    public void showLoading() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public String getSchoolFilterName() {
        return schoolTextView.getText().toString();
    }

    @Override
    public String getTypeFilterName() {
        return typeTextView.getText().toString();
    }

    @Override
    public String getTimeFilterName() {
        return timeTextView.getText().toString();
    }

    @Override
    public void refreshEventUI() {
        myAdapter.notifyDataSetChanged();
    }
}
