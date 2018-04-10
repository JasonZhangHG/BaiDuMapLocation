package com.example.json.dingwei;

import cn.bmob.v3.BmobUser;

/**
 * Created by jason on 2018/1/19.
 */
public class DBTaskManagerUserInfoBean extends BmobUser {

    public long creatTimeAsId;//录入的具体数据的时间作为ID
    public String name;
    public String old;
    public String tellPhone;
    public String mail;
    public String typeOfWork;
    public int typeOfWorkManager;

    public long getCreatTimeAsId() {
        return creatTimeAsId;
    }

    public void setCreatTimeAsId(long creatTimeAsId) {
        this.creatTimeAsId = creatTimeAsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOld() {
        return old;
    }

    public void setOld(String old) {
        this.old = old;
    }

    public String getTellPhone() {
        return tellPhone;
    }

    public void setTellPhone(String tellPhone) {
        this.tellPhone = tellPhone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTypeOfWork() {
        return typeOfWork;
    }

    public void setTypeOfWork(String typeOfWork) {
        this.typeOfWork = typeOfWork;
    }

    public int getTypeOfWorkManager() {
        return typeOfWorkManager;
    }

    public void setTypeOfWorkManager(int typeOfWorkManager) {
        this.typeOfWorkManager = typeOfWorkManager;
    }

    @Override
    public String toString() {
        return "DBTaskManagerUserInfoBean{" +
                "creatTimeAsId=" + creatTimeAsId +
                ", name='" + name + '\'' +
                ", old='" + old + '\'' +
                ", tellPhone='" + tellPhone + '\'' +
                ", mail='" + mail + '\'' +
                ", typeOfWork='" + typeOfWork + '\'' +
                ", typeOfWorkManager=" + typeOfWorkManager +
                '}';
    }
}
