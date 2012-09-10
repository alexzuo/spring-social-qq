/**
 * 
 */
package com.georges.social.qq.api.impl;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.CharSet;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;

import com.georges.social.qq.api.QQ;
import com.georges.social.qq.api.UserOperations;
import com.georges.social.qq.api.impl.json.QQModule;

public class QQTemplate extends AbstractOAuth2ApiBinding implements QQ {
	
	private static final String openIdUrl="https://graph.qq.com/oauth2.0/me";
	
	private UserOperations userOperations;
	private ObjectMapper objectMapper;

	@SuppressWarnings("unused")
	public QQTemplate(String appId, String accessToken) {
		super(accessToken);
				
		String callback=getRestTemplate().getForObject(openIdUrl+"?access_token="+ accessToken, String.class);
		String startKey="\"openid\":\"";
		String endKey="\"";
		
		int start=callback.indexOf(startKey);
		int end=callback.indexOf(endKey, start+startKey.length());
		
		String openId=callback.substring(start+startKey.length(), end);
			
		initialize(appId, openId,accessToken );
	}

	public QQTemplate() {
		// TODO Auto-generated constructor stub
	}

	private void initialize(String appId, String openId,  String token) {
		userOperations = new UserTemplate(this, getRestTemplate(),
				isAuthorized(), appId, openId, token);
	}

	/**
	 * @return the userOperations
	 */

	public UserOperations userOperations() {
		return userOperations;
	}

	@Override
	protected MappingJacksonHttpMessageConverter getJsonMessageConverter() {
		MappingJacksonHttpMessageConverter converter = super
				.getJsonMessageConverter();
		List<MediaType> list = new ArrayList<MediaType>();
		list.addAll(converter.getSupportedMediaTypes());
		list.add(MediaType.TEXT_PLAIN);
		list.add(MediaType.APPLICATION_JSON);
		list.add(MediaType.TEXT_HTML);
		list.add(new MediaType("text","x-javascript",Charset.forName("UTF-8")));
		converter.setSupportedMediaTypes(list);

		objectMapper = new ObjectMapper();
		objectMapper.registerModule(new QQModule());
		converter.setObjectMapper(objectMapper);
		return converter;
	}

}
