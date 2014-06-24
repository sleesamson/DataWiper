<%@page import="org.codehaus.jackson.map.ObjectMapper"%>
<%@page import="java.net.*"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Instructions</title>
    <!-- Bootstrap core CSS -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="bootstrap/css/dashboard.css" rel="stylesheet">
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
<div class="container theme-showcase" role="main">
<% String os = request.getParameter("os"); %>

<div class="page-header">
	<h4>Instructions for clearing your  <%= os %> Device</h4>
</div>

<%
String url = String.format("http://datawiper.herokuapp.com/rest/OS/%s/instructions", os);
ObjectMapper mapper = new ObjectMapper();
ArrayList<String> instructions = mapper.readValue(new URL(url), ArrayList.class); 

%>
<div class="jumbotron">
<ol>
<% for (String step : instructions){ %> 
	<li><%= step %></li>
	<%} %>

</ol>
</div>
</div>
</body>


</html>