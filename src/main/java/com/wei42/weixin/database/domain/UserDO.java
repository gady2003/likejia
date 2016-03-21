package com.wei42.weixin.database.domain;

import java.util.Date;

/**
 * Created by xuanxiao on 2016-03-21.
 */
public class UserDO {

    //系统用户id
    private int id;

    //微信用户id
    private  String wxid;

    //微信用户nick
    private  String wxnick;

    private String telphone;

    // online offline unsubscribe;
    private String status;

    private String address;
    //当前位置
    private String curaddress;

    private Date createtime;

    public String getWxid() {
        return wxid;
    }

    public void setWxid(String wxid) {
        this.wxid = wxid;
    }

    public String getWxnick() {
        return wxnick;
    }

    public void setWxnick(String wxnick) {
        this.wxnick = wxnick;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCuraddress() {
        return curaddress;
    }

    public void setCuraddress(String curaddress) {
        this.curaddress = curaddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}
