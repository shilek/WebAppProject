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
<table>
<tr>
<td id="item">Image</td>
<td id="item">Name</td>
<td id="item">Price</td>
<td id="item">Quantity</td>
</tr>
<c:forEach var="item" items="${sessionScope.cart}">
<tr>
<td id="item"><img src="${item.getItem().getImage()}" class="img-responsive" width="100" height="100"></td>
<td id="item">${item.getItem().getName()}</td>
<td id="item">${item.getItem().getPrice() * item.getQuantity()}</td>
<td id="item">${item.getQuantity()}</td>
</tr>
</c:forEach>
<c:set var="sum" value="0"></c:set>
<c:forEach var="item" items="${sessionScope.cart}">
<c:set var="sum" value="${sum + item.getItem().getPrice() *  item.getQuantity()}"></c:set>
</c:forEach>
<tr>
<td>Final price:</td>
<td>${sum}</td>
</tr>
</table>
<h1>Buy items</h1>
  <form action="<%= request.getContextPath() %>/buy?action=lastStep" method="post">
   <table style="with: 80%">
    <tr>
     <td>Name: </td>
     <td>${order.get(0)}</td>
    </tr>
    <tr>
     <td>Surname: </td>
     <td>${order.get(1)}</td>
    </tr>
    <tr>
     <td>Email: </td>
     <td>${order.get(2)}</td>
     </tr>
    <tr>
     <td>Address: </td>
     <td>${order.get(3)}</td>
     </tr>
     <tr>
     <td>City: </td>
     <td>${order.get(4)}</td>
     </tr>
   </table>
   <input type="submit" value="Buy" />
  </form>
</body>
</html>
