<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
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
html {
  box-sizing: border-box;
}
*, *:before, *:after {
  box-sizing: inherit;
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

.column {
  float: left;
  width: 30%;
  padding: 5px;
}

/* Clear floats after image containers */
.row::after {
  content: "";
  clear: both;
  display: table;
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


.column {
  float: left;
  width: 33.3%;
  margin-bottom: 8px;
  padding: 0 8px;
}

.card {
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
  margin: 8px;
}

.about-section {
  padding: 30px;
  text-align: center;
  background-color: #474e5d;
  color: white;
}

.container {
  padding: 0 16px;
}

.container::after, .row::after {
  content: "";
  clear: both;
  display: table;
}


.title {
  color: grey;
}






</style>
<meta charset="UTF-8">
<title>Contact</title>
</head>
<body>
<div>
	<img src="images/logotest.jpg" alt="Logo" width="300" height="105" class="center">
</div>
<ul>
	<li><a href="index"><i class="fa fa-home"></i></a></li>
	<li><a href="products"><i class="fa fa-desktop" aria-hidden="true"></i><b> PRODUCTS</b></a></li>
	<li><a href="contact" class="active"><i class="fa fa-info" aria-hidden="true"><b> CONTACT US!</b></i></a></li>
	<li style="float:right"><a href="account"><i class="fa fa-user" aria-hidden="true"></i></a></li>
	<li style="float:right"><a href="cart"><i class="fa fa-shopping-cart" aria-hidden="true"></i> </a></li>
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
<h3>Regulamin</h3>

<h4 style="text-align:center">NASZ ZESPOL</h4>
<div class="row">
  <div class="column">
    <div class="card">
      <img src="images/avatar.png" alt="Dawid" style="width:100%">
      <div class="container">
        <h4>Dawid S</h4>
        <p class="title">Scrum Master and Backend Developer</p>
     	<p>prywantymail@prywantymail.com</p>
      </div>
    </div>
  </div>

  <div class="column">
    <div class="card">
      <img src="images/avatar.png" alt="Mikolaj" style="width:100%">
      <div class="container">
        <h4>Mikolaj R</h4>
        <p class="title">Backend Developer and Frontend Developer</p>
        <p>prywantymail@prywantymail.com</p>
      </div>
    </div>
  </div>
  
  <div class="column">
    <div class="card">
      <img src="images/avatar.png" alt="John" style="width:100%">
      <div class="container">
        <h4>Daniel M</h4>
        <p class="title">Frontend Developer and Backend Developer</p>
        <p>tojest@prywantymail.com</p>
      
      </div>
    </div>
  </div>
</div>



<h3>Kontakt z administracja</h3>
<form action="<%= request.getContextPath() %>/contact" method="post">
   <table style="with: 80%">
    <tr>
     <td>E-mail: </td>
     <td><input type="text" name="email" /></td>
    </tr>
    <tr>
     <td>Subject:  </td>
     <td><input type="text" name="subject" /></td>
    </tr>
    <tr>
     <td>Problem:  </td>
     <td><input type="text" name="text" /></td>
    </tr>
   </table>
   <input type="submit" value="Submit" />
  </form>
<footer>
		<div class="footer"> &copy; 2020 Copyright:
	      <a href="index"> Logo.com</a>
	    </div>
	</footer>	

</body>
</html>