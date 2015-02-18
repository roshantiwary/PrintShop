$(document).ready(function(){
	$( "#formAddVarietyType" ).submit(function( event ) {
			event.preventDefault();
			var varTypeName = $('#inputVarietyType').val();
			var varId = $('#selectAddVariety option:selected').attr("id");
			var varName = $('#selectAddVariety option:selected').val();
			var sizeUp = [];
			
			$('#selectAddSize option:selected').each(function() {
			   // alert($(this).attr("id"));
			    sizeUp.push({
			    	"id" : $(this).attr("id"),
			    	"name" : $(this).val(),
			    	"width_up" : $(this).data("width_up"),
			    	"height_up" : $(this).data("height_up") 
			    });
			}); 
			
			var varietyType = new Object();
			varietyType.varietyId = varId;
			varietyType.varietyName = varName;
			varietyType.varTypeName = varTypeName;
			varietyType.sizeUp = sizeUp;
			var jsonVarietyType= JSON.stringify(varietyType);
			
			addVariety(jsonVarietyType);
		});

		$('.deleteVarietyType').on('click',function(e){
			e.preventDefault();
			var varId = $(this).data('id');
			$('#delete-varietytype-id').val(varId);
			$('#deleteModal').modal('show');
			
		});
		
		$('.editVarietyType').on('click',function(e){
			e.preventDefault();
			var varId = $(this).data('id');
			var varName = $(this).data('name');
			$('#edit-variety-id').val(varId);
			$('#editInputVariety').val(varName);
			$('#editModal').modal('show');
			
		});
		
		$( "#formDeleteVarietyType" ).submit(function( event ) {
			event.preventDefault();
			var inputVal = $('#delete-varietytype-id').val();
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
	
	function addVariety(sizeUp) {
	$('.loadingBar').show();
	$.ajax({
		  url: "http://localhost:8080/PrintShop/rest/print/varietytype/add/",
		  type: 'POST',
		  dataType: 'json',
		  contentType: 'application/json',
		  data: sizeUp,
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
			  url: "http://localhost:8080/PrintShop/rest/print/varietytype/remove/" + varId + "/",
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
				  url: "http://localhost:8080/PrintShop/rest/print/varietytype/edit/" + varId + "?name=" + varName,
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
