package com.example.json.dingwei;


import cn.bmob.v3.BmobObject;

/**
 * Created by Json on 2017/6/21.
 */

public class WeiZhiBean extends BmobObject {

    private long userID;//用户ID
    private String netKind;//获取定位的方式
    private String time;//时间
    private String address;//位置
    private String locationDescribe;//附近标志建筑物
    private double longitude;//获取经度信息
    private double latitude;//获取纬度信息

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public String getNetKind() {
        return netKind;
    }

    public void setNetKind(String netKind) {
        this.netKind = netKind;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocationDescribe() {
        return locationDescribe;
    }

    public void setLocationDescribe(String locationDescribe) {
        this.locationDescribe = locationDescribe;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "WeiZhiBean{" +
                "userID=" + userID +
                ", netKind='" + netKind + '\'' +
                ", time='" + time + '\'' +
                ", address='" + address + '\'' +
                ", locationDescribe='" + locationDescribe + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }

}
