package com.example.json.dingwei;

import android.os.Bundle;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.PermissionUtils;
import com.example.json.dingwei.base.BaseActivity;
import com.example.json.dingwei.login.LoginActivity;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;

//欢迎界面  第一个界面
public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //默认初始化
        Bmob.initialize(this, "db204405c95a2dc850f65e78c6ec9457");
        PermissionUtils.permission(PermissionConstants.LOCATION, PermissionConstants.STORAGE, PermissionConstants.SMS)
                .rationale(new PermissionUtils.OnRationaleListener() {
                    @Override
                    public void rationale(final ShouldRequest shouldRequest) {
                        shouldRequest.again(true);
                    }
                })
                .callback(new PermissionUtils.FullCallback() {
                    @Override
                    public void onGranted(List<String> permissionsGranted) {
                        BmobUser bmobUser = BmobUser.getCurrentUser(); /*获取当前user 登录界面*/
                        if (bmobUser != null) {
                            // 允许用户使用应用，跳转至登录或者主界面
                            toActivity(MainActivity.class);
                            WelcomeActivity.this.finish();
                        } else {
                            toActivity(LoginActivity.class);
                            WelcomeActivity.this.finish();
                        }
                        finish();
                    }

                    @Override
                    public void onDenied(List<String> permissionsDeniedForever,
                                         List<String> permissionsDenied) {
                        if (!permissionsDeniedForever.isEmpty()) {
                            PermissionUtils.launchAppDetailsSettings();
                        }
                    }
                }).request();

    }
}

