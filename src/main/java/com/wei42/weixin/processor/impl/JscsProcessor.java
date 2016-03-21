package com.wei42.weixin.processor.impl;



import com.wei42.weixin.domain.Article;
import com.wei42.weixin.domain.message.response.ResponseNewsMessage;
import com.wei42.weixin.domain.message.response.ResponseTextMessage;
import com.wei42.weixin.processor.Processor;
import com.wei42.weixin.util.MessageUtil;
import com.wei42.weixin.util.WeixinConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JscsProcessor implements Processor {

	@Override
	public String processTextMessage(Map<String, String> map) {

		String fromUserName = map.get("FromUserName");
		String toUserName = map.get("ToUserName");
		
		ResponseTextMessage textmessage = new ResponseTextMessage();
		 textmessage.setFromUserName(toUserName);
		 textmessage.setToUserName(fromUserName);
		 textmessage.setCreateTime(System.currentTimeMillis());
		 textmessage.setMsgType("text");
		 textmessage.setContent("欢迎进入金属超市,点击下面按钮进入.");
		 return MessageUtil.textMessage2Xml(textmessage);
	
	}

	@Override
	public String processSubscribeMessage(Map<String, String> map) {
		 String fromUserName = map.get("FromUserName");
		 String toUserName = map.get("ToUserName");
		 ResponseTextMessage textmessage = new ResponseTextMessage();
		 textmessage.setFromUserName(toUserName);
		 textmessage.setToUserName(fromUserName);
		 textmessage.setCreateTime(System.currentTimeMillis());
		 textmessage.setMsgType("text");
		 textmessage.setContent("欢迎您订阅金属超市公众号");
		 return MessageUtil.textMessage2Xml(textmessage);
	}

    @Override
    public void processUnsubscribeMessage(Map<String, String> map) {
    }

    @Override
	public String processClickMessage(Map<String, String> map) {
		String fromUserName = map.get("FromUserName");
		String toUserName = map.get("ToUserName");
		
		ResponseNewsMessage newsMsg = new ResponseNewsMessage();
		newsMsg.setFromUserName(toUserName);
		newsMsg.setToUserName(fromUserName);
		newsMsg.setCreateTime(System.currentTimeMillis());
		newsMsg.setMsgType(WeixinConstants.MESSAGE_TYPE_NEWS);
		List<Article> articles = new ArrayList<Article>();
		String eventkey = map.get("EventKey");
		 //行情
		 if("hq".equals(eventkey)){
			 Article article = new Article();
				article.setTitle("因精炼铜价格上涨,中国废铜进口随之增加");
				article.setDescription("因精炼铜价格上涨,中国废铜进口随之增加");
				article.setPicUrl("http://www.hellomachine.cn/image/meinv1.jpg");
				article.setUrl("http://www.jscs168.com/NewsArticleList.aspx?ArticleID=26");
				articles.add(article);
				
				article = new Article();
				article.setTitle("俄罗斯今年4月大规模增加黄金储备量");
				article.setPicUrl("http://www.hellomachine.cn/image/jiage.jpg");
				article.setUrl("http://www.jscs168.com/NewsArticleList.aspx?ArticleID=27");
				articles.add(article);
				
				article = new Article();
				article.setTitle("2014年铜均价料为每吨6,450美元");
				article.setPicUrl("http://www.hellomachine.cn/image/zhuce.jpg");
				article.setUrl("http://www.jscs168.com/NewsArticleList.aspx?ArticleID=28");
				articles.add(article);
				
				newsMsg.setArticleCount(articles.size());
				newsMsg.setArticles(articles);
		 }
		
		
		
		return MessageUtil.newsMessage2Xml(newsMsg);
	}

    @Override
    public void processLocationMessage(Map<String, String> map) {

    }
}
