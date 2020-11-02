<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
</style>
<title>Home</title>
</head>
<body>
<div>
	<div align="center" id="logo" style="height:100px">LOGO</div>
</div>
<ul>
	<li><a class="active" href="">Home</a></li>
	<li><a href="/">Products</a></li>
	<li><a href="/">Contact</a></li>
	<li>
		<a href="register">Add Item</a>
	</li>
	<li style="float:right"><a href="">Account</a></li>
	<li style="float:right"><a href="">Cart: </a></li>
</ul>
<div></div>

</body>
</html>
