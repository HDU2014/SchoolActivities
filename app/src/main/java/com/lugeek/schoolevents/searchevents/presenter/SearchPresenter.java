package com.lugeek.schoolevents.searchevents.presenter;

import android.os.Handler;

import com.lugeek.schoolevents.searchevents.biz.ISearchResult;
import com.lugeek.schoolevents.searchevents.biz.SearchBiz;
import com.lugeek.schoolevents.searchevents.view.ISearchView;

/**
 * Created by lugeek on 2015/8/11.
 */
public class SearchPresenter {
    private ISearchView searchView;
    private SearchBiz searchBiz;
    private Handler handler = new Handler();

    public SearchPresenter(ISearchView searchView) {
        this.searchView = searchView;
        searchBiz = new SearchBiz();
    }

    public void searchEvents(String content){
        searchView.showLoading();
        searchBiz.searchEvents(content, new ISearchResult() {
            @Override
            public void searchSuccess() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        searchView.hideLoading();
                        searchView.refreshUI();
                    }
                });
            }

            @Override
            public void searchFail() {

            }
        });
    }
}
