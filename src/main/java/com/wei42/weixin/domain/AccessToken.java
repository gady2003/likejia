package com.wei42.weixin.domain;
public class AccessToken {
	
	/**
	 *  获取到的凭证
	 */
	private String token;
	
	/**
	 *  凭证有效时间，单位：秒
	 */
	private int expiresIn;
	
	private long createTime;
	
	public AccessToken(){
		createTime = System.currentTimeMillis();
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}

	public AccessToken(String token, int expiresIn) {
		super();
		this.token = token;
		this.expiresIn = expiresIn;
	}
	
    public boolean isExpires(){
    	return System.currentTimeMillis() -createTime > expiresIn*1000;
    }

	
}