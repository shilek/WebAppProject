<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style>
ul {
  list-style-type: none;
  margin: 0;
  padding: 0;
  overflow: hidden;
  background-color: #3591e7;
}

li {
  float: left;
}

li a {
  display: block;
  color: black;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
}

/* Change the link color to #111 (black) on hover */
li a:hover {
  background-color: #1170c7;
}

.active {
  background-color: #c6ddf2;
}

#item{
padding: 20px;
}

.footer {
position: fixed;
bottom: 0;
}
</style>
<title>Home</title>
</head>
<body>
<div>
	<div align="center" id="logo" style="height:100px">LOGO</div>
</div>
<ul>
	<li><a href="index">Home</a></li>
	<li><a href="products">Products</a></li>
	<li><a href="contact">Contact</a></li>
	<li style="float:right"><a href="account">Account</a></li>
	<li style="float:right"><a href="cart">Cart: </a></li>
</ul>
<c:if test = "${loggedUser == NULL}">  
<table>
<tr><td>
  <form action="<%= request.getContextPath() %>/account?action=buy" method="post">
   <table>
   <tr><td>Login to save an order on account</td></tr>
    <tr>
     <td>E-mail: </td>
     <td><input type="text" name="email" /></td>
    </tr>
    <tr>
     <td>Password: </td>
     <td><input type="password" name="password" /></td>
    </tr>
   </table>
   <input type="submit" value="Submit" />
  </form>
  </td>
  <td>
  <table>
  <tr><td>Continue without login</td></tr>
  <tr><td><input type="button" onClick="javascript:window.location='buy?action=noAccount';" value="Continue"/></td></tr>
  </table>
  </td>
  </tr>
  </table>
  </c:if>
  <c:if test = "${loggedUser != NULL}">  
   <table>
   <tr><td>Do you want to save this order to your account?</td></tr>
  <tr><td><input type="button" onClick="javascript:window.location='account?action=buy';" value="Yes"/></td><td><input type="button" onClick="javascript:window.location='buy?action=noAccount';" value="No"/></td></tr>
  </table>
  </c:if>
</body>
</html>
