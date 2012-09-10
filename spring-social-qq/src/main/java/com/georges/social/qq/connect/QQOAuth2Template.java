/**
 * 
 */
package com.georges.social.qq.connect;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.social.support.ClientHttpRequestFactorySelector;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.client.RestTemplate;

/**
 * @author alexzuo
 *
 */
public class QQOAuth2Template extends OAuth2Template {
	
	
	
	public QQOAuth2Template(String clientId, String clientSecret,
			String authorizeUrl, String accessTokenUrl) {
		super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
	}

	/* (non-Javadoc)
	 * @see org.springframework.social.oauth2.OAuth2Template#createRestTemplate()
	 */
	@Override
	protected RestTemplate createRestTemplate() {
		RestTemplate restTemplate = new RestTemplate(ClientHttpRequestFactorySelector.getRequestFactory());
		List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>(2);
		
		FormHttpMessageConverter formConverter=new FormHttpMessageConverter();
		
		List<MediaType> list1 = new ArrayList<MediaType>();
		list1.addAll(formConverter.getSupportedMediaTypes());
		list1.add(MediaType.TEXT_HTML);
		formConverter.setSupportedMediaTypes(list1);
		
		converters.add(formConverter);
		
		MappingJacksonHttpMessageConverter jacksonConverter = new MappingJacksonHttpMessageConverter();
		
		List<MediaType> list2 = new ArrayList<MediaType>();
		list2.addAll(jacksonConverter.getSupportedMediaTypes());
		list2.add(MediaType.TEXT_PLAIN);
		jacksonConverter.setSupportedMediaTypes(list2);
		
		converters.add(jacksonConverter);
		
		restTemplate.setMessageConverters(converters);
		return restTemplate;
	}
	

	@SuppressWarnings("unchecked")
	@Override
	protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
	
		MultiValueMap<String, Object> result =getRestTemplate().postForObject(accessTokenUrl, parameters, MultiValueMap.class);
	
		String accessToken=(String)result.getFirst("access_token");
		String scope=(String) result.getFirst("scope");
		String refreshToken=(String) result.getFirst("refresh_token");
		Integer expiresIn=Integer.parseInt((String) result.getFirst("expires_in"));

		AccessGrant ag = new AccessGrant(accessToken, scope, refreshToken, expiresIn);
		//A bug in AccessGrant which cause Integer overflow, fix it here
		Field field = ReflectionUtils.findField(AccessGrant.class, "expireTime", Long.class);
		ReflectionUtils.makeAccessible(field);
		ReflectionUtils.setField(field, ag, System.currentTimeMillis()+((long)expiresIn )* 1000);

		return ag;
	}

}
