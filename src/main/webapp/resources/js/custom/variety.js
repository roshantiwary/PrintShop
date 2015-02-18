$(document).ready(function(){
$( "#formAddVariety" ).submit(function( event ) {
			event.preventDefault();
			var inputVal = $('#inputVariety').val();
			//alert(inputVal);
			addVariety(inputVal);
		});
		
		$('.deleteVariety').on('click',function(e){
			e.preventDefault();
			var varId = $(this).data('id');
			$('#delete-variety-id').val(varId);
			$('#deleteModal').modal('show');
			
		});
		
		$('.editVariety').on('click',function(e){
			e.preventDefault();
			var varId = $(this).data('id');
			var varName = $(this).data('name');
			$('#edit-variety-id').val(varId);
			$('#editInputVariety').val(varName);
			$('#editModal').modal('show');
			
		});
		
		$( "#formDeleteVariety" ).submit(function( event ) {
			event.preventDefault();
			var inputVal = $('#delete-variety-id').val();
			//alert(inputVal);
			deleteVariety(inputVal);
		});
		
		$( "#formEditVariety" ).submit(function( event ) {
			event.preventDefault();
			var varId = $('#edit-variety-id').val();
			var varName = $('#editInputVariety').val();
			editVariety(varId, varName);
		});
	});
	
	function addVariety(variety) {
	$('.loadingBar').show();
	$.ajax({
		  url: "http://localhost:8080/PrintShop/rest/print/variety/add/" + variety + "/",
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
	
	function deleteVariety(varId) {
		$('.loadingBar').show();
		$.ajax({
			  url: "http://localhost:8080/PrintShop/rest/print/variety/remove/" + varId + "/",
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
		
		function editVariety(varId, varName) {
			$('.loadingBar').show();
			$.ajax({
				  url: "http://localhost:8080/PrintShop/rest/print/variety/edit/" + varId + "?name=" + varName,
				  type: 'POST',
				  dataType: 'json',
				  contentType: 'application/json',
				  data: {
					  id: varId,
					  name: varName
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
