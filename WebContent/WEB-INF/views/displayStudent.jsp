<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    <%@taglib uri="http://www.springframework.org/tags"  prefix="spring"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student Details</title>
</head>
<body>

${requestScope.message}
<br>
<spring:message code="label1"></spring:message><br>
<spring:message code="name"></spring:message> : ${requestScope.student.name}<br>
<spring:message code="id"></spring:message> : ${requestScope.student.id}<br>
</body>
</html>