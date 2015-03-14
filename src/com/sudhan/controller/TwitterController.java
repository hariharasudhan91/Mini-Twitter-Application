package com.sudhan.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.sudhan.beans.ConnectionsBean;
import com.sudhan.beans.Tweets;
import com.sudhan.dao.TweetsDao;
import com.sudhan.dao.UserDao;



@Controller
public class TwitterController {
	
	
	@Autowired
	TweetsDao tweetsDao;
	@Autowired
	UserDao userDao;
	
    @ResponseBody 
	@RequestMapping(value = "/tweets", method = RequestMethod.GET)
	public String getTweets(
	@RequestParam(value = "apikey", required=false) String apikey, 
	@RequestParam(value = "search", required=false) String search) throws ClassNotFoundException, SQLException{
    	
    	List<Tweets> returnList = null;
    	String error;
    	if(apikey == null){
    		return "You must provide apikey"; 
    	}
    	else{
    		  String check=userDao.autheticate(apikey);
    		  if (check != null && !check.isEmpty())
    		  {
           try {
				returnList = tweetsDao.getTweets(apikey, search);
			} catch (Exception e) {
				e.printStackTrace();
			} 
    	}
    	}
    	Gson gson = new Gson();
    	if (returnList == null)
    		
    	{
    		error="Not Authentic Api key";
    		String s=gson.toJson(error);
    	}
    	String s = gson.toJson(returnList);
    	
		return gson.toJson(returnList);
	}
    
    
    @ResponseBody 
	@RequestMapping(value = "/connections", method = RequestMethod.GET)
	public String getConnections(	
			@RequestParam(value = "apikey", required=false) String apikey
			) throws ClassNotFoundException, SQLException{
    	
    	ConnectionsBean returnList = null;
    	String error;
    	
    	if(apikey == null){
    		return "You must provide apikey"; 
    	}
    	else{
    		  String check=userDao.autheticate(apikey);
    		  if (check != null && !check.isEmpty())
    		  {
           try {
				returnList = userDao.getConnections(apikey);
			} catch (Exception e) {
				e.printStackTrace();
			} 
    	}
    	}
    	Gson gson = new Gson();
    	if (returnList == null)
    		
    	{
    		error="Not Authentic Api key";
    		return gson.toJson(error);
    	}
    	
		return gson.toJson(returnList);
	}
    
    @ResponseBody 
	@RequestMapping(value = "/follow", method = RequestMethod.GET)
    public String follow(
    		@RequestParam(value = "apikey", required=false) String apikey, 
    		@RequestParam(value = "id", required=false) String id){
    	   
    	   String status="Failure";
           Gson gson = new Gson();
    	    	if(apikey == null ||id==null){
    	    		return "You must provide apikey and the id to proceed"; 
    	    	}
    	    	else{
    	    		String check = null;
					try {
						check = userDao.autheticate(apikey);
					} catch (ClassNotFoundException e1) {
						
						e1.printStackTrace();
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
					 if (check != null && !check.isEmpty())
    	      		  {
    					try {
							status = userDao.follow(apikey,id);
						} catch (ClassNotFoundException e) {
							
							e.printStackTrace();
						} catch (SQLException e) {
							
							e.printStackTrace();
						}
    					}
    	      		  }
    	    	String s = gson.toJson(status);
    	    	return gson.toJson(status);
    	    	
    		} 
    
    
    @ResponseBody 
    		@RequestMapping(value = "/unfollow", method = RequestMethod.GET)
    	    public String unFollow(
    	    		@RequestParam(value = "apikey", required=false) String apikey, 
    	    		@RequestParam(value = "id", required=false) String id){
    	
  	   String status="Success";
         Gson gson = new Gson();
  	    	if(apikey == null||id==null){
  	    		return "You must provide apikey and the id to proceed"; 
  	    	}
  	    	else{
  	    		String check = null;
					try {
						check = userDao.autheticate(apikey);
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
					 if (check != null && !check.isEmpty())
  	      		  {
  					try {
							status = userDao.unfollow(apikey,id);
						} catch (ClassNotFoundException e) {
							
							e.printStackTrace();
						} catch (SQLException e) {
							
							e.printStackTrace();
						}
  					}
  	      		
  	    	}
  	    	return gson.toJson(status);
	
}
}
