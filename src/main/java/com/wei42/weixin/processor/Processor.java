package com.wei42.weixin.processor;

import java.util.Map;

public interface Processor {
	
	public String processTextMessage(Map<String, String> map) ;
	
	public String processSubscribeMessage(Map<String, String> map) ;

    public void processUnsubscribeMessage(Map<String, String> map) ;
	
	public String processClickMessage(Map<String, String> map) ;

    public void processLocationMessage(Map<String, String> map);

}
