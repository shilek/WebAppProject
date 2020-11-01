<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Item Register</h1>
  <form action="<%= request.getContextPath() %>/register" method="post">
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
   </table>
   <input type="submit" value="Submit" />
  </form>
</body>
</html>