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
/* Ten kontener przechowuje 3 obrazki */
.column {
  float: left;
  width: 33%;
  padding: 5px;
}

/* Clear floats after image containers */
.row::after {
  content: "";
  clear: both;
  display: table;
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
<h1 style="text-align:center">Products</h1>
 	<div class="row">
		<div>

					<c:forEach var="item" items="${itemsList}">
					<div class="column">
							<p style="text-align: center;"><img src="${item.getImage()}"class="img-responsive" width="400" height="300"><br></p>
							<p style="text-align: center;"><font size="+2"><c:out value="${item.getName()}"></c:out></font></p>
							<p style="text-align: center;">Ilosc: <c:out value="${item.getQuantity()}"></c:out></p>
							<p style="text-align: center;">Cena: <c:out value="${item.getPrice()}" ></c:out>zl</p>
							</div>
					</c:forEach>
					<!-- } -->
		</div>
	</div>
	
<footer>
		<div class="footer"> &copy; 2020 Copyright:
	      <a href="Controller?page=index"> Logo.com</a>
	    </div>
	</footer>	
			
			
			
			
			
</body>
</html>