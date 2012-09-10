/**
 * 
 */
package com.georges.social.qq.api.impl;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import com.georges.social.qq.api.QQProfile;

/**
 * @author alexzuo
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4871054765734757519L;
	
	private final List<QQProfile> users;
	private final long nextCursor;
	private final long previousCursor;
	private final long totalNumber;
	/**
	 * @param users
	 * @param nextCursor
	 * @param previousCursor
	 * @param totalNumber
	 */
	@JsonCreator
	public UserList(
			@JsonProperty("users") List<QQProfile> users,
			@JsonProperty("next_cursor") long nextCursor,
			@JsonProperty("previous_cursor") long previousCursor,
			@JsonProperty("total_number") long totalNumber) {
		this.users = users;
		this.nextCursor = nextCursor;
		this.previousCursor = previousCursor;
		this.totalNumber = totalNumber;
	}
	/**
	 * @return the users
	 */
	public List<QQProfile> getUsers() {
		return users;
	}
	/**
	 * @return the nextCursor
	 */
	public long getNextCursor() {
		return nextCursor;
	}
	/**
	 * @return the previousCursor
	 */
	public long getPreviousCursor() {
		return previousCursor;
	}
	/**
	 * @return the totalNumber
	 */
	public long getTotalNumber() {
		return totalNumber;
	}

}
