/**
 * 
 */
package com.sudhan.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.sudhan.beans.ConnectionsBean;
import com.sudhan.beans.TwitterUserBean;

/**
 * @author Hari
 *
 */
public class UserDao {
	
	public String autheticate(String apikey) throws ClassNotFoundException, SQLException
	{    
		
	   String id = null;
	   Statement stmt=null;
	   Connection con = GetConnection.getConnection();
		 String sql = "SELECT  twitter_id FROM TwitterUser where apikey='"+apikey+"'";
		 stmt = con.createStatement();
	     ResultSet rs = stmt.executeQuery(sql);
	     
	     while(rs.next()){
	        id= rs.getString("twitter_id");
	      }
	     con.close();
	      return id;
	}
	
	
	
	public ConnectionsBean getConnections(String apikey) throws ClassNotFoundException, SQLException{

    	Connection con = GetConnection.getConnection();
    	
    	ArrayList<TwitterUserBean> followers=new ArrayList<TwitterUserBean>();
    	ArrayList<TwitterUserBean> following=new ArrayList<TwitterUserBean>();

    	String sql = "select following_id from Followers where follower_id=?";
    	
    	PreparedStatement ps = con.prepareStatement(sql);
    	String id= autheticate(apikey);
    	ps.setString(1, id);
    	
    	ResultSet rs = ps.executeQuery();
    	
    	ConnectionsBean connection=new ConnectionsBean();
    	
    	TwitterUserBean t;
    	
    	while(rs.next()){
    		
    		t = new TwitterUserBean();
    		t.setUsername(rs.getString("following_id"));
    		
    		
    		following.add(t);
    	}
    	connection.setFollowing(following);
          sql = "select follower_id from Followers where following_id=?";
    	
    	ps = con.prepareStatement(sql);
    	id= autheticate(apikey);
    	ps.setString(1, id);
    	
    	rs = ps.executeQuery();
    	
    	while(rs.next()){
    		
    		t = new TwitterUserBean();
    		t.setUsername(rs.getString("follower_id"));
    		followers.add(t);
    	}
    	
    	connection.setFollowers(followers);
    	
    	con.close();
    	return connection;
    }
	
	@Autowired
	TweetsDao tweetsDao;
	
	public String follow (String apikey, String twitterid) throws ClassNotFoundException, SQLException {
    
	String message="User Followed";
	String sql;
	Connection con = GetConnection.getConnection();
	sql = "select following_id from Followers where follower_id=? and following_id=?";
	PreparedStatement ps = con.prepareStatement(sql);
	String id = autheticate(apikey);
	ps.setString(1, id);
	ps.setString(2,twitterid);
	ResultSet rs = ps.executeQuery();
	
	if(id==null)
	{
		message="User does not exist";
	}
	while(rs.next()){
	  message="Already following User"+twitterid;
	}
	if(message!="Already following this user")
	{
	String insertTableSQL = "INSERT INTO Followers"
			+ "(follower_id,following_id) VALUES"
			+ "(?,?)";
	
	
	PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
	preparedStatement.setString(1, id);
	preparedStatement.setString(2,twitterid);
	
	int flag=preparedStatement.executeUpdate();
	}
	
	con.close();
	return message;
	
}	
	public String unfollow (String apikey, String twitterid) throws ClassNotFoundException, SQLException {

		Connection con = GetConnection.getConnection();
		String sql;
		String message="User Un-followed";
		sql = "select follower_id from Followers where following_id=? and follower_id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		String id = autheticate(apikey);
		ps.setString(1, twitterid);
		ps.setString(2,id);
		ResultSet rs = ps.executeQuery();
		boolean userExists = false;
		while(rs.next()){
			userExists = true;
		}
		
		if(!userExists){
			message = "You are not following "+twitterid;
			con.close();
			return message;
		}
		
		String insertTableSQL = "DELETE from Followers where follower_id = ? and following_id = ? LIMIT 1";
		PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
		if(id==null)
		{
			message="User does not exist";
		}
		preparedStatement.setString(1,id);
		preparedStatement.setString(2,twitterid);
		preparedStatement.executeUpdate();
		
		
		con.close();
		return message;
		
	}
	
}

