package com.wei42.weixin.handler;



import com.wei42.weixin.domain.Article;
import com.wei42.weixin.domain.message.response.ResponseNewsMessage;
import com.wei42.weixin.domain.message.response.ResponseTextMessage;
import com.wei42.weixin.processor.Processor;
import com.wei42.weixin.processor.impl.LikejiaProcessor;
import com.wei42.weixin.util.MessageUtil;
import com.wei42.weixin.util.WeixinConstants;

import javax.servlet.ServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MessageRequestHandler {
	
	Processor processor = new LikejiaProcessor();
	
	public String handle(ServletRequest request) {
		
		 Map<String, String> map = MessageUtil.parseXml(request);
		 if(map != null){
			
			 String msgType = map.get("MsgType");
			
			 if(msgType.equals("text")){
				return processor.processTextMessage(map);
			 }else if(WeixinConstants.MESSAGE_TYPE_EVENT.equals(msgType)){
				 String eventType = map.get("Event");
				 if(WeixinConstants.EVENT_TYPE_SUBSCRIBE.equals(eventType)){
					 return processor.processSubscribeMessage(map);
				 }else if(WeixinConstants.EVENT_TYPE_UNSUBSCRIBE.equals(eventType)){
					 
//					 String fromUserName = map.get("FromUserName");
//					 System.err.println(" User :"+ fromUserName +" 退出服务号");
                     return  processor.processUnsubscribeMessage(map);
				 }else if(WeixinConstants.EVENT_TYPE_CLICK.equals(eventType)){
					 return processor.processClickMessage(map);
				 }
						 
			 }else{
				 System.out.println("no handler for msgtype:"+ msgType);
			 }
		 }else{
			 System.out.println("ERROR : request map is null");
		 }
		 
		return null;
	}

	private String processTextMessage(Map<String, String> map) {
		 String fromUserName = map.get("FromUserName");
		 String toUserName = map.get("ToUserName");
		String content = map.get("Content");
		if(content.equals("1")){
			 ResponseTextMessage textmessage = new ResponseTextMessage();
			 textmessage.setFromUserName(toUserName);
			 textmessage.setToUserName(fromUserName);
			 textmessage.setCreateTime(System.currentTimeMillis());
			 textmessage.setMsgType("text");
			 textmessage.setContent("欢迎您订阅公众号HelloMachine!");
			 return MessageUtil.textMessage2Xml(textmessage);
		}else if(content.equals("2")){
			ResponseNewsMessage newsMsg = new ResponseNewsMessage();
			newsMsg.setFromUserName(toUserName);
			newsMsg.setToUserName(fromUserName);
			newsMsg.setCreateTime(System.currentTimeMillis());
			newsMsg.setMsgType(WeixinConstants.MESSAGE_TYPE_NEWS);
			Article article = new Article();
			article.setTitle("这是张美女图片");
			article.setDescription("这是美女图片的描述");
			article.setPicUrl("http://www.hellomachine.cn/image/meinv1.jpg");
			article.setUrl("http://www.hellomachine.cn");
			List<Article> articles = new ArrayList<Article>();
			articles.add(article);
			newsMsg.setArticleCount(articles.size());
			newsMsg.setArticles(articles);
			return MessageUtil.newsMessage2Xml(newsMsg);
		}else if(content.equals("3")){
			ResponseNewsMessage newsMsg = new ResponseNewsMessage();
			newsMsg.setFromUserName(toUserName);
			newsMsg.setToUserName(fromUserName);
			newsMsg.setCreateTime(System.currentTimeMillis());
			newsMsg.setMsgType(WeixinConstants.MESSAGE_TYPE_NEWS);
			List<Article> articles = new ArrayList<Article>();
			
			Article article = new Article();
			article.setTitle("这是第二张美女图片");
			article.setDescription("这是美女图片的描述");
			article.setPicUrl("http://www.hellomachine.cn/image/meinv2.jpg");
			article.setUrl("http://www.hellomachine.cn");
			articles.add(article);
			
			article = new Article();
			article.setTitle("这是第三张美女图片");
			article.setDescription("这是美女图片的描述");
			article.setPicUrl("http://www.hellomachine.cn/image/meinv3.jpg");
			article.setUrl("http://www.hellomachine.cn");
			articles.add(article);
			
			newsMsg.setArticleCount(articles.size());
			newsMsg.setArticles(articles);
			return MessageUtil.newsMessage2Xml(newsMsg);
		}
		return null;
	}

}
