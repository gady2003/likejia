package com.wei42.weixin.domain.message.response;


import com.wei42.weixin.domain.message.BaseMessage;

public class ResponseTextMessage extends BaseMessage {

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
