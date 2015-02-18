<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
		.loadingBar{position:absolute; left:0; top:0;bottom:0;right:0; background: url(resources/images/ajax-loader.gif) no-repeat center center; z-index:9; display:none;}
	</style>
 </head>
 
 <body>
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
		<div class="row">
			<div class="clearfix">
			</div>
			<div class="col-sm-10 col-sm-offset-1">	
				
				<div class="panel panel-primary">
				  <div class="panel-heading">
					<h3 class="panel-title">Add Post Press</h3>
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
								<c:forEach items="${customers}" var="element"> 
									<option id="${element.name}">${element.name}</option>
								</c:forEach>
							</select>
						</div>
					  </div>
					  
					  <div class="form-group" id="divExtraParentType">
						<label for="selectParentExtraType" class="col-sm-3 control-label">Parent Post Press Type</label>
						<div class="col-sm-9">
							<select class="form-control" id="selectParentExtraType">
								<c:forEach items="${parentExtraList}" var="element"> 
									<option id="${element.id}">${element.name}</option>
								</c:forEach>
							</select>
						</div>
					  </div>
					  
					 <div class="form-group" id="divExtraItemType">
						<label for="selectExtraItemType" class="col-sm-3 control-label">Post Press Item Type</label>
						<div class="col-sm-9">
							<select class="form-control" id="selectExtraItemType">
								<c:forEach items="${extraItemList}" var="element"> 
									<option id="${element.id}">${element.name}</option>
								</c:forEach>
							</select>
						</div>
					  </div>
					  
					  <div class="form-group" id="divExtraItem">
						<label for="inputExtraItemType" class="col-sm-3 control-label">Post Press Item Name</label>
						<div class="col-sm-9">
							<input type="text" class="form-control" id="inputExtraItem" placeholder="Post Press Type Name">
						</div>
					  </div>
					
					  <div class="form-group" id="divPriceType">
					 	<label for="selectPriceType" class="col-sm-3 control-label">Price Type</label>
						<div class="col-sm-9">
								<label class="radio-inline">
							  		<input type="radio" name="radioPrice" id="radioBasePriceId" value="Base Price">Base Price
								</label>
								<label class="radio-inline">
							  		<input type="radio" name="radioPrice" id="radioQuantitativePriceId" value="Quantitative Price">Quantitative Price
								</label>
								<label class="radio-inline">
							  		<input type="radio" name="radioPrice" id="radioSetPriceId" value="Set Price">Set Price
								</label>
								<label class="radio-inline">
							  		<input type="radio" name="radioPrice" id="radioMultiPriceId" value="Multi Price">Multi Price
								</label>
						</div>
					  </div>	
	
					  <div style=display:none; id="formBasePrice">
						<div class="form-group">
							<label for="inputBasePrice" class="col-sm-3 control-label">Base Price</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="inputBasePrice" placeholder="Base Price Amount">
							</div>
						</div>
					  </div>
					  
					  <div style=display:none; id="formQuantitativePrice">
						<div id="divMinimumQty" class="form-group">
							<label for="inputMinimumQty" class="col-sm-3 control-label">Minimum Quantity</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="inputMinimumQty" placeholder="Minimum Quantity">
							</div>
						</div>
						
						<div id="divPricePerItem" class="form-group">
							<label for="inputMinimumPrice" class="col-sm-3 control-label">Minimum Price</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="inputMinimumPrice" placeholder="Minimum Price">
							</div>
						</div>
						
						<div id="divMinimumPrice" class="form-group">
							<label for="inputPricePerItem" class="col-sm-3 control-label">Price Per Item</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="inputPricePerItem" placeholder="Price Per Item">
							</div>
						</div>
					  </div>
					  
					  <div style=display:none; id="formPricePerSet">
						<div id="divItemPerSet" class="form-group">
							<label for="inputItemPerSet" class="col-sm-3 control-label">Item per Set</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="inputItemPerSet" placeholder="Item Per Set">
							</div>
						</div>
						<div id="divPricePerSet" class="form-group">
							<label for="inputPricePerSet" class="col-sm-3 control-label">Price Per Set</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="inputPricePerSet" placeholder="Price Per Set">
							</div>
						</div>
					  </div>
					  
					  <div style=display:none; id="formMultiPrice">
					  	<div id="divThresholdQty" class="form-group">
							<label for="inputThresholdQty" class="col-sm-3 control-label">Threshold Qty</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="inputThresholdQty" placeholder="Threshold Qty">
							</div>
						</div>
						<div id="divLowThresholdPrice" class="form-group">
							<label for="inputLowThresholdPrice" class="col-sm-3 control-label">Low Price</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="inputLowThresholdPrice" placeholder="Low Price">
							</div>
						</div>
						<div id="divHighThresholdPrice" class="form-group">
							<label for="inputHighThresholdPrice" class="col-sm-3 control-label">High Price</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="inputHighThresholdPrice" placeholder="High Price">
							</div>
						</div>
					  </div>
					  
					  <div class="form-group">
						<div class="col-sm-12 text-right">
							<button class="btn btn-primary" id="addExtra">
										  Add Post Press
							</button>
						</div>
					</div>
					</form>
					
					
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
    <script src="resources/js/jquery.min.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
	<script src="resources/js/custom/addExtras.js"></script>
	
 </body>
 
</html>
