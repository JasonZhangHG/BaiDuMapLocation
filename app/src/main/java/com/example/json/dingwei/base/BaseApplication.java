package com.example.json.dingwei.base;

import android.app.Application;

import com.example.json.dingwei.utils.PreferenceUtil;
import com.example.json.dingwei.utils.ToastHelper;

/**
 * Created by Json on 2017/7/3.
 */

public class BaseApplication  extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        ToastHelper.init(this);
        PreferenceUtil.initInstance(getApplicationContext(), PreferenceUtil.MODE_ENCRYPT_ALL);
    }
}
