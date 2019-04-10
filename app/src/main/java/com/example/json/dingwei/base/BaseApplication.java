package com.example.json.dingwei.base;

import android.app.Application;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.blankj.utilcode.util.Utils;
import com.example.json.dingwei.utils.PreferenceUtil;
import com.example.json.dingwei.utils.ToastHelper;


public class BaseApplication  extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        ToastHelper.init(this);
        PreferenceUtil.initInstance(getApplicationContext(), PreferenceUtil.MODE_ENCRYPT_ALL);
        Utils.init(getApplicationContext());
        SDKInitializer.initialize(this);
        SDKInitializer.setCoordType(CoordType.BD09LL);
    }
}
