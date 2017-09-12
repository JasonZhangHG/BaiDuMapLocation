package com.example.json.dingwei;

import android.content.Intent;
import android.os.Bundle;

import com.example.json.dingwei.base.BaseActivity;

import cn.bmob.v3.Bmob;

public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //第一：默认初始化
        Bmob.initialize(this, "19fb1a7ca34b632c3d283aebc5e14864");
//        long lastTime = PreferenceUtil.getInstance().getLong(ConstKey.KEY_LAST_LOGIN_TIME);
//        if (lastTime == 0) {
//            toActivity(Login.class);
//        } else {
            doInUI(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                    WelcomeActivity.this.finish();
                }
            }, 100);

        }

    }
//}
