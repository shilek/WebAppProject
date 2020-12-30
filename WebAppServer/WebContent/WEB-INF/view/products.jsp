<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<%
    response.setCharacterEncoding("UTF-8");
    request.setCharacterEncoding("UTF-8");
%>
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

.center {
  margin-left: auto;
  margin-right: auto;
}

/* Clear floats after image containers */
.row::after {
  content: "";
  clear: both;
  display: table;
}

#newProduct {
display: block;
color: black;
text-decoration: none;
}

#newProduct:hover{
color: blue;
text-decoration: underline;
}

h1 {
background-color: lightblue;
}

.container {
position: relative;
width: 100%;
}

.left {
width: 10%;
float: left;
background-color: lightblue;
}

.right {
width: 75%;
margin-left: 20%;
margin-right: auto;
}

#categoryHref {
color: black;
text-decoration: none;
}

#categoryHref:hover{
color: blue;
text-decoration: underline;
}

.footer {
position: fixed;
bottom: 0;
}

.search{
width: 60%;
height: 50px;
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
	<li><a href="index">Home</a></li>
	<li><a href="products">Products</a></li>
	<li><a href="contact">Contact</a></li>
	<li style="float:right"><a href="account">Account</a></li>
	<li style="float:right"><a href="cart">Cart: </a></li>
</ul>
<h1 style="text-align:center">Products</h1>
<div class=container>
<div class=search>
<form action="<%= request.getContextPath() %>/products?action=search" method="post">
   <input list="items" name="searchedItem">
   <datalist id="items">
   <c:forEach var="item" items="${itemsList}">
   <option value="${item.getName()}">
   </c:forEach>
   </datalist>
   <input type="submit" value="Submit" />
  </form>
</div>
<div class=left>
		<table class=center>
		<tr>
		<td>
		<p style="text-align: center;"><a id=categoryHref href="products">All products</a>
		</td>
		</tr>
				<c:forEach var="category" items="${categories}">
					<tr>
						<td>
							<p style="text-align: center;"><a id=categoryHref href="products?category=${category.getId()}">${category.getName()}</a><br></p>
						</td>
					</tr>	
				</c:forEach>
		</table>
</div>
 	<div class=right>
		<table class=center>
				<c:forEach var="item" items="${itemsList}">
					<tr>
							<td>
							<p style="text-align: center;"><img src="${item.getImage()}"class="img-responsive" width="150" height="150"><br></p>
							</td>
							<td width=80%>
							<p style="text-align: center;"><font size="+2"><a id=newProduct href="products?id=${item.getId()}"><c:out value="${item.getName()}"></c:out></a></font></p>
							</td>
							<td width=10%>
							<p style="text-align: center;">Cena: <c:out value="${item.getPrice()}" ></c:out>zl</p>
							</td>
					</tr>	
				</c:forEach>
		</table>
	</div>
	<div class="footer"> &copy; 2020 Copyright:
	      <a href="index"> Logo.com</a>
	    </div>	
</div>	
			
</body>
</html>