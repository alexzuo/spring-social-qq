package com.georges.social.qq.connect;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

import com.georges.social.qq.api.QQ;
import com.georges.social.qq.api.impl.QQTemplate;

public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

	private String clientId;
	
	public QQServiceProvider(String clientId, String clientSecret) {
		super(new QQOAuth2Template(clientId, clientSecret,
				"https://graph.qq.com/oauth2.0/authorize",
				"https://graph.qq.com/oauth2.0/token"));
		this.clientId=clientId;
	}

	@Override
	public QQ getApi(String accessToken) {

		return new QQTemplate(clientId, accessToken);
	}

}
