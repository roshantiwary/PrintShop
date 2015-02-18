$(document).ready(function(){
		
		$('.editPricing').on('click',function(e){
			e.preventDefault();
			var varId = $(this).data('id');
			var varEach = $(this).data('each');
			var varabove10 = $(this).data('above10');
			var varabove50 = $(this).data('above50');
			var varabove100 = $(this).data('above100');
			var varabove200 = $(this).data('above200');
			var varabove500 = $(this).data('above500');
			$('#edit-pricing-id').val(varId);
			$('#editInputEachPrice').val(varEach);
			$('#editInputPriceAbove10').val(varabove10);
			$('#editInputPriceAbove50').val(varabove50);
			$('#editInputPriceAbove100').val(varabove100);
			$('#editInputPriceAbove200').val(varabove200);
			$('#editInputPriceAbove500').val(varabove500);
			
			$('#editModal').modal('show');
			
		});
		
		$( "#formEditPricing" ).submit(function( event ) {
			event.preventDefault();
			var varId = $('#edit-pricing-id').val();
			var varEach = $('#editInputEachPrice').val();
			var varabove10 = $('#editInputPriceAbove10').val();
			var varabove50 = $('#editInputPriceAbove50').val();
			var varabove100 = $('#editInputPriceAbove100').val();
			var varabove200 = $('#editInputPriceAbove200').val();
			var varabove500 = $('#editInputPriceAbove500').val();
			
			var pricing = new Object();
			pricing.pricingId = varId;
			pricing.eachPrice= varEach;
			pricing.priceAbove10 = varabove10;
			pricing.priceAbove50 = varabove50;
			pricing.priceAbove100 = varabove100;
			pricing.priceAbove200 = varabove200;
			pricing.priceAbove500 = varabove500;
			
			var jsonpricing= JSON.stringify(pricing);
			
			updatePricing(jsonpricing);
		});
	});
		
function updatePricing(pricing) {
	$('.loadingBar').show();
	$.ajax({
		  url: "http://localhost:8080/PrintShop/rest/print/pricing/update/",
		  type: 'POST',
		  dataType: 'json',
		  contentType: 'application/json',
		  data: pricing,
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
