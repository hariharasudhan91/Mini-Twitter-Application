/**
 * 
 */
package com.sudhan.beans;

import java.util.ArrayList;

/**
 * @author Hari
 *
 */
public class ConnectionsBean {
	ArrayList<TwitterUserBean> Followers;
	ArrayList<TwitterUserBean> Following;
	public ArrayList<TwitterUserBean> getFollowers() {
		return Followers;
	}
	public void setFollowers(ArrayList<TwitterUserBean> followers) {
		Followers = followers;
	}
	public ArrayList<TwitterUserBean> getFollowing() {
		return Following;
	}
	public void setFollowing(ArrayList<TwitterUserBean> following) {
		Following = following;
	}
	

}
