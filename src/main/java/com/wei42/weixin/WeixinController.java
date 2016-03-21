package com.wei42.weixin;

import com.wei42.weixin.handler.MessageRequestHandler;
import com.wei42.weixin.security.SignUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("/weixin.html")
public class WeixinController {
   
	private MessageRequestHandler requestProcessor = new MessageRequestHandler();
	
	@RequestMapping(method = RequestMethod.GET)
	public void weixinSign(ServletRequest request, ServletResponse response){
		 // 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。  
        String signature = request.getParameter("signature");  
        // 时间戳  
        String timestamp = request.getParameter("timestamp");  
        // 随机数  
        String nonce = request.getParameter("nonce");  
        // 随机字符串  
        String echostr = request.getParameter("echostr");  
       
        PrintWriter out = null;  
        try {  
            out = response.getWriter();  
            if(echostr == null){
            	out.println("no param");
            	return ;
            }
            // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，否则接入失败  
            if (SignUtil.checkSignature(signature, timestamp, nonce)) {
                out.print(echostr);  
            }else{
            	out.print(echostr); 
            }
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            out.close();  
            out = null;  
        } 
	}
	@RequestMapping(method= RequestMethod.POST)
	public void handle(ServletRequest request, ServletResponse response){
		
        response.setCharacterEncoding("UTF-8"); 
        String res = requestProcessor.handle(request);
        if(res == null){
        	return ;
        }
		 PrintWriter out = null; 
		 try {
			out = response.getWriter();
			out.write(res);
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			  out.close();  
	          out = null;  
		}
		
	}
	
	
}
