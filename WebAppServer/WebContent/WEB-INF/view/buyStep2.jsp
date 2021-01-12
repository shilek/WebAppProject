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

#item{
padding: 20px;
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
  <form action="<%= request.getContextPath() %>/buy?action=step3" method="post">
   <table style="with: 80%">
    <tr>
     <td>Name</td>
     <td><input type="text" name="name" /></td>
    </tr>
    <tr>
     <td>Surname</td>
     <td><input type="text" name="surname" /></td>
    </tr>
    <tr>
     <td>Email</td>
     <td><input type="text" name="email" /></td>
     </tr>
    <tr>
     <td>Address</td>
     <td><input type="text" name="address" /></td>
     </tr>
     <tr>
     <td>City</td>
     <td><input type="text" name="city" /></td>
     </tr>
   </table>
   <input type="submit" value="Buy" />
  </form>
  <footer>
		<div class="footer"> &copy; 2020 Copyright:
	      <a href="index"> Logo.com</a>
	    </div>
	</footer>	
</body>
</html>
