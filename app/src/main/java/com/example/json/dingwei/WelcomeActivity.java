package com.example.json.dingwei;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.json.dingwei.base.BaseActivity;
import com.example.json.dingwei.constants.ConstKey;
import com.example.json.dingwei.utils.PreferenceUtil;

public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        long lastTime = PreferenceUtil.getInstance().getLong(ConstKey.KEY_LAST_LOGIN_TIME);
        if (lastTime == 0) {
            toActivity(Login.class);
        } else {
            doInUI(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                    WelcomeActivity.this.finish();
                }
            }, 100);

        }

    }
}
