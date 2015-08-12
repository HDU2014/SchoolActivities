package wtt.com.message.ui.activity;

import android.content.Intent;
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
public class LoginActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.iv_username)
    ImageView ivUsername;
    @Bind(R.id.et_username)
    EditText etUsername;
    @Bind(R.id.iv_password)
    ImageView ivPassword;
    @Bind(R.id.bt_login)
    Button btLogin;
    @Bind(R.id.tv_forgetpass)
    TextView tvForgetpass;
    @Bind(R.id.tv_regist)
    TextView tvRegist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        ButterKnife.bind(this);
        initToolbar();
        tvRegist.setOnClickListener(this);

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
                NavUtils.navigateUpFromSameTask(LoginActivity.this);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_regist:
                Intent intent=new Intent(this,RegistActivity.class);
                startActivity(intent);
                break;
        }
    }
}
