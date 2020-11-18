<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
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
</style>
<meta charset="UTF-8">
<title>Contact</title>
</head>
<body>
<div>
	<div align="center" id="logo" style="height:100px">LOGO</div>
</div>
<ul>
	<li><a href="">Home</a></li>
	<li><a href="products">Products</a></li>
	<li><a href="contact">Contact</a></li>
	<li><a href="register">Add Item</a></li>
	<li style="float:right"><a href="">Account</a></li>
	<li style="float:right"><a href="">Cart: </a></li>
</ul>

<h1>About Us!</h1>
<p>Nasza historia rozpoczela sie w 2020 roku z chwila rozpoczecia projektu na przedmiot Zaawansowane technologie uslug sieciowych. 
Od tej pory nieustannie się rozwijamy, dzięki czemu za kilka lat staniemy sie ekspertami w branzy komputerowej. 
Wyroznia nas empatia i wiedza technologiczna, co pozwala nam lepiej rozumiec potrzeby naszych Klientow. Jestesmy otwarci na sugestie i oferujemy nowe, ciekawe, ale i sprawdzone rozwiazania.</p>
<h2>Dane firmy</h2>
<p>Logo</p>
<p>ul. Szafrana 2</p>
<p>65-001 Zielona Góra</p>
<h3>Dostawa</h3>
<h4>Regulamin</h4>

<footer>
		<div class="footer"> &copy; 2020 Copyright:
	      <a href="Controller?page=index"> Logo.com</a>
	    </div>
	</footer>	

</body>
</html>