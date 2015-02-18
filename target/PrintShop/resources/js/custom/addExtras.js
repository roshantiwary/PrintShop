$(document).ready(function(){
	$("input[type='radio']").change(function () {
		var priceTypeId=$(this).attr("id");	
		showPriceSection(priceTypeId);
	});
	
	$("#addExtra").click(function(e){
	    e.preventDefault();
	    var priceRadio = $("input[type='radio']:checked").attr("id");
	    addExtras(priceRadio);
	});    
});

function addExtras(priceTypeId) {
	
	if(priceTypeId == 'undefined' || priceTypeId == null || priceTypeId == '') {
		$('#divPriceType').addClass("has-error");
	} else {
		$( "#divPriceType" ).removeClass( "has-error" );
	}
	
	if(priceTypeId == 'radioBasePriceId') {
		var basePriceJson = new Object();
		var makeCall = true;
		
		basePriceJson.customerType = $('#selectCustType option:selected').val();
		basePriceJson.extraParentType = $('#selectParentExtraType option:selected').val();
		basePriceJson.extraItemType = $('#selectExtraItemType option:selected').val();
		basePriceJson.extraItem = $('#inputExtraItem').val();
		
		basePriceJson.basePrice = $('#inputBasePrice').val();
		
		var inputExtItem = $('#inputExtraItem').val();
		if(inputExtItem == 'undefined' || inputExtItem == null || inputExtItem == '') {
			makeCall = false;
			$('#divExtraItem').addClass("has-error");
		} else {
			$( "#divExtraItem" ).removeClass( "has-error" );
		}
		
		var inputBasePrice = $('#inputBasePrice').val();
		if(inputBasePrice == 'undefined' || inputBasePrice == null || inputBasePrice == '') {
			makeCall = false;
			$('#formBasePrice').addClass("has-error");
		} else {
			$( "#formBasePrice" ).removeClass( "has-error" );
		}
		
		var jsonData = JSON.stringify(basePriceJson);
		
		if(makeCall == true) {
		$('.loadingBar').show();
		$.ajax({
			  url: "http://localhost:8080/PrintShop/rest/print/extras/baseprice/",
			  type: 'POST',
			  dataType: 'json',
			  contentType: 'application/json',
			  data: jsonData,
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
		}
	}
	
	if(priceTypeId == 'radioQuantitativePriceId') {
		var makeCall = true;
		var quantitativePriceJson = new Object();
		
		quantitativePriceJson.customerType = $('#selectCustType option:selected').val();
		quantitativePriceJson.extraParentType = $('#selectParentExtraType option:selected').val();
		quantitativePriceJson.extraItemType = $('#selectExtraItemType option:selected').val();
		quantitativePriceJson.extraItem = $('#inputExtraItem').val();
		
		quantitativePriceJson.minQty = $('#inputMinimumQty').val();
		quantitativePriceJson.pricePerItem = $('#inputPricePerItem').val();
		quantitativePriceJson.minPrice = $('#inputMinimumPrice').val();
		
		var inputExtItem = $('#inputExtraItem').val();
		if(inputExtItem == 'undefined' || inputExtItem == null || inputExtItem == '') {
			makeCall = false;
			$('#divExtraItem').addClass("has-error");
		} else {
			$( "#divExtraItem" ).removeClass( "has-error" );
		}
		
		var inputMinimumQty = $('#inputMinimumQty').val();
		if(inputMinimumQty == 'undefined' || inputMinimumQty == null || inputMinimumQty == '') {
			makeCall = false;
			$('#divMinimumQty').addClass("has-error");
		} else {
			$( "#divMinimumQty" ).removeClass( "has-error" );
		}
		
		var inputPricePerItem = $('#inputPricePerItem').val();
		if(inputPricePerItem == 'undefined' || inputPricePerItem == null || inputPricePerItem == '') {
			makeCall = false;
			$('#divPricePerItem').addClass("has-error");
		} else {
			$( "#divPricePerItem" ).removeClass( "has-error" );
		}
		
		var inputMinimumPrice = $('#inputMinimumPrice').val();
		if(inputMinimumPrice == 'undefined' || inputMinimumPrice == null || inputMinimumPrice == '') {
			makeCall = false;
			$('#divMinimumPrice').addClass("has-error");
		} else {
			$( "#divMinimumPrice" ).removeClass( "has-error" );
		}
	
		var jsonData = JSON.stringify(quantitativePriceJson);
		
		if(makeCall == true) {
		$('.loadingBar').show();
		$.ajax({
			  url: "http://localhost:8080/PrintShop/rest/print/extras/qtyprice/",
			  type: 'POST',
			  dataType: 'json',
			  contentType: 'application/json',
			  data: jsonData,
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
	}
	}
	
	if(priceTypeId == 'radioSetPriceId') {
		var makeCall = true;
		var PriceSetJson = new Object();
		PriceSetJson.customerType = $('#selectCustType option:selected').val();
		PriceSetJson.extraParentType = $('#selectParentExtraType option:selected').val();
		PriceSetJson.extraItemType = $('#selectExtraItemType option:selected').val();
		PriceSetJson.extraItem = $('#inputExtraItem').val();
		
		PriceSetJson.itemPerSet = $('#inputItemPerSet').val();
		PriceSetJson.pricePerSet = $('#inputPricePerSet').val();
		
		var inputExtItem = $('#inputExtraItem').val();
		if(inputExtItem == 'undefined' || inputExtItem == null || inputExtItem == '') {
			makeCall = false;
			$('#divExtraItem').addClass("has-error");
		} else {
			$( "#divExtraItem" ).removeClass( "has-error" );
		}
		
		var inputItemPerSet = $('#inputItemPerSet').val();
		if(inputItemPerSet == 'undefined' || inputItemPerSet == null || inputItemPerSet == '') {
			makeCall = false;
			$('#divItemPerSet').addClass("has-error");
		} else {
			$( "#divItemPerSet" ).removeClass( "has-error" );
		}
		
		var inputPricePerSet = $('#inputPricePerSet').val();
		if(inputPricePerSet == 'undefined' || inputPricePerSet == null || inputPricePerSet == '') {
			makeCall = false;
			$('#divPricePerSet').addClass("has-error");
		} else {
			$( "#divPricePerSet" ).removeClass( "has-error" );
		}
		
		var jsonData = JSON.stringify(PriceSetJson);
		
		if(makeCall == true) {
		$('.loadingBar').show();
		$.ajax({
			  url: "http://localhost:8080/PrintShop/rest/print/extras/persetprice/",
			  type: 'POST',
			  dataType: 'json',
			  contentType: 'application/json',
			  data: jsonData,
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
		}
	}
	
	if(priceTypeId == 'radioMultiPriceId') {
		var makeCall = true;
		var MultiPriceJson = new Object();
		
		MultiPriceJson.customerType = $('#selectCustType option:selected').val();
		MultiPriceJson.extraParentType = $('#selectParentExtraType option:selected').val();
		MultiPriceJson.extraItemType = $('#selectExtraItemType option:selected').val();
		MultiPriceJson.extraItem = $('#inputExtraItem').val();
		
		MultiPriceJson.thresholdQty = $('#inputThresholdQty').val();
		MultiPriceJson.lowPrice = $('#inputLowThresholdPrice').val();
		MultiPriceJson.highPrice = $('#inputHighThresholdPrice').val();
		
		var inputExtItem = $('#inputExtraItem').val();
		if(inputExtItem == 'undefined' || inputExtItem == null || inputExtItem == '') {
			makeCall = false;
			$('#divExtraItem').addClass("has-error");
		} else {
			$( "#divExtraItem" ).removeClass( "has-error" );
		}
		
		var inputThresholdQty = $('#inputThresholdQty').val();
		if(inputThresholdQty == 'undefined' || inputThresholdQty == null || inputThresholdQty == '') {
			makeCall = false;
			$('#divThresholdQty').addClass("has-error");
		} else {
			$( "#divThresholdQty" ).removeClass( "has-error" );
		}
		
		var inputLowThresholdPrice = $('#inputLowThresholdPrice').val();
		if(inputLowThresholdPrice == 'undefined' || inputLowThresholdPrice == null || inputLowThresholdPrice == '') {
			makeCall = false;
			$('#divLowThresholdPrice').addClass("has-error");
		} else {
			$( "#divLowThresholdPrice" ).removeClass( "has-error" );
		}
		
		var inputHighThresholdPrice = $('#inputHighThresholdPrice').val();
		if(inputHighThresholdPrice == 'undefined' || inputHighThresholdPrice == null || inputHighThresholdPrice == '') {
			makeCall = false;
			$('#divHighThresholdPrice').addClass("has-error");
		} else {
			$( "#divHighThresholdPrice" ).removeClass( "has-error" );
		}
		
		var jsonData = JSON.stringify(MultiPriceJson);
		
		if(makeCall == true) {
		$('.loadingBar').show();
		$.ajax({
			  url: "http://localhost:8080/PrintShop/rest/print/extras/multiprice/",
			  type: 'POST',
			  dataType: 'json',
			  contentType: 'application/json',
			  data: jsonData,
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
		}
	}

}

function showPriceSection(priceTypeId) {
	
	if(priceTypeId == 'radioBasePriceId') {
		//show formBasePrice
		$('#formBasePrice').show();
		$('#formQuantitativePrice').hide();
		$('#formPricePerSet').hide();
		$('#formMultiPrice').hide();
	}
	
	if(priceTypeId == 'radioQuantitativePriceId') {
		//show formQuantitativePrice
		$('#formBasePrice').hide();
		$('#formQuantitativePrice').show();
		$('#formPricePerSet').hide();
		$('#formMultiPrice').hide();
	}
	
	if(priceTypeId == 'radioSetPriceId') {
		//show formPricePerSet
		$('#formBasePrice').hide();
		$('#formQuantitativePrice').hide();
		$('#formPricePerSet').show();
		$('#formMultiPrice').hide();
	}
	
	if(priceTypeId == 'radioMultiPriceId') {
		//show formMultiPrice
		$('#formBasePrice').hide();
		$('#formQuantitativePrice').hide();
		$('#formPricePerSet').hide();
		$('#formMultiPrice').show();
	}
}