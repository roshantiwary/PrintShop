$(document).ready(function(){
	$('.deleteExtras').on('click',function(e){
		e.preventDefault();
		var extrasId = $(this).data('id');
		$('#delete-extras-id').val(extrasId);
		$('#deleteModal').modal('show');
	});
	
	$( "#formDeleteExtras" ).submit(function( event ) {
		event.preventDefault();
		var inputVal = $('#delete-extras-id').val();
		//alert(inputVal);
		deleteExtras(inputVal);
	});
});


function deleteExtras(extraId) {
	$('.loadingBar').show();
	$.ajax({
		  url: "http://localhost:8080/PrintShop/rest/print/extras/remove/" + extraId + "/",
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
