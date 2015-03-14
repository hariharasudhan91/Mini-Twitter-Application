/**

 * 
 */
package com.sudhan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sudhan.beans.Tweets;

/**
 * @author Hari
 *
 */
public class TweetsDao {

	
    public List<Tweets> getTweets(String apikey, String search) throws ClassNotFoundException, SQLException{

    	Connection con = GetConnection.getConnection();
    	
    	String sql = "select t.* from Tweets t inner join TwitterUser u on t.twitter_id = u.twitter_id where u.apikey = ? ";
    	sql += " union all select * from Tweets t where t.twitter_id in (select f.following_id from Followers f where f.follower_id = (select twitter_id from TwitterUser where apikey=?))";
    	
    	if( search != null ){
    		sql  = "select T.* from ( "+sql+" ) T where T.tweet like '%"+search+"%'";
    		
    		System.out.println(sql);
    	}
    	
    	PreparedStatement ps = con.prepareStatement(sql);
    	
    	ps.setString(1, apikey);
    	ps.setString(2, apikey);
    	
    	ResultSet rs = ps.executeQuery();
    	
    	List<Tweets> tweetList = new ArrayList<Tweets>();
    	
    	Tweets t;
    	
    	while(rs.next()){
    		
    		t = new Tweets();
    		t.setTweet(rs.getString("tweet"));
    		t.setId(rs.getString("twitter_id"));
    		t.setDate(rs.getDate("tweet_date"));
    		
    		tweetList.add(t);
    	}
    	
    	System.out.println("working");
    	
    	con.close();
    	return tweetList;
    }
}
