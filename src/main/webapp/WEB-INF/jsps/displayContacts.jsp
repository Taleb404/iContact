<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2>Contacts: </h2>
<h3>Welcome ${userWel}</h3>
<table>
<tr>
<th>id</th>
<th>First Name</th>
<th>Last Name</th>
<th>Email</th>
<th>Phone</th>
<th>Address</th>
</tr>
<c:forEach items="${contacts}" var="contact">
<tr>
<td>${contact.id}</td>
<td>${contact.firstname}</td>
<td>${contact.lastname}</td>
<td>${contact.email}</td>
<td>${contact.phone}</td>
<td>${contact.address}</td>
<td><a href="deleteContact?id=${contact.id}"> Delete </a></td>
<td><a href="showUpdatePage?id=${contact.id}">Edit</a></td>
</tr>
</c:forEach>
</table>
<br/>
${transF}

 ${submitted}  
 ${notFound}
<a href="createContact" >Add Contact</a>
<a href="logout">Logout</a>
<br>
<a href="export-users">Export To *.CSV</a>
</body>
</html>