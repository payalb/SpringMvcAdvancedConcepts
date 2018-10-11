<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error Page</title>
</head>
<body>

Something went wrong!!!!!<br>
<h3>This is example exception page</h3>
 <p>Exception: <b>${exceptionType}</b></p>
 <p>Handler method: <b> ${handlerMethod} </b></p>
${message}
</body>
</html>