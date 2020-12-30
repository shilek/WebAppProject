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
<div></div>
<a href="products">Continue Shopping</a>
<table>
<tr>
<td>Id</td>
<td>Image</td>
<td>Name</td>
<td>Price</td>
<td>Quantity</td>
</tr>
<c:forEach var="item" items="${sessionScope.cart}">
<tr>
<td>${item.getItem().getId()}</td>
<td><img src="${item.getItem().getImage()}" class="img-responsive" width="400" height="300"><br></td>
<td>${item.getItem().getName()}</td>
<td>${item.getItem().getPrice() * item.getQuantity()}</td>
<td>${item.getQuantity()}</td>
<td><a href="cart?id=${item.getItem().getId()}&action=delete" onClick="return confirm('Are you sure?')">Delete</a></td>
</tr>
</c:forEach>
</table>
<input type="button" onClick="javascript:window.location='buy?action=step1';" value="Buy"/>
</body>
</html>
