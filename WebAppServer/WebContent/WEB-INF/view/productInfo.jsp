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
	<li><a href="products" class="active"><i class="fa fa-desktop" aria-hidden="true"></i><b> PRODUCTS</b></a></li>
	<li><a href="contact"><i class="fa fa-info" aria-hidden="true"><b> CONTACT US!</b></i></a></li>
	<li style="float:right"><a href="account"><i class="fa fa-user" aria-hidden="true"></i></a></li>
	<li style="float:right"><a href="cart"><i class="fa fa-shopping-cart" aria-hidden="true"></i> </a></li>
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
<footer>
		<div class="footer"> &copy; 2020 Copyright:
	      <a href="index"> Logo.com</a>
	    </div>
	</footer>	
</body>
</html>
