<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<h1>Item Register</h1>
  <form action="<%= request.getContextPath() %>/itemRegister" method="post">
   <table style="with: 80%">
    <tr>
     <td>name</td>
     <td><input type="text" name="name" /></td>
    </tr>
    <tr>
     <td>quantity</td>
     <td><input type="text" name="quantity" /></td>
    </tr>
    <tr>
     <td>price</td>
     <td><input type="text" name="price" /></td>
     </tr>
     <tr>
     <td>image</td>
     <td><input type="text" name="image" /></td>
     </tr>
     <tr>
     <td>category</td>
     <td><input type="text" name="category" /></td>
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
</body>
</html>