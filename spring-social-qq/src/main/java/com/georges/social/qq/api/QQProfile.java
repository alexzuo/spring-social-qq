
package com.georges.social.qq.api;
import java.io.Serializable;

import org.springframework.social.connect.UserProfile;

public class QQProfile  implements Serializable {

	private static final long serialVersionUID = 8544985307803572909L;
	
	String openId; 
	String nickName; 
	String figureUrl; 
	String gender; 
	int vip; 
	int level;
	
	public QQProfile(String nickname, String figureUrl, String gender, int vip, int level) {

		this.nickName=nickname;
		this.figureUrl=figureUrl;
		this.gender=gender;
		this.vip=vip;
		this.level=level;
	}

	public String getNickName() {
		return nickName;
	}

	public String getFigureUrl() {
		return figureUrl;
	}

	public String getGender() {
		return gender;
	}

	public int getVip() {
		return vip;
	}

	public int getLevel() {
		return level;
	}
	public String getOpenId()
	{
		return this.openId;
	}
}
