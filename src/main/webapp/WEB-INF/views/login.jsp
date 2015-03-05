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
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
	<style>
		.loadingBar{position:absolute; left:0; top:0;bottom:0;right:0; background: url(images/ajax-loader.gif) no-repeat center center; z-index:9; display:none;}
	</style>
 </head>
 
<body>
<c:url value="/login" var="loginUrl" />
<div class="loadingBar"> </div>
<div class="container">
	
<div class="header">
       <ul class="nav nav-pills pull-right">
         <li><a href="/PrintShop">Calculator</a></li>
         <li class="active"><a href="admin">Admin</a></li>
       </ul>
       <h3 class="text-muted"><img src="resources/images/logo.jpg" width="175" height="44"></h3>
</div>
		
<hr/>
<div class="container">
<!-- Example row of columns -->
<div class="row">

<form:form method="post" action="${loginUrl}" modelAttribute="loginForm" class="form-horizontal">
	<form:errors path="*" element="div" cssClass="alert alert-error"/>
	<c:if test="${param.logout != null}">
		<div class="alert alert-success">
			You have been logged out.
		</div>
	</c:if>
  <div class="form-group">
    <label for="inputUser" class="col-sm-2 control-label">Email</label>
    <div class="col-sm-10">
    	<form:input path="username" id="inputUser"/>
    </div>
  </div>
  <div class="form-group">
    <label for="inputPassword" class="col-sm-2 control-label">Password</label>
    <div class="col-sm-10">
      <form:password path="password" id="inputPassword" />
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <div class="checkbox">
        <label>
          <input type="checkbox"> Remember me
        </label>
      </div>
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" class="btn btn-default">Sign in</button>
    </div>
  </div>
</form:form>
</div>	
<hr/>

<div class="footer">
      <p>&copy; Print Shop 2014</p>
   </div>
</div>
</div>
</body>
 
</html>
