package com.wei42.weixin.service;


import com.wei42.weixin.domain.AccessToken;
import com.wei42.weixin.domain.user.OpenUser;
import com.wei42.weixin.domain.user.WeixinUser;
import com.wei42.weixin.util.WeixinConstants;
import com.wei42.weixin.util.WeixinUtil;
import net.sf.json.JSONObject;

public class UserService {
	
	public static final String USER_GET_URL = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=";

    public static final String USER_GET_ID_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	
	public static void main(String[] args) {
        UserService userService = new UserService();
        WeixinUser user = userService.getWeixinUserbyOpenId("o7elat4UcAdxfQFf9ndT6P9h_YOw");
        System.out.println(user);
	}

    public OpenUser getAllUser(){
        AccessToken token = WeixinUtil.getAccessToken(WeixinConstants.APP_ID, WeixinConstants.SECRET);
        if(token != null && token.getToken() != null){
            String user_url = USER_GET_URL.replace("ACCESS_TOKEN", token.getToken());
            JSONObject jsonObject = WeixinUtil.httpsRequest(user_url, "GET", null);
            OpenUser ou = (OpenUser) JSONObject.toBean(jsonObject, OpenUser.class);
            return ou;
        }
        return null;
    }


    public WeixinUser getWeixinUserbyOpenId(String openid){

        AccessToken token = WeixinUtil.getAccessToken(WeixinConstants.APP_ID, WeixinConstants.SECRET);
        if(token != null && token.getToken() != null){
            String user_url = USER_GET_ID_URL.replace("ACCESS_TOKEN", token.getToken());
            user_url = user_url.replace("OPENID",openid);
            JSONObject jsonObject = WeixinUtil.httpsRequest(user_url, "GET", null);
            WeixinUser weixinUser = (WeixinUser) JSONObject.toBean(jsonObject, WeixinUser.class);
            return weixinUser;
        }
        return null;
    }


}
