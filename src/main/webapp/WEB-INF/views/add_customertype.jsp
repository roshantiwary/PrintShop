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
					<h3 class="panel-title">Add Customer Type</h3>
				  </div>
				  <div class="panel-body">
						<table class="table table-hover table-bordered">
							<tr>
								<!-- <th>#</th>  -->
								<th>Customer Type</th> 
								<th>Edit</th> 
								<th>Delete</th>
							</tr>
							<c:forEach items="${customers}" var="element"> 
							<tr>
								<!--  <td>${element.id}</td> -->
								<td>${element.name}</td>
								<td>
									<button class="btn btn-primary editCustomer" data-id="${element.id}" data-name="${element.name}">
									  Edit
									</button>
								</td> 
								<td>
									<button class="btn btn-primary deleteCustomer" data-id="${element.id}">
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
								  Add Customer
								</button>
							</div>
						</div>
						
						
					   
					 <!-- Delete Customer Type Modal -->
							<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							  <div class="modal-dialog">
								<div class="modal-content">
								  <div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
									<h4 class="modal-title" id="myModalLabel">Delete Customer Type</h4>
								  </div>
								  <form role="form" id="formDeleteCustomer">
								  <div class="modal-body">
									<div class="row">
										<p class="text-center">Are you sure you want to delete <mark>Customer Type</mark> ?</p>
										<input type="hidden" name="customerId" value="" id="delete-customer-id">
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
						
						<!-- Add Customer Type Modal -->
							<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							  <div class="modal-dialog">
								<div class="modal-content">
								  <div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
									<h4 class="modal-title" id="myModalLabel">Enter Customer Type</h4>
								  </div>
								  <form role="form" id="formAddCustomer">
								  <div class="modal-body">
									<div class="row">
									<div class="form-group">
										<label for="inputCustType" class="col-sm-3 control-label">CustomerType</label>
										<div class="col-sm-9">
										  <input type="text" name="custType" class="form-control" id="inputCustType" placeholder="Customer Type">
										</div>
									</div>
									</div>
								  </div>
								  <div class="modal-footer">
									<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
									<button type="submit" id="btnAddCustomer" class="btn btn-primary">Save changes</button>
								  </div>
								  </form>
								</div>
							  </div>
							</div>
							
							<!-- Edit Customer Type Modal -->
							<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							  <div class="modal-dialog">
								<div class="modal-content">
								  <div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
									<h4 class="modal-title" id="myModalLabel">Edit Customer Type</h4>
								  </div>
								  <form role="form" id="formEditCustomer">
								  <div class="modal-body">
									<div class="row">
									
									<div class="form-group">
										<label for="inputCustType" class="col-sm-3 control-label">CustomerType</label>
										<div class="col-sm-9">
										  <input type="text" class="form-control" id="editInputCustType" placeholder="Customer Type" value="edit-customer-name">
										  <input type="hidden" name="customerId" value="" id="edit-customer-id">
										</div>
									</div>
									</div>
								  </div>
								  <div class="modal-footer">
									<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
									<button type="submit" id="btnEditCustomer" class="btn btn-primary">Save changes</button>
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
	<script>
	$(document).ready(function(){
		$('#inlineRadio1').on('click',function(){
			if($(this).is(':checked')){
				//$('#selectPaperType').removeAttr('disabled');
				//$('#selectStickerType').attr('disabled','disabled');
				getVarietyTypes();
				$('#groupPaperType').show();
				$('#groupStickerType').hide();
			}
		});
		
		$('#inlineRadio2').on('click',function(){
			if($(this).is(':checked')){
				//$('#selectPaperType').attr('disabled','disabled');
				//$('#selectStickerType').removeAttr('disabled');
				getVarietyTypes();
				$('#groupPaperType').hide();
				$('#groupStickerType').show();
			}
		});
		
		$( "#formAddCustomer" ).submit(function( event ) {
			event.preventDefault();
			var inputVal = $('#inputCustType').val();
			//alert(inputVal);
			addCustomerType(inputVal);
		});
		
		$('.deleteCustomer').on('click',function(e){
			e.preventDefault();
			var custId = $(this).data('id');
			//alert(custId);
			$('#delete-customer-id').val(custId);
			$('#deleteModal').modal('show');
			
		});
		
		$('.editCustomer').on('click',function(e){
			e.preventDefault();
			var custId = $(this).data('id');
			var custName = $(this).data('name');
			//alert(custId);
			$('#edit-customer-id').val(custId);
			$('#editInputCustType').val(custName);
			$('#editModal').modal('show');
			
		});
		
		$( "#formDeleteCustomer" ).submit(function( event ) {
			event.preventDefault();
			var inputVal = $('#delete-customer-id').val();
			//alert(inputVal);
			deleteCustomerType(inputVal);
		});
		
		$( "#formEditCustomer" ).submit(function( event ) {
			event.preventDefault();
			var customerId = $('#edit-customer-id').val();
			var customerName = $('#editInputCustType').val();
			//alert(customerId);
			//alert(customerName);
			editCustomerType(customerId, customerName);
		});
	});
	
	function addCustomerType(customer) {
	$('.loadingBar').show();
	$.ajax({
		  url: "http://localhost:8080/PrintShop/rest/print/customertype/add/" + customer + "/",
		  type: 'POST',
		  success:function(response){
			  console.log(response);
			  $('.loadingBar').hide();
			  window.location.reload(true);
		  },
		  error:function(xhr,status){
			  console.log('Some error occured');
			  $('.loadingBar').hide();
		  }
		});
	};
	
	function deleteCustomerType(customerId) {
		$('.loadingBar').show();
		$.ajax({
			  url: "http://localhost:8080/PrintShop/rest/print/customertype/remove/" + customerId + "/",
			  dataType: 'json',
			  type: 'POST',
			  success:function(response){
				  console.log(response);
				  $('.loadingBar').hide();
				  window.location.reload(true);
			  },
			  error:function(xhr,status){
				  console.log('Some error occured');
				  $('.loadingBar').hide();
			  }
			});
		};
		
		function editCustomerType(customerId, customerName) {
			$('.loadingBar').show();
			$.ajax({
				  url: "http://localhost:8080/PrintShop/rest/print/customertype/edit/" + customerId + "?name=" + customerName,
				  type: 'POST',
				  dataType: 'json',
				  contentType: 'application/json',
				  data: {
					  id: customerId,
					  name: customerName
				  },
				  success:function(response){
					  console.log(response);
					  $('.loadingBar').hide();
					  window.location.reload(true);
				  },
				  error:function(xhr,status){
					  console.log('Some error occured');
					  $('.loadingBar').hide();
				  }
				});
			};
	</script>
	
 </body>
 
</html>