<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
     
    <!-- Tag Library -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head> 
<body>

<!--Scriplet tag /**/  -->	
 
 <!-- 
	 String name = request.getAttribute("label").toString();
		out.print(name);
	 -->
	
	<!-- Expression Tag -->
	
<!--  	${students} <br>  -->
		 
		 <c:forEach items="${students}" var="s">
		 ${s} <br/>
		 </c:forEach>
	
	 <!-- <c:import url="http://www.google.co.in"></c:import> -->
	
</body>
</html>