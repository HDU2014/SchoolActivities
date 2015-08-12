package com.lugeek.schoolevents.officeevents.view;

import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * Created by lugeek on 2015/8/9.
 */
public interface IEventsView {
    void searchClick();
    void filterTagClick(FrameLayout frameLayout, HorizontalScrollView schoolScrollView, HorizontalScrollView typeScrollView, HorizontalScrollView timeScrollView);
    void radioButtonClick(String filterName, RadioButton radioButton, TextView filterTextView, HorizontalScrollView scrollView);
    void showLoading();
    void hideLoading();
    String getSchoolFilterName();
    String getTypeFilterName();
    String getTimeFilterName();
    void refreshEventUI();
}
