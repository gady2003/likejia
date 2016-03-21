package com.wei42.weixin.domain.message.request;


import com.wei42.weixin.domain.message.BaseMessage;

public class ImageMessage extends BaseMessage {
  
    private String picUrl;  
  
    public String getPicUrl() {  
        return picUrl;  
    }  
  
    public void setPicUrl(String picUrl) {  
        this.picUrl = picUrl;  
    } 
}
      