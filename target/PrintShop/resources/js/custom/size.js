$(document).ready(function(){
$( "#formAddSize" ).submit(function( event ) {
			event.preventDefault();
			var sizeVal = $('#inputSizeUpName').val();
			var widthVal = $('#inputWidthUp').val();
			var heightVal = $('#inputHeightUp').val();
			//alert(inputVal);
			addSize(sizeVal, widthVal, heightVal);
		});
		
		$('.deleteSize').on('click',function(e){
			e.preventDefault();
			var sizeId = $(this).data('id');
			$('#delete-size-id').val(sizeId);
			$('#deleteModal').modal('show');
			
		});
		
		$('.editSize').on('click',function(e){
			e.preventDefault();
			var sizeId = $(this).data('id');
			var sizeName = $(this).data('name');
			var height = $(this).data('height');
			var width = $(this).data('width');
			$('#edit-size-id').val(sizeId);
			$('#editInputSize').val(sizeName);
			$('#editInputWidth').val(width);
			$('#editInputHeight').val(height);
			$('#editModal').modal('show');
			
		});
		
		$( "#formDeleteSize" ).submit(function( event ) {
			event.preventDefault();
			var inputVal = $('#delete-size-id').val();
			//alert(inputVal);
			deleteSize(inputVal);
		});
		
		$( "#formEditSize" ).submit(function( event ) {
			event.preventDefault();
			var sizeId = $('#edit-size-id').val();
			var sizeName = $('#editInputSize').val();
			var sizeHeight = $('#editInputHeight').val();
			var sizeWidth = $('#editInputWidth').val();
			editSize(sizeId, sizeName, sizeHeight, sizeWidth);
		});
	});
	
	function addSize(size, width, height) {
	$('.loadingBar').show();
	$.ajax({
		  url: "http://localhost:8080/PrintShop/rest/print/sizeup/add/" + "?name=" + size + "&height=" + height + "&width=" + width,
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
	
	function deleteSize(sizeId) {
		$('.loadingBar').show();
		$.ajax({
			  url: "http://localhost:8080/PrintShop/rest/print/sizeup/remove/" + sizeId + "/",
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
		
		function editSize(sizeId, name, height, width) {
			$('.loadingBar').show();
			$.ajax({
				  url: "http://localhost:8080/PrintShop/rest/print/sizeup/edit/" + sizeId + "?name=" + name + "&height=" + height + "&width=" + width,
				  type: 'POST',
				  dataType: 'json',
				  contentType: 'application/json',
				  data: {
					  id: sizeId,
					  name: name,
					  height: height,
					  width: width
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
