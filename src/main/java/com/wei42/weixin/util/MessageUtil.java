package com.wei42.weixin.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.wei42.weixin.domain.Article;
import com.wei42.weixin.domain.message.BaseMessage;
import com.wei42.weixin.domain.message.response.ResponseNewsMessage;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.ServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageUtil {

	  private static XStream xstream = new XStream(new XppDriver() {  
	        public HierarchicalStreamWriter createWriter(Writer out) {  
	            return new PrettyPrintWriter(out) {  
	                // 对所有xml节点的转换都增加CDATA标记  
	                boolean cdata = true;  
	                protected void writeText(QuickWriter writer, String text) {  
	                    if (cdata) {  
	                        writer.write("<![CDATA[");  
	                        writer.write(text);  
	                        writer.write("]]>");  
	                    } else {  
	                        writer.write(text);  
	                    }  
	                }  
	            };  
	        }  
	    });  
	public static Map<String, String> parseXml(ServletRequest request){
		
		 // 将解析结果存储在HashMap中  
		Map<String, String> map;
		try {
			map = new HashMap<String, String>();  
  
			// 从request中取得输入流  
			InputStream inputStream = request.getInputStream();  
			// 读取输入流  
			SAXReader reader = new SAXReader();  
			Document document = reader.read(inputStream);  
			// 得到xml根元素  
			Element root = document.getRootElement();  
			// 得到根元素的所有子节点  
			  
			@SuppressWarnings("unchecked")  
			List<Element> elementList = root.elements();  
  
			// 遍历所有子节点  
			for (Element e : elementList)  
			    map.put(e.getName(), e.getText());  
  
			// 释放资源  
			inputStream.close();  
			inputStream = null;
			return map;  
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}  
		return null;
        
	}
	
	public static String textMessage2Xml(BaseMessage message){
		  xstream.alias("xml", message.getClass());  
	        return xstream.toXML(message);  
		
	}
	
	public static String newsMessage2Xml(ResponseNewsMessage message){
		  xstream.alias("xml", message.getClass());  
		  xstream.alias("item",Article.class);
	        return xstream.toXML(message);  
		
	}
}
