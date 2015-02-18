<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
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
					<h3 class="panel-title">Show Extras</h3>
				  </div>
				  <div class="panel-body">
						<table class="table table-hover table-bordered">
							<tr>
								<!-- <th>#</th>  -->
								<th>Name</th>
								<th>Customer</th>
								<th>Parent Type</th> 
								<th>Price Details</th> 
								<th>Delete</th>
							</tr>
							<c:forEach items="${extraList}" var="element"> 
							<tr>
								<td>${element.name}</td>
								<td>${element.customerTypeName}</td>
								<td>${element.parentExtraName}</td> 
								<td>
									<c:if test="${fn:contains(element.extraTypePrice, 'QuantitativePrice')}">
									   <ul>
									   	<li>Minimum Qty: ${element.extraTypePrice.minimumQty}</li>
									   	<li>Minimum Price: ${element.extraTypePrice.minimumPrice}</li>
									   	<li>Price per item: ${element.extraTypePrice.pricePerItem}</li>
									   </ul>
									</c:if>
									<c:if test="${fn:contains(element.extraTypePrice, 'BasePrice')}">
									    <ul>
										   	<li>Price per item: ${element.extraTypePrice.price}</li>
									   </ul>
									</c:if>
									<c:if test="${fn:contains(element.extraTypePrice, 'MultiPrice')}">
									   <ul>
									   	<li>Threshold Qty: ${element.extraTypePrice.thresholdQty}</li>
									   	<li>Low Threshold Price: ${element.extraTypePrice.lowThresholdPrice}</li>
									   	<li>High Threshold item: ${element.extraTypePrice.highThresholdPrice}</li>
									   </ul>
									</c:if>
									<c:if test="${fn:contains(element.extraTypePrice, 'PricePerSet')}">
									   <ul>
									   	<li>Price Per Set: ${element.extraTypePrice.pricePerSet}</li>
									   	<li>Items Per Set: ${element.extraTypePrice.itemPerSet}</li>
									   </ul>
									</c:if>
								</td>
								<td>
									<button class="btn btn-primary deleteExtras" data-id="${element.id}">
									  Delete
									</button>
								</td>
							</tr>
							</c:forEach>
						</table>
					   
					 <!-- Delete Customer Type Modal -->
							<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							  <div class="modal-dialog">
								<div class="modal-content">
								  <div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
									<h4 class="modal-title" id="myModalLabel">Delete Customer Type</h4>
								  </div>
								  <form role="form" id="formDeleteExtras">
								  <div class="modal-body">
									<div class="row">
										<p class="text-center">Are you sure you want to delete <mark>Extra</mark> ?</p>
										<input type="hidden" name="extrasId" value="" id="delete-extras-id">
									</div>
								  </div>
								  <div class="modal-footer">
									<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
									<button type="submit" id="btnDeleteCustomer" class="btn btn-primary">Save changes</button>
								  </div>
								  </form>
								</div>
							  </div>
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
	<script src="resources/js/custom/showExtras.js"></script>
	
	
 </body>
 
</html>