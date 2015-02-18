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
					<h3 class="panel-title">Add Size Up</h3>
				  </div>
				  <div class="panel-body">
						<table class="table table-hover table-bordered">
							<tr>
								<!-- <th>#</th>  -->
								<th>Name</th> 
								<th>Height-Up</th>
								<th>Width-Up</th> 	
								<th>Edit</th> 
								<th>Delete</th>
							</tr>
							<c:forEach items="${sizeUpList}" var="element"> 
							<tr>
								<!-- <td>#</td>  -->
								<td>${element.name}</td>
								<td>${element.height_up}</td>
								<td>${element.width_up}</td>
								<td>
									<button class="btn btn-primary editSize" data-id="${element.id}" data-name="${element.name}" data-height="${element.height_up}" 
										data-width="${element.width_up}">
									  Edit
									</button>
								</td> 
								<td>
									<button class="btn btn-primary deleteSize" data-id="${element.id}">
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
								  Add Size Up
								</button>
							</div>
						</div>
						
						
					   
					 <!-- Delete Size Modal -->
							<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							  <div class="modal-dialog">
								<div class="modal-content">
								  <div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
									<h4 class="modal-title" id="myModalLabel">Delete Size Up</h4>
								  </div>
								  <form role="form" id="formDeleteSize">
								  <div class="modal-body">
									<div class="row">
										<p class="text-center">Are you sure you want to delete <mark>Size Up</mark> ?</p>
										<input type="hidden" name="sizeId" value="" id="delete-size-id">
									</div>
								  </div>
								  <div class="modal-footer">
									<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
									<button type="submit" id="btnDeleteSize" class="btn btn-primary">Save changes</button>
								  </div>
								  </form>
								</div>
							  </div>
							</div>
						
						<!-- Add Size Modal -->
							<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							  <div class="modal-dialog">
								<div class="modal-content">
								  <div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
									<h4 class="modal-title" id="myModalLabel">Enter New Size Up</h4>
								  </div>
								  <form role="form" id="formAddSize">
								  <div class="modal-body">
									<div class="row" class="col-sm-12">
										<div class="form-group">
											<label for="inputSizeUpName" class="col-sm-3 control-label">Name</label>
											<div class="col-sm-9">
											  <input type="SizeUpName" class="form-control" id="inputSizeUpName" placeholder="Size Up Name">
											</div>
										</div>
										<div class="form-group">
											<label for="inputHeightUp" class="col-sm-3 control-label">Height-Up</label>
											<div class="col-sm-9">
											  <input type="WidthUp" class="form-control" id="inputWidthUp" placeholder="Height-Up">
											</div>
										</div>

										<div class="form-group">
											<label for="inputHeightUp" class="col-sm-3 control-label">Width-Up</label>
											<div class="col-sm-9">
											  <input type="HeightUp" class="form-control" id="inputHeightUp" placeholder="Width-Up">
											</div>
										</div>
									</div>
								  </div>
								  <div class="modal-footer">
									<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
									<button type="submit" id="btnAddSize" class="btn btn-primary">Save changes</button>
								  </div>
								  </form>
								</div>
							  </div>
							</div>
							
							<!-- Edit Size Modal -->
							<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							  <div class="modal-dialog">
								<div class="modal-content">
								  <div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
									<h4 class="modal-title" id="myModalLabel">Edit Size Up</h4>
								  </div>
								  <form role="form" id="formEditSize">
								  <div class="modal-body">
									<div class="row class="col-sm-12">
										<div class="form-group">
											<label for="inputSizeUpName" class="col-sm-3 control-label">Name</label>
											<div class="col-sm-9">
											  <input type="SizeUpName" class="form-control" id="editInputSize" placeholder="Size Up Name">
											  <input type="hidden" name="sizeId" value="" id="edit-size-id">
											</div>
										</div>
										<div class="form-group">
											<label for="inputHeightUp" class="col-sm-3 control-label">Height-Up</label>
											<div class="col-sm-9">
											  <input type="WidthUp" class="form-control" id="editInputWidth" placeholder="Height-Up">
											</div>
										</div>
										<div class="form-group">
											<label for="inputHeightUp" class="col-sm-3 control-label">Width-Up</label>
											<div class="col-sm-9">
											  <input type="HeightUp" class="form-control" id="editInputHeight" placeholder="Width-Up">
											</div>
										</div>	
									</div>
								  </div>
								  <div class="modal-footer">
									<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
									<button type="submit" id="btnEditSize" class="btn btn-primary">Save changes</button>
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
	<script src="resources/js/custom/size.js"></script>
	
 </body>
 
</html>