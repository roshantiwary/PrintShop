<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="en">
 <head>
	<title>print shop</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	
	 <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
	<style>
		.loadingBar{position:absolute; left:0; top:0;bottom:0;right:0; background: url(resources/images/ajax-loader.gif) no-repeat center center; z-index:9; display:none;}
	</style>
 </head>
 
 <body><c:set var="contextPath" value="${pageContext.request.contextPath}"/>
 <div class="loadingBar"> </div>
	<div class="container">
	
	<div class="header">
        <ul class="nav nav-pills pull-right">
          <li class="active"><a href="${pageContext.request.contextPath}/">Calculator</a></li>
          <li><a href="${pageContext.request.contextPath}/admin/admin">Admin</a></li>
          <c:url var="logoutUrl" value="/j_spring_security_logout"/>
         	<sec:authorize access="authenticated">
         		<li>
         			<a href="${pageContext.request.contextPath}/${logoutUrl}">Log out</a>
         		</li>
         	</sec:authorize>
        </ul>
        <h3 class="text-muted"><a href="${pageContext.request.contextPath}/"><img src="${pageContext.request.contextPath}/resources/images/logo.jpg" width="175" height="44"/></a></h3>
    </div>
		
<hr/>