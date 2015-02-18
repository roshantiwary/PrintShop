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
					<h3 class="panel-title">Add Variety Type</h3>
				  </div>
				  <div class="panel-body">
						<table class="table table-hover table-bordered">
							<tr>
								<!-- <th>#</th>  -->
								<th>Name</th> 
								<th>Size</th>
								<th>Variety</th> 	
								<th>Delete</th>
							</tr>
							<c:forEach items="${varietyTypeList}" var="element"> 
							<tr>
								<!--  <td>#</td>  -->
								<td>${element.name}</td>
								<td>
								
								<c:forEach items="${element.sizeUp}" var="sizeElement">
									${sizeElement.name},
								</c:forEach>
								
								</td>
								<td>${element.variety.name}</td> 
								<td>
									<button class="btn btn-primary deleteVarietyType" data-id="${element.id}">
									  Delete
									</button>
								</td>
							</tr>
							</c:forEach>
						</table>
						
						<div class="row">
							<div class="col-sm-8">
							</div>
							<div class="col-sm-4">
							  <!-- Button trigger modal -->
								<button class="btn btn-primary" data-toggle="modal" data-target="#addModal">
								  Add Variety Type
								</button>
							</div>
						</div>
						
						
					   
					 <!-- Delete Variety Type Modal -->
							<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							  <div class="modal-dialog">
								<div class="modal-content">
								  <div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
									<h4 class="modal-title" id="myModalLabel">Delete Variety Type</h4>
								  </div>
								  <form role="form" id="formDeleteVarietyType">
								  <div class="modal-body">
									<div class="row">
										<p class="text-center">Are you sure you want to delete <mark>Variety Type</mark> ?</p>
										<input type="hidden" name="varietyTypeId" value="" id="delete-varietytype-id">
									</div>
								  </div>
								  <div class="modal-footer">
									<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
									<button type="submit" id="btnDeleteVarietyType" class="btn btn-primary">Save changes</button>
								  </div>
								  </form>
								</div>
							  </div>
							</div>
						
						<!-- Add Variety Type Modal -->
							<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							  <div class="modal-dialog">
								<div class="modal-content">
								  <div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
									<h4 class="modal-title" id="myModalLabel">Enter New Variety Type</h4>
								  </div>
								  <form role="form" id="formAddVarietyType">
								  <div class="modal-body">
									<div class="row">
									<div class="col-sm-12">
										<div class="form-group">
											<label for="inputSizeUpName" class="col-sm-3 control-label">Name</label>
											<div class="col-sm-9">
											  <input type="SizeUpName" class="form-control" id="inputVarietyType" placeholder="Size Up Name">
											</div>
										</div>
										
										<div class="clearfix"></div>
										
										<div class="form-group">
											<label for="inputSize" class="col-sm-3 control-label">Size</label>
											<div class="col-sm-9">
												<select multiple class="form-control" id="selectAddSize">
													<c:forEach items="${sizeUpList}" var="elementSize"> 
														<option id="${elementSize.id}" data-width_up="${elementSize.width_up}" data-height_up="${elementSize.height_up}">${elementSize.name}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										
										<div class="clearfix"></div>

										<div class="form-group">
											<label for="inputHeightUp" class="col-sm-3 control-label">Variety</label>
											<div class="col-sm-9">
												<select class="form-control" id="selectAddVariety">
													<c:forEach items="${varietyList}" var="elementVariety"> 
												  		<option id="${elementVariety.id}">${elementVariety.name}</option>
												  	</c:forEach>
												</select>
											</div>
										</div>
									</div>
									</div>
								  </div>
								  <div class="modal-footer">
									<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
									<button type="submit" id="btnAddVarietyType" class="btn btn-primary">Save changes</button>
								  </div>
								  </form>
								</div>
							  </div>
							</div>
							
							<!-- Edit Variety Type Modal -->
							<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							  <div class="modal-dialog">
								<div class="modal-content">
								  <div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
									<h4 class="modal-title" id="myModalLabel">Edit Variety Type</h4>
								  </div>
								  <div class="modal-body">
									<div class="row">
									<div class="col-sm-12">
									<form class="form-horizontal" role="form">
										<div class="form-group">
											<label for="inputSizeUpName" class="col-sm-3 control-label">Name</label>
											<div class="col-sm-9">
											  <input type="SizeUpName" class="form-control" id="inputSizeUpName" placeholder="Size Up Name">
											</div>
										</div>
										<div class="form-group">
											<label for="inputSize" class="col-sm-3 control-label">Size</label>
											<div class="col-sm-9">
												<select multiple class="form-control">
													<c:forEach items="${sizeUpList}" var="elementSize"> 
														<option id="${elementSize.id}">${elementSize.name}</option>
													</c:forEach>
												</select>
											</div>
										</div>

										<div class="form-group">
											<label for="inputHeightUp" class="col-sm-3 control-label">Variety</label>
											<div class="col-sm-9">
												<select class="form-control">
													<c:forEach items="${varietyList}" var="elementVariety"> 
												  		<option id="${elementVariety.id}">${elementVariety.name}</option>
												  	</c:forEach>
												</select>
											</div>
										</div>	
										</form>
									</div>
									</div>
								  </div>
								  <div class="modal-footer">
									<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
									<button type="button" class="btn btn-primary">Save changes</button>
								  </div>
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
	<script src="resources/js/custom/varietytype.js"></script>
	
 </body>
 
</html>