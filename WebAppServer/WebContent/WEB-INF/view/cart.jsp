<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href='https://fonts.googleapis.com/css?family=Lato' rel='stylesheet'>
<style>
body {
    font-family: 'Lato';font-size: 16px;
}
ul {
  list-style-type: none;
  margin: 0;
  padding: 0;
  overflow: hidden;
  background-color: #666;
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
    background-color: #ddd;
  color: black;
}
li a.active {
  background-color: #888;
}

.footer {
padding: 20px;
text-align: center;
background: #ddd;
bottom: 0;
}

.center {
  display: block;
  margin-left: auto;
  margin-right: auto;
  width: 30%;
}



</style>
<title>Home</title>
</head>
<body>
<div>
	<img src="images/logotest.jpg" alt="Logo" width="300" height="105" class="center">
</div>
<ul>
	<li><a href="index"><i class="fa fa-home"></i></a></li>
	<li><a href="products"><i class="fa fa-desktop" aria-hidden="true"></i><b> PRODUCTS</b></a></li>
	<li><a href="contact"><i class="fa fa-info" aria-hidden="true"><b> CONTACT US!</b></i></a></li>
	<li style="float:right"><a href="account"><i class="fa fa-user" aria-hidden="true"></i></a></li>
	<li style="float:right"><a href="cart" class="active"><i class="fa fa-shopping-cart" aria-hidden="true"></i> </a></li>
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

<footer>
		<div class="footer"> &copy; 2020 Copyright:
	      <a href="index"> Logo.com</a>
	    </div>
	</footer>	
</html>
