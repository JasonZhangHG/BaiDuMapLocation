package com.example.json.dingwei;

import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.Poi;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Json on 2017/6/21.
 */

public class MyLocationListener implements BDLocationListener {

    @Override
    public void onReceiveLocation(BDLocation location) {

        StringBuffer sb = new StringBuffer(256);

        sb.append("time : ");
        sb.append(location.getTime());    //获取定位时间

        sb.append("\nerror code : ");
        sb.append(location.getLocType());    //获取类型类型

        sb.append("\nlatitude : ");
        sb.append(location.getLatitude());    //获取纬度信息

        sb.append("\nlontitude : ");
        sb.append(location.getLongitude());    //获取经度信息

        sb.append("\nradius : ");
        sb.append(location.getRadius());    //获取定位精准度


        if (location.getLocType() == BDLocation.TypeGpsLocation) {

            // GPS定位结果
            sb.append("\nspeed : ");
            sb.append(location.getSpeed());    // 单位：公里每小时

            sb.append("\nsatellite : ");
            sb.append(location.getSatelliteNumber());    //获取卫星数

            sb.append("\nheight : ");
            sb.append(location.getAltitude());    //获取海拔高度信息，单位米

            sb.append("\ndirection : ");
            sb.append(location.getDirection());    //获取方向信息，单位度

            sb.append("\naddr : ");
            sb.append(location.getAddrStr());    //获取地址信息

            sb.append("\ndescribe : ");
            sb.append("gps定位成功");

            WeiZhiBean weiZhiBean = new WeiZhiBean();
            weiZhiBean.setUserID(1);
            weiZhiBean.setAddress(location.getAddrStr());
            weiZhiBean.setTime(formatTime());
            weiZhiBean.setLocationDescribe(location.getLocationDescribe().toString());
            weiZhiBean.setNetKind("GPS定位成功");
            weiZhiBean.setLatitude(location.getLatitude());
            weiZhiBean.setLongitude(location.getLongitude());

            EventBus.getDefault().post(weiZhiBean);

            weiZhiBean.save(new SaveListener<String>() {
                @Override
                public void done(String objectId, BmobException e) {
                    if (e == null) {
                    } else {
                    }
                }
            });

        } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {

            // 网络定位结果
            sb.append("\naddr : ");
            sb.append(location.getAddrStr());    //获取地址信息
            sb.append("\noperationers : ");
            sb.append(location.getOperators());    //获取运营商信息
            sb.append("\ndescribe : ");
            sb.append("网络定位成功");

            WeiZhiBean weiZhiBean = new WeiZhiBean();
            weiZhiBean.setUserID(1);
            weiZhiBean.setAddress(location.getAddrStr());
            weiZhiBean.setTime(formatTime());
            weiZhiBean.setLocationDescribe(location.getLocationDescribe().toString());
            weiZhiBean.setNetKind("网络定位成功");
            weiZhiBean.setLatitude(location.getLatitude());
            weiZhiBean.setLongitude(location.getLongitude());

            EventBus.getDefault().post(weiZhiBean);

            weiZhiBean.save(new SaveListener<String>() {
                @Override
                public void done(String objectId, BmobException e) {
                    if (e == null) {
                    } else {
                    }
                }
            });


        } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {
            // 离线定位结果
            sb.append("\ndescribe : ");
            sb.append("离线定位成功，离线定位结果也是有效的");

        } else if (location.getLocType() == BDLocation.TypeServerError) {
            sb.append("\ndescribe : ");
            sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");


        } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
            sb.append("\ndescribe : ");
            sb.append("网络不同导致定位失败，请检查网络是否通畅");

        } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
            sb.append("\ndescribe : ");
            sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
        }

        sb.append("\nlocationdescribe : ");
        sb.append(location.getLocationDescribe());    //位置语义化信息

        List<Poi> list = location.getPoiList();    // POI数据
        if (list != null) {
            sb.append("\npoilist size = : ");
            sb.append(list.size());
            for (Poi p : list) {
                sb.append("\npoi= : ");
                sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
            }
        }
    }

    //返回当前的时间
    public String formatTime() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(d);
        return time;
    }

    @Override
    public void onConnectHotSpotMessage(String s, int i) {
    }
}

