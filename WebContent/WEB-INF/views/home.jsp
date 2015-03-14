<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Twitter API Home</title>
</head>
<body>
<h1>
	Welcome to Twitter API 
</h1>

<P>  
To read the tweets for a given user: 
<br><strong> 
52.0.149.253:8080/TwitterApiServices/tweets
</strong>
<br>     
(eg:http://52.0.149.253:8080/TwitterApiServices/tweets?apikey=zb476eks4a3bbd9e&search=dex)
</P>
<hr>
 
<P> 
To get the list of people a user is following as well as the followers of the user:
<br><strong>  
52.0.149.253:8080/TwitterApiServices/connections
</strong>
<br> 
(eg:http://52.0.149.253:8080/TwitterApiServices/connections?apikey=zb476eks4a3bbd9e) 
</P>
<hr>
<P>
To start following a user
<br><strong>  
52.0.149.253:8080/TwitterApiServices/follow
</strong>
<br> 
(eg:http://52.0.149.253:8080/TwitterApiServices/follow?apikey=zb476eks4a3bbd9e&id=hari2sudhan) 
</P>
<hr>
<P> 
To start following a user
<br><strong>  
52.0.149.253:8080/TwitterApiServices/unfollow
</strong>
<br> 
(eg:http://52.0.149.253:8080/TwitterApiServices/unfollow?apikey=zb476eks4a3bbd9e&id=sidboy)   
</P>
<hr>


</body>
</html>
