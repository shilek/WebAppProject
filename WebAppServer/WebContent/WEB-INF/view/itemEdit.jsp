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

.footer {
position: fixed;
bottom: 0;
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
	<li><a href="itemRegister">Add Item</a></li>
	<li><a href="itemEdit">Edit Item</a></li>
	<li style="float:right"><a href="account">Account</a></li>
	<li style="float:right"><a href="cart">Cart: </a></li>
</ul>
<h1>Item Edit</h1>
<c:if test = "${pickedItemToEdit == NULL}">
  <form action="<%= request.getContextPath() %>/itemEdit" method="post">
   <input list="items" name="pickedItem">
   <datalist id="items">
   <c:forEach var="item" items="${itemsList}">
   <option value="${item.getId()}, ${item.getName()}">
   </c:forEach>
   </datalist>
   <input type="submit" value="Submit" />
  </form>
  </c:if>
  
  <c:if test = "${pickedItemToEdit != NULL}">
  <form action="<%= request.getContextPath() %>/itemEdit" method="post">
  <table>
  <tr>
     <td>Name: </td>
     <td><input type="text" name="name" value="${pickedItemToEdit.getName()}"/></td>
    </tr>
    <tr>
     <td>Quantity: </td>
     <td><input type="text" name="quantity" value="${pickedItemToEdit.getQuantity()}"/></td>
  </tr>
    <tr>
     <td>Price: </td>
     <td><input type="text" name="price" value="${pickedItemToEdit.getPrice()}"/></td>
     </tr>
     <tr>
     <td>Image: </td>
     <td><input type="text" name="image" value="${pickedItemToEdit.getImage()}"/></td>
     </tr>
     <tr>
     <td>Category: </td>
     <td><input type="text" name="category" value="${pickedItemToEdit.getCategory()}"/></td>
     </tr>
  </table>
  <input type="submit" value="Submit" />
  </form>
    Sciagawka kategorii<br>
  0- brak
  1- karta graficzna<br>
  2- procesor<br>
  3- plyta glowna<br>
  4- nosnik pamieci<br>
  5- zasialnie<br>
  6- chlodzenie<br>
  </c:if>
</body>
</html>