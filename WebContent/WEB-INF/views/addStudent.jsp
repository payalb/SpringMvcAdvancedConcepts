<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    <%@taglib uri="http://www.springframework.org/tags" prefix="tag" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"/>
</head>
<body>
<form:form class="form-inline" action="./addStudent" method="post" modelAttribute="student">
  <div class="form-group mb-2">
    <label for="name" class="sr-only" >
    <tag:message code="name"></tag:message>
    </label>
    <form:input type="text"  class="form-control-plaintext" path="name" />
  </div>
  <div class="form-group mx-sm-3 mb-2">
     <label  for="password" class="sr-only">
     <tag:message code="password"></tag:message>
     </label>
    <form:input type="password" class="form-control" path="password" placeholder="Password" />
  </div>
  <button type="submit" class="btn btn-primary mb-2">
  <tag:message code="submit"></tag:message>
  </button>
</form:form>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script> 

</body>
</html>