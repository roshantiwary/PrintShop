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
		.loadingBar{position:absolute; left:0; top:0;bottom:0;right:0; background: url(${pageContext.request.contextPath}/resources/images/ajax-loader.gif) no-repeat center center; z-index:9; display:none;}
	</style>
 </head>
 
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:url value="${contextPath}/login" var="loginUrl" />
<c:url value="${contextPath}/register" var="registerUrl"/>
<div class="loadingBar"> </div>
<div class="container">
	
<div class="header">
       <ul class="nav nav-pills pull-right">
         <li><a href="${pageContext.request.contextPath}/">Calculator</a></li>
         <li class="active"><a href="${pageContext.request.contextPath}/admin/admin">Admin</a></li>
         <c:url var="logoutUrl" value="${pageContext.request.contextPath}/j_spring_security_logout"/>
         	<sec:authorize access="authenticated">
         		<li>
         			<a href="${pageContext.request.contextPath}/${logoutUrl}">Log out</a>
         		</li>
         	</sec:authorize>
       </ul>
       <h3 class="text-muted"><a href="${pageContext.request.contextPath}/"><img src="${pageContext.request.contextPath}/resources/images/logo.jpg" width="175" height="44"/></a></h3>
</div>
		
<hr/>

<sec:authorize access="hasAnyRole('ROLE_ADMINISTRATOR')">
<div class="table-responsive">

  <table class="table table-hover table-bordered">
	<tr class="info">
		<td><strong>User Name</strong></td>
		<td><strong>First Name</strong></td>
		<td><strong>Last Name</strong></td>
		<td><strong>Status</strong></td>
		<td><strong>Action</strong></td>
		<td><strong>Modify Role</strong></td>
	</tr>
	<c:forEach items="${userList}" var="element"> 
		<c:set var="adminRole" value="" />
		<c:set var="superAdminRole" value="" />
		<c:forEach items="${element.roles}" var="role">
			<c:if test="${role.id eq 'ROLE_ADMIN'}">
				<c:set var="adminRole" value="${role.id}" />
			</c:if>
			<c:if test="${role.id eq 'ROLE_ADMINISTRATOR'}">
				<c:set var="superAdminRole" value="${role.id}" />
			</c:if>
		</c:forEach>
		<tr>
			<td>${element.username}</td>
			<td>${element.firstname}</td>
			<td>${element.lastname}</td>
			<c:choose>
				<c:when test="${element.enabled}">
					<td id="${element.username}_userStatusMsg">Enabled</td>
					<td><button id="${element.username}_changeUserStatus" data-username="${element.username}" data-status="false" type="submit" class="changeUserStatus btn btn-success">Disable</button></td>
				</c:when>
				<c:otherwise>
					<td id="${element.username}_userStatusMsg">Disabled</td>
					<td><button id="${element.username}_changeUserStatus" data-username="${element.username}" data-status="true" type="submit" class="changeUserStatus btn btn-danger">Enable</button></td>
				</c:otherwise>
			</c:choose>
			<td>
				<c:choose>
					<c:when test="${not empty adminRole && not empty superAdminRole}">
						<button id="${element.username}_changeUserRole" data-username="${element.username}" type="submit" class="changeUserRole btn btn-success">Revoke All Admin Rights</button>
					</c:when>
					<c:when test="${(not empty adminRole && empty superAdminRole) || (empty adminRole && not empty superAdminRole)}">
						<button id="${element.username}_changeUserRole" data-username="${element.username}" type="submit" class="changeUserRole btn btn-warning">Revoke Admin Rights</button>
					</c:when>
					<c:otherwise>
						<button id="${element.username}_changeUserRole" data-username="${element.username}" type="submit" class="changeUserRole btn btn-danger">Give Admin Rights</button>
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
	</c:forEach>
  </table>
</div>
</sec:authorize>

<sec:authorize ifAllGranted="ROLE_USER">
	<h3>Not Authorized!!!</h3>
	<p>Please sign in as Administrator to access this page.</p>
</sec:authorize>

<div class="modal fade" id="editStatus" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
				<h4 class="modal-title" id="myModalLabel">Modify User Status</h4>
			</div>
			<form role="form" id="formChangeStatus">
				<div class="modal-body">
						Are you sure want you want to change <strong><span id="statusStr"></span></strong> the user status?
						<input type="hidden" name="eleUsername" value="" id="eleUsernameId"/>
						<input type="hidden" name="eleStatus" value="" id="eleUserStatus"/>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="submit" id="btnChangeStatus" class="btn btn-primary">Save changes</button>
				</div>
			</form>
		</div>
	</div>
</div>	

<div class="modal fade" id="editRole" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
				<h4 class="modal-title" id="myModalLabel">Modify Roles for Admin Rights</h4>
			</div>
			<form role="form" id="formChangeRole">
				<div class="modal-body">
					<input type="hidden" name="eleUsername" value="" id="eleUsernameId"/>
					<input type="hidden" name="eleRole" value="" id="eleUserRole"/>
					<div class="checkbox">
					   <label>
					     <input type="checkbox" id="roleadmin">admin
					   </label>
					</div>
					<div class="checkbox">
					   <label>
					     <input type="checkbox" id="rolesuperadmin">super admin
					   </label>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="submit" id="btnChangeRole" class="btn btn-primary">Save changes</button>
				</div>
			</form>
		</div>
	</div>
</div>	


<div class="footer">
    <p>&copy; Print Shop 2014</p>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/custom/manageusers.js"></script>

</body>
 
</html>