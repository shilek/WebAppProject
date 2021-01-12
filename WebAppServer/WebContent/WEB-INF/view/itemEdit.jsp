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
<meta charset="ISO-8859-1">
<title>AdminPanel</title>
</head>
<body>
<div>
	<img src="images/logotest.jpg" alt="Logo" width="300" height="105" class="center">
</div>
<ul>
	<li><a href="index"><i class="fa fa-home"></i></a></li>
	<li><a href="products"><i class="fa fa-desktop" aria-hidden="true"></i><b> PRODUCTS</b></a></li>
	<li><a href="contact"><i class="fa fa-info" aria-hidden="true"><b> CONTACT US!</b></i></a></li>
	<li><a href="itemRegister" class="active"><i class="fa fa-upload" aria-hidden="true"></i><b> ADD ITEM</b></a></li>
	<li><a href="itemEdit"><i class="fa fa-pencil-square-o" aria-hidden="true"></i><b> EDIT ITEM</b></a></li>
	<li style="float:right"><a href="account"><i class="fa fa-user" aria-hidden="true"></i></a></li>
	<li style="float:right"><a href="cart"><i class="fa fa-shopping-cart" aria-hidden="true"></i> </a></li>
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
  0- brak<br>
  1- karta graficzna<br>
  2- procesor<br>
  3- plyta glowna<br>
  4- nosnik pamieci<br>
  5- zasialnie<br>
  6- chlodzenie<br>
  </c:if>
  <footer>
		<div class="footer"> &copy; 2020 Copyright:
	      <a href="index"> Logo.com</a>
	    </div>
	</footer>	
  
  
</body>
</html>