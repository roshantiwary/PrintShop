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
					<h3 class="panel-title">Add Pricing for Variety Type</h3>
				  </div>
				  <div class="panel-body">
						<table class="table table-hover table-bordered">
							<tr>
								<!-- <th>#</th>  -->
								<th>Customer</th> 
								<th>Variety Type</th>
								<th>Size</th>
								<th>Each Price</th>
								<th>Price Above 10</th>
								<th>Price Above 50</th>
								<th>Price Above 100</th>
								<th>Price Above 200</th>
								<th>Price Above 500</th> 	
								<th>Update</th> 
							</tr>
							<c:forEach items="${priceList}" var="element"> 
							<tr>
								<!--  <td>#</td>  -->
								<td>${element.customerType.name}</td>
								<td>${element.varietyType.name}</td>
								<td>${element.sizeUp.name}</td>
								<td>${element.eachPrice}</td>
								<td>${element.priceAbove10}</td>
								<td>${element.priceAbove50}</td>
								<td>${element.priceAbove100}</td>
								<td>${element.priceAbove200}</td>
								<td>${element.priceAbove500}</td>
								<td>
									<button class="btn btn-primary editPricing" data-id="${element.id}" data-each="${element.eachPrice}"
									data-above10="${element.priceAbove10}" data-above50="${element.priceAbove50}" data-above100="${element.priceAbove100}"
									data-above200="${element.priceAbove200}" data-above500="${element.priceAbove50}">
									  update
									</button>
								</td> 
							</tr>
							</c:forEach>
						</table>
							
							<!-- Edit Customer Type Modal -->
							<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							  <div class="modal-dialog">
								<div class="modal-content">
								  <div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
									<h4 class="modal-title" id="myModalLabel">Update Price</h4>
								  </div>
								  <form role="form" id="formEditPricing">
								  <div class="modal-body">
									<div class="row">
									<div class="form-group">
										<label for="inputCustType" class="col-sm-3 control-label">Each Price</label>
										<div class="col-sm-9">
										  <input type="text" class="form-control" id="editInputEachPrice" placeholder="Variety" value="edit-variety-name">
										  <input type="hidden" name="pricingId" value="" id="edit-pricing-id">
										</div>
									</div>
									<div class="form-group">
										<label for="inputCustType" class="col-sm-3 control-label">Price Above 10</label>
										<div class="col-sm-9">
										  <input type="text" class="form-control" id="editInputPriceAbove10" value="edit-variety-name">
										  <input type="hidden" name="varietyId" value="" id="edit-variety-id">
										</div>
									</div>
									<div class="form-group">
										<label for="inputCustType" class="col-sm-3 control-label">Price Above 50</label>
										<div class="col-sm-9">
										  <input type="text" class="form-control" id="editInputPriceAbove50" value="edit-variety-name">
										  <input type="hidden" name="varietyId" value="" id="edit-variety-id">
										</div>
									</div>
									<div class="form-group">
										<label for="inputCustType" class="col-sm-3 control-label">Price Above 100</label>
										<div class="col-sm-9">
										  <input type="text" class="form-control" id="editInputPriceAbove100" value="edit-variety-name">
										  <input type="hidden" name="varietyId" value="" id="edit-variety-id">
										</div>
									</div>
									<div class="form-group">
										<label for="inputCustType" class="col-sm-3 control-label">Price Above 200</label>
										<div class="col-sm-9">
										  <input type="text" class="form-control" id="editInputPriceAbove200" value="edit-variety-name">
										  <input type="hidden" name="varietyId" value="" id="edit-variety-id">
										</div>
									</div>
									<div class="form-group">
										<label for="inputCustType" class="col-sm-3 control-label">Price Above 500</label>
										<div class="col-sm-9">
										  <input type="text" class="form-control" id="editInputPriceAbove500" value="edit-variety-name">
										  <input type="hidden" name="varietyId" value="" id="edit-variety-id">
										</div>
									</div>
									
									</div>
								  </div>
								  <div class="modal-footer">
									<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
									<button type="submit" id="btnEditVariety" class="btn btn-primary">Save changes</button>
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
	<script src="resources/js/custom/pricing.js"></script>
	
 </body>
 
</html>