package com.georges.social.qq.api.impl;

import java.lang.reflect.Field;

import org.codehaus.jackson.JsonNode;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.client.RestTemplate;

import com.georges.social.qq.api.QQ;
import com.georges.social.qq.api.QQProfile;
import com.georges.social.qq.api.UserOperations;
/**
 * @author alexzuo
 * 
 */
public class UserTemplate extends AbstractQQOperations implements
		UserOperations {
	
	private String appId;
	private String openId;
	private String accessToken;
	
	public UserTemplate(QQ api, RestTemplate restTemplate,
			boolean isAuthorized,
			String appId,
			String openId,
			String accessToken) {
		super(api, restTemplate, isAuthorized);
		
		this.appId=appId;
		this.openId = openId;
		this.accessToken=accessToken;
	}

	@SuppressWarnings("unused")
	public QQProfile getUserProfile() {
		requireAuthorization();
		/*		
		https://graph.qq.com/user/get_user_info?access_token=YOUR_ACCESS_TOKEN&oauth_consumer_key=YOUR_APP_ID&openid=YOUR_OPENID
		*/			
	//	https://graph.qq.com/oauth2.0/me?access_token=YOUR_ACCESS_TOKEN
		
//		
//		JsonNode json = this.restTemplate.getForObject(
//				buildUrl("account/get_uid.json"), JsonNode.class);
//		return this.restTemplate.getForObject(
//				buildUrl("users/show.json", "uid", json.get("uid").asText()),
//				WeiboProfile.class);
		
		MultiValueMap<String, String> params=new LinkedMultiValueMap<String, String>();
		params.add("openid", openId);
		params.add("access_token", accessToken);
		params.add("oauth_consumer_key", appId);
		
		QQProfile qqProfile = this.restTemplate.getForObject(
				buildUrl("user/get_user_info", params), QQProfile.class);
		
		//save the ProviderUserId
		Field field = ReflectionUtils.findField(QQProfile.class, "openId", String.class);
		ReflectionUtils.makeAccessible(field);
		ReflectionUtils.setField(field, qqProfile, openId);
		
		return qqProfile;
	}

}
