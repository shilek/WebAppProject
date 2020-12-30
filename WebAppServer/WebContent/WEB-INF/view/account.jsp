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
<h1>Login</h1>
  <form action="<%= request.getContextPath() %>/account" method="post">
   <table>
    <tr>
     <td>E-mail: </td>
     <td><input type="text" name="email" /></td>
    </tr>
    <tr>
     <td>Password: </td>
     <td><input type="password" name="password" /></td>
    </tr>
   </table>
   <input type="submit" value="Submit" /> <input type="button" onClick="javascript:window.location='register';" value="Register"/>
  </form>
</c:if> 
 <c:if test = "${loggedUser != NULL}">  
 <table>
 <tr>
 <td> Name: </td> <td><c:out value="${userName}"/></td>
 </tr>
 <tr>
 <td> Surname: </td> <td><c:out value="${userSurname}"/></td>
 </tr>
 <tr>
 <td> E-mail: </td> <td><c:out value="${loggedUser}"/></td>
 </tr>
 </table>
 <form action="<%= request.getContextPath() %>/account" method="post">
 <input type="submit" value="Log out" name="logout"/>
 </form>
</c:if>
</body>
</html>
