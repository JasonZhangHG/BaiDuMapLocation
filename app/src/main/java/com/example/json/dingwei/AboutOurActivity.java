package com.example.json.dingwei;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.example.json.dingwei.base.BaseActivity;
import com.example.json.dingwei.constants.ConstKey;
import com.example.json.dingwei.utils.PreferenceUtil;
import com.example.json.dingwei.view.MarqueeTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutOurActivity extends BaseActivity {

    @BindView(R.id.flAboutOurActivityBack)
    FrameLayout flAboutOurActivityBack;
    @BindView(R.id.ivAboutOurActivityUserID)
    MarqueeTextView ivAboutOurActivityUserID;
    @BindView(R.id.title)
    RelativeLayout title;
    @BindView(R.id.barTitle)
    Toolbar barTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_our);
        ButterKnife.bind(this);
        flAboutOurActivityBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AboutOurActivity.this.finish();
            }
        });

        ivAboutOurActivityUserID.setText("用户编号为：" + PreferenceUtil.getInstance().getLong(ConstKey.KEY_LAST_LOGIN_TIME));
    }
}
