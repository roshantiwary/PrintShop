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
		
		<div class="row">
			<div class="clearfix">
			</div>
			<div class="col-sm-10 col-sm-offset-1">	
				
				<div class="panel panel-primary">
				  <div class="panel-heading">
					<h3 class="panel-title">Print Calculator</h3>
				  </div>
				  <div class="panel-body">
				<div class="row">
					<div class="col-sm-2"></div>
						<div class="col-sm-8">
						<form class="form-horizontal" role="form">
					
					  <div class="form-group" id="divCustType">
						<label for="selectCustType" class="col-sm-3 control-label">Customer Type</label>
						<div class="col-sm-9">
							<select class="form-control" id="selectCustType">
								<option>select customer type</option>
								<c:forEach items="${customers}" var="element"> 
									<option id="${element.id}" value="${element.name}">${element.name}</option>
								</c:forEach>
							</select>
						</div>
					  </div>
					  <div class="form-group" id="divVariety">
					  <label for="selectCustType" class="col-sm-3 control-label">Variety</label>
						<div class="col-sm-9">
							<c:forEach items="${varietys}" var="element"> 
								<label class="radio-inline">
							  		<input type="radio" name="radioVariety" id="${element.id}" value="${element.id}">${element.name}
								</label>
							</c:forEach>
						</div>
					  </div>
					  
					<div class="form-group" style=display:none; id="formVarietyType">
						<label for="selectCustType" class="col-sm-3 control-label">Variety Type</label>
						<div class="col-sm-9">
							<select class="form-control" id="selectVarType">
								<option>select variety type</option>
							</select>
						</div>
					</div>	
	
					  <div class="form-group" style=display:none; id="formSizeUp">
						<label for="selectCustType" class="col-sm-3 control-label">Size</label>
						<div class="col-sm-9">
							<select class="form-control" id="selectSizeUp">
								<option>select size</option>
							</select>
						</div>
					  </div>
					  <div class="form-group" id="divHeightWidth">
						<label for="inputUPS" class="col-sm-3 control-label">Height & Width</label>
							<ul class="list-unstyled list-inline col-sm-9">
								<li class="">
									<input type="text" class="form-control" id="inputWidth" placeholder="Width">
								</li>
								<li class="">
									<input type="text" class="form-control" id="inputHeight" placeholder="Height">
								</li>
							</ul>	
					  </div>
					  <div class="form-group">
						<label for="inputSheet" class="col-sm-3 control-label">Sheets</label>
						<div class="col-sm-9">
							  <input type="text" class="form-control" id="inputSheet" disabled="disabled" placeholder="Number of Sheets">
						</div>
					  </div>
					  <div class="form-group" id="divQty">
						<label for="inputEmail3" class="col-sm-3 control-label">Quantity</label>
						<div class="col-sm-9">
						  <input type="email" class="form-control" id="inputQty" placeholder="Quantity">
						</div>
					  </div>
					
					<!-- Extras Section -->  
					<c:forEach var="entry" items="${customerExtraMap}"> 
						<div id="${entry.key}" class="form-group" style=display:none;>
									<c:forEach var="customerEntry" items="${entry.value}">
									<label for="inputExtras" class="col-sm-3 control-label">${customerEntry.key}</label> 
									<div class="col-sm-9">
										<c:forEach items="${customerEntry.value}" var="element">
										<div class="row">
											<div class="checkbox col-sm-6">
											  <label>
											    <input type="checkbox" value="${element.id}" id="${element.id}">
											    ${element.name}
											  </label>
											</div> 
											<div  id="li${element.id}" class="col-sm-6">
												<div class="input-group">	
													<div class="input-group-addon">${element.extraTypePrice.extraItemType}</div>
													<input type="text" class="form-control col-sm-3" id="input${element.id}" placeholder="QTY">
												</div>
											</div>
										</div>
										<br/>
										</c:forEach>
									</div>		
									<br/>
									</c:forEach>
						</div>
					</c:forEach>
					  
					  <div class="form-group">
						<div class="col-sm-12 text-right">
							<button class="btn btn-primary" id="calculatePrice">
										  Calculate Price
							</button>
						</div>
					  </div>
					</form>
										
					<div class="col-xs-12 well">
						<div class="subtotal-inner left col-xs-6"><strong>Print Price</strong></div>
						<div class="subtotal-inner right col-xs-6 text-right" id="printAmt">
								<strong><span id="inputPrintAmt"></span></strong>
						</div>
						<div class="clearfix">
						</div>
						<div class="subtotal-inner left col-xs-6"><strong>Post Press Price</strong></div>
						<div class="subtotal-inner right col-xs-6 text-right" id="extrasAmt">
								<strong><span id="inputExtrasAmt"></span></strong>
						</div>
						<div class="clearfix">
						</div>
						<div class="subtotal-inner left col-xs-6"><strong>Total</strong></div>
						<div class="subtotal-inner right col-xs-6 text-right" id="subtotalAmt">
								<strong><span id="inputSubtotalAmt"></span></strong>
						</div>
					</div>
					
						</div>
					<div class="col-sm-2"></div>
				</div>	
					
				  </div>
				</div>
			</div>
		</div>
		
	<hr/>
	
	<div class="footer">
       <p>&copy; Print Shop 2014</p>
    </div>
	</div>
	
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/custom/index.js"></script>
	
 </body>
 
</html>
