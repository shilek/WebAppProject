<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
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
</style>
<meta charset="ISO-8859-1">
<title>AdminPanel</title>
</head>
<body>
<div>
	<div align="center" id="logo" style="height:100px">LOGO</div>
</div>
<ul>
	<li><a href="">Home</a></li>
	<li><a href="products">Products</a></li>
	<li><a href="contact">Contact</a></li>
	<li><a href="register">Add item</a></li>
	<li style="float:right"><a href="">Account</a></li>
	<li style="float:right"><a href="">Cart: </a></li>
</ul>
<h1>Products</h1>
  <table>
				<thead>
					<tr>
						<th>Image</th>
						<th>Name</th>
						<th>Quantity</th>
						<th>Price</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${itemsList}">

						<tr>
							<td><img src="${item.getImage()}"></img></td>
							<td><c:out value="${item.getName()}" /></td>
							<td><c:out value="${item.getQuantity()}" /></td>
							<td><c:out value="${item.getPrice()}" /></td>
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
</body>
</html>