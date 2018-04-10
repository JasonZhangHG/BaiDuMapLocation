package com.example.json.dingwei;

import android.os.Bundle;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.PermissionUtils;
import com.example.json.dingwei.base.BaseActivity;
import com.example.json.dingwei.login.LoginActivity;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;

public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //第一：默认初始化
        Bmob.initialize(this, "19fb1a7ca34b632c3d283aebc5e14864");
        PermissionUtils.permission(PermissionConstants.LOCATION)
                .rationale(new PermissionUtils.OnRationaleListener() {
                    @Override
                    public void rationale(final ShouldRequest shouldRequest) {
                        shouldRequest.again(true);
                    }
                })
                .callback(new PermissionUtils.FullCallback() {
                    @Override
                    public void onGranted(List<String> permissionsGranted) {
                        doInUI(new Runnable() {
                            @Override
                            public void run() {

                                PermissionUtils.permission(PermissionConstants.STORAGE)
                                        .rationale(new PermissionUtils.OnRationaleListener() {
                                            @Override
                                            public void rationale(final ShouldRequest shouldRequest) {
                                                shouldRequest.again(true);
                                            }
                                        })
                                        .callback(new PermissionUtils.FullCallback() {
                                            @Override
                                            public void onGranted(List<String> permissionsGranted) {
                                                PermissionUtils.permission(PermissionConstants.SMS)
                                                        .rationale(new PermissionUtils.OnRationaleListener() {
                                                            @Override
                                                            public void rationale(final ShouldRequest shouldRequest) {
                                                                shouldRequest.again(true);
                                                            }
                                                        })
                                                        .callback(new PermissionUtils.FullCallback() {
                                                            @Override
                                                            public void onGranted(List<String> permissionsGranted) {
                                                                BmobUser bmobUser = BmobUser.getCurrentUser();
                                                                if (bmobUser != null) {
                                                                    // 允许用户使用应用
                                                                    toActivity(MainActivity.class);
                                                                    WelcomeActivity.this.finish();
                                                                } else {
                                                                    toActivity(LoginActivity.class);
                                                                    WelcomeActivity.this.finish();
                                                                }
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

                                            @Override
                                            public void onDenied(List<String> permissionsDeniedForever,
                                                                 List<String> permissionsDenied) {
                                                if (!permissionsDeniedForever.isEmpty()) {
                                                    PermissionUtils.launchAppDetailsSettings();
                                                }
                                            }
                                        }).request();

                            }
                        }, 100);
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

