package com.lugeek.schoolevents.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.lugeek.schoolevents.R;
import com.lugeek.schoolevents.officeevents.view.OfficeFragment;
import com.lugeek.schoolevents.ui.fragment.TestFragment;
import com.lugeek.schoolevents.ui.widget.CustomBottom;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends ActionBarActivity {

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.toolbar_title) TextView toolbarTitle;
    @Bind(R.id.office_tab) CustomBottom office_tab;
    @Bind(R.id.personal_tab) CustomBottom personal_tab;
    @Bind(R.id.message_tab) CustomBottom message_tab;
    @Bind(R.id.me_tab) CustomBottom me_tab;
    @Bind(R.id.viewpager) ViewPager viewPager;
    @OnClick(R.id.office_tab) void office_click(){
        clickTabBiz(0);
    }
    @OnClick(R.id.personal_tab) void personal_click(){
        clickTabBiz(1);
    }
    @OnClick(R.id.message_tab) void message_click(){
        clickTabBiz(2);
    }
    @OnClick(R.id.me_tab) void me_click(){
        clickTabBiz(3);
    }

    private List<CustomBottom> tabs = new ArrayList<CustomBottom>();
    private List<Fragment> fragments = new ArrayList<Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //初始化Toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbarTitle.setText(getResources().getStringArray(R.array.pagename)[0]);

        initView();
    }

    private void initView() {
        //ViewPager滚动变色
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //通过打Log找规律得到，position总是指向左边的tab
                if (positionOffset > 0) {
                    CustomBottom left = tabs.get(position);
                    CustomBottom right = tabs.get(position + 1);
                    left.setIconAlpha(1 - positionOffset);
                    right.setIconAlpha(positionOffset);
                }
            }

            @Override
            public void onPageSelected(int position) {
                toolbarTitle.setText(getResources().getStringArray(R.array.pagename)[position]);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        tabs.add(office_tab);
        tabs.add(personal_tab);
        tabs.add(message_tab);
        tabs.add(me_tab);
        //初始选中第一个
        office_tab.setIconAlpha(1.0f);

        OfficeFragment officeFragment = new OfficeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("testfragment", "First");
        officeFragment.setArguments(bundle);
        fragments.add(officeFragment);
        TestFragment test2Fragment = new TestFragment();
        Bundle bundle2 = new Bundle();
        bundle2.putString("testfragment", "Second");
        test2Fragment.setArguments(bundle2);
        fragments.add(test2Fragment);
        TestFragment test3Fragment = new TestFragment();
        Bundle bundle3 = new Bundle();
        bundle3.putString("testfragment", "Third");
        test3Fragment.setArguments(bundle3);
        fragments.add(test3Fragment);
        TestFragment test4Fragment = new TestFragment();
        Bundle bundle4 = new Bundle();
        bundle4.putString("testfragment", "Fourth");
        test4Fragment.setArguments(bundle4);
        fragments.add(test4Fragment);

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });

    }

    /**
     * 底部Tab点击逻辑
     * @param position Tab位置
     */
    public void clickTabBiz(int position){
        //全部设成透明
        for (int i = 0; i < tabs.size(); i++) {
            tabs.get(i).setIconAlpha(0);
        }

        //被点击的Tab设成不透明，并改变ViewPager
        tabs.get(position).setIconAlpha(1.0f);
        viewPager.setCurrentItem(position, false);
    }
}
