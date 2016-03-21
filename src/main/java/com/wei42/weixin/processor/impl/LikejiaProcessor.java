package com.wei42.weixin.processor.impl;

import com.wei42.weixin.database.dao.WeixinUserDAO;
import com.wei42.weixin.database.domain.UserDO;
import com.wei42.weixin.domain.message.response.ResponseTextMessage;
import com.wei42.weixin.domain.user.WeixinUser;
import com.wei42.weixin.processor.Processor;
import com.wei42.weixin.service.UserService;
import com.wei42.weixin.util.MessageUtil;
import com.wei42.weixin.util.WeixinConstants;

import java.util.Date;
import java.util.Map;

/**
 * Created by xuanxiao on 2016-03-21.
 */
public class LikejiaProcessor implements Processor {

    private  UserService userService = new UserService();

    private  WeixinUserDAO weixinUserDAO = new WeixinUserDAO();
    @Override
    public String processTextMessage(Map<String, String> map) {
        String fromUserName = map.get("FromUserName");
        String toUserName = map.get("ToUserName");
        ResponseTextMessage textmessage = new ResponseTextMessage();
        textmessage.setFromUserName(toUserName);
        textmessage.setToUserName(fromUserName);
        textmessage.setCreateTime(System.currentTimeMillis());
        textmessage.setMsgType("text");
        textmessage.setContent("欢迎进入狸客快修,点击下面按钮进入.");
        return MessageUtil.textMessage2Xml(textmessage);
    }

    @Override
    public String processSubscribeMessage(Map<String, String> map) {
        String fromUserName = map.get("FromUserName");

        try {
            WeixinUser weixinUser = userService.getWeixinUserbyOpenId(fromUserName);
            if(weixinUser != null){
                UserDO userDO = new UserDO();
                userDO.setWxid(weixinUser.getOpenid());
                userDO.setCreatetime(new Date());
                userDO.setAddress(weixinUser.getCountry() + weixinUser.getProvince() + weixinUser.getCity());
                userDO.setWxnick(weixinUser.getNickname());
                int subs = weixinUser.getSubscribe();
                if(subs == 0){
                    userDO.setStatus(WeixinConstants.UNSUBSCRIBE_STATUS);
                }else{
                    userDO.setStatus(WeixinConstants.ON_LINE_STATUS);
                }
                weixinUserDAO.insertWeixinUser(userDO);
            }
        } catch (Exception e) {//ignore
            e.printStackTrace();
        }

        String toUserName = map.get("ToUserName");
        ResponseTextMessage textmessage = new ResponseTextMessage();
        textmessage.setFromUserName(toUserName);
        textmessage.setToUserName(fromUserName);
        textmessage.setCreateTime(System.currentTimeMillis());
        textmessage.setMsgType("text");
        textmessage.setContent("欢迎您订阅狸客快修公众号");
        return MessageUtil.textMessage2Xml(textmessage);
    }

    @Override
    public String processUnsubscribeMessage(Map<String, String> map) {
        String fromUserName = map.get("FromUserName");
        UserDO userDO = new UserDO();
        userDO.setWxid(fromUserName);
        userDO.setStatus(WeixinConstants.UNSUBSCRIBE_STATUS);
        weixinUserDAO.updateWeixinUserStatus(userDO);
        return null;
    }

    @Override
    public String processClickMessage(Map<String, String> map) {
        return null;
    }
}
