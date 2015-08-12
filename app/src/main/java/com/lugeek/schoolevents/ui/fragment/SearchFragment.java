package com.lugeek.schoolevents.ui.fragment;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lugeek.schoolevents.R;
import com.lugeek.schoolevents.officeevents.adapter.MyAdapter;
import com.lugeek.schoolevents.officeevents.bean.Events;
import com.lugeek.schoolevents.searchevents.presenter.SearchPresenter;
import com.lugeek.schoolevents.searchevents.view.ISearchView;
import com.lugeek.schoolevents.utils.mylog.mylog;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements ISearchView{


    @Bind(R.id.searchEditText)
    EditText searchEditText;
    @Bind(R.id.clearButton)
    ImageView clearButton;
    @OnClick(R.id.clearButton) void clear(){clearText(searchEditText);}
    @Bind(R.id.search_swipe)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.search_recyclerview)
    RecyclerView recyclerView;

    private MyAdapter myAdapter;
    private SearchPresenter searchPresenter = new SearchPresenter(this);

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    public void initView(){
        //EditText字符变动监听器
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (clearButton.getVisibility()==View.INVISIBLE && (start+count)>0)
                    clearButton.setVisibility(View.VISIBLE);
                if (clearButton.getVisibility()==View.VISIBLE && (start+count)==0){
                    clearButton.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //EditText的imeoptions监听器
        searchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    if (searchEditText.getText().toString().equals("")){
                        Toast.makeText(getActivity(), "搜索内容不能为空", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                    search(searchEditText.getText().toString());
                    hideIme();
                    handled = true;
                }
                return handled;
            }
        });

        //取消下拉
        swipeRefreshLayout.setEnabled(false);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        //recyclerview初始化
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        myAdapter = new MyAdapter(getActivity(), Events.getInstance().setTestEvents());
        recyclerView.setAdapter(myAdapter);

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
    public void search(String content) {
        searchPresenter.searchEvents(content);
    }

    @Override
    public void clearText(EditText searchEditText) {
        searchEditText.setText("");
    }

    @Override
    public void refreshUI() {
        myAdapter.notifyDataSetChanged();
    }

    private void hideIme(){
        View view = getActivity().getCurrentFocus();
        if (view != null){
            InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
