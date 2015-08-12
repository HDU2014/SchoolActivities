package wtt.com.message.ui.activity;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lugeek.schoolevents.R;

import Application.BaseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2015/8/12.
 */
public class RegistActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.tv_haveaccount)
    TextView tvHaveaccount;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.iv_username)
    ImageView ivUsername;
    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.bt_getYZM)
    Button btGetYZM;
    @Bind(R.id.iv_password)
    ImageView ivPassword;
    @Bind(R.id.et_YZM)
    EditText etYZM;
    @Bind(R.id.iv_password1)
    ImageView ivPassword1;
    @Bind(R.id.et_pass)
    EditText etPass;
    @Bind(R.id.bt_regist)
    Button btRegist;
    @Bind(R.id.iv_qq)
    ImageView ivQq;
    @Bind(R.id.iv_weixin)
    ImageView ivWeixin;
    @Bind(R.id.iv_weibo)
    ImageView ivWeibo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registactivity);
        ButterKnife.bind(this);
        initToolbar();
        tvHaveaccount.setOnClickListener(this);
    }

    /**
     * ≥ı ºªØToolbar
     */
    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavUtils.navigateUpFromSameTask(RegistActivity.this);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_haveaccount:
                onBackPressed();
                break;
        }
    }
}
