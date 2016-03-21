package com.wei42.weixin.domain.message.request;


import com.wei42.weixin.domain.message.BaseMessage;

public class TextMessage extends BaseMessage {
    /** 
     * 回复的消息内容 
     */  
    private String Content;  
  
    public String getContent() {  
        return Content;  
    }  
  
    public void setContent(String content) {  
        Content = content;  
    }  
}  