package com.lugeek.schoolevents.searchevents.view;

import android.widget.EditText;

/**
 * Created by lugeek on 2015/8/11.
 */
public interface ISearchView {
    void showLoading();
    void hideLoading();
    void search(String content);
    void clearText(EditText searchEditText);
    void refreshUI();
}
