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

.center {
  margin-left: auto;
  margin-right: auto;
}

p {
margin: 20px;
}

input[type=button]#A1 {
  background-color: red;
  border: none;
  color: black;
  padding: 16px 32px;
  text-decoration: none;
  margin: 4px 2px;
  cursor: pointer;
}

input[type=button]#A2 {
  background-color: blue;
  border: none;
  color: white;
  padding: 16px 32px;
  text-decoration: none;
  margin: 4px 2px;
  cursor: pointer;
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
<table class="center">
<tr>
	<td>
	<p style="text-align: center;"><img src="${selectedItem.getImage()}"class="img-responsive" width="200" height="200"><br></p>
	</td>
	<td>
	<p style="text-align: center;"><font size="+2"><c:out value="${selectedItem.getName()}"></c:out></font></p>
	</td>
	<td>
	<p style="text-align: center;">Ilosc: <c:out value="${selectedItem.getQuantity()}"></c:out></p>
	</td>
	<td>
	<p style="text-align: center;">Cena: <c:out value="${selectedItem.getPrice()}" ></c:out>zl</p>
	</td>
	<td>
	<p style="text-align: center;"><a href="cart?id=${selectedItem.getId()}&action=ordernow">Order Now</a></p>
	</td>
	<c:if test = "${loggedUser != NULL}">
	<td>
	<c:if test = "${observed == true}">
		<input type="button" id=A1 onClick="javascript:window.location='products?id=${selectedItem.getId()}&observe=no';" value="Observed"/>
	</c:if>
	<c:if test = "${observed == false}">
		<input type="button" id=A2 onClick="javascript:window.location='products?id=${selectedItem.getId()}&observe=yes';" value="Observe"/>
	</c:if>
	</td>
	</c:if>
</tr>
</table>
</body>
</html>
