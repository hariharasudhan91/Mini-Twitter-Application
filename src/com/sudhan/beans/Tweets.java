/**
 * 
 */
package com.sudhan.beans;
import java.util.Date;

/**
 * @author Hari
 *
 */
public class Tweets {
	String tweet;
	String username;
	String id;
	Date date = new Date(new Date().getTime());
	
	public String getTweet() {
		return tweet;
	}
	public void setTweet(String tweet) {
		this.tweet = tweet;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	

}
