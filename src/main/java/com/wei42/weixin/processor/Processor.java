package com.wei42.weixin.processor;

import java.util.Map;

public interface Processor {
	
	public String processTextMessage(Map<String, String> map) ;
	
	public String processSubscribeMessage(Map<String, String> map) ;

    public String processUnsubscribeMessage(Map<String, String> map) ;
	
	public String processClickMessage(Map<String, String> map) ;

}
