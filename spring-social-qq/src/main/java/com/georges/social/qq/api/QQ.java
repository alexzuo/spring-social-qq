/**
 * 
 */
package com.georges.social.qq.api;

import org.springframework.social.ApiBinding;

import com.georges.social.qq.api.UserOperations;

/**
 * @author alexzuo
 *
 */
public interface QQ extends ApiBinding {

	public abstract UserOperations userOperations();
	
}
