/**
 * 
 */
package com.georges.social.qq.connect;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UserProfileBuilder;

import com.georges.social.qq.api.QQ;
import com.georges.social.qq.api.QQProfile;

/**
 * @author alexzuo
 * 
 */
public class QQAdapter implements ApiAdapter<QQ> {

	public UserProfile fetchUserProfile(QQ qq) {
		QQProfile profile = qq.userOperations().getUserProfile();
		//userName will become the local user Id for Grape. To make it easy to read
		//and make it unique, we use the unique OpenId of QQ account with a prefix
		return new UserProfileBuilder().setName(profile.getNickName())
				.setUsername("QQ_"+profile.getOpenId()).build();
	}

	public void setConnectionValues(QQ qq, ConnectionValues values) {
		QQProfile profile = qq.userOperations().getUserProfile();
		values.setProviderUserId(profile.getOpenId());
		values.setDisplayName(profile.getNickName());
		values.setImageUrl(profile.getFigureUrl());
		values.setProfileUrl(null);
	}

	public boolean test(QQ qq) {
		try {
			qq.userOperations().getUserProfile();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void updateStatus(QQ qq, String message) {
		//Nothing to do
	}

}
