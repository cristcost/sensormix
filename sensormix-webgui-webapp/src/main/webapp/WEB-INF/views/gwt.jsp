<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<%
  java.util.List<String> items = java.util.Arrays.asList("one", "two", "three");
  pageContext.setAttribute("items", items);
%>
<c:set var="title" value="Spring MVC In ServiceMix Example" />

<title><c:out value="${title}" /></title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link href="styles/bootstrap.min.css" rel="stylesheet" media="screen">
<!-- <script type="text/javascript" src="gwtdemoapp/gwtdemoapp.nocache.js"></script> -->
</head>
<body>
	<iframe src="javascript:''" id="__gwt_historyFrame" tabIndex='-1'
		style="position: absolute; width: 0; height: 0; border: 0"></iframe>

	<div class="container">
		<div class="jumbotron">
			<h1>
				<c:out value="${title}" />
			</h1>
			<p>GWT with Backend of Spring MVC</p>
		</div>
	</div>
</body>
</html>