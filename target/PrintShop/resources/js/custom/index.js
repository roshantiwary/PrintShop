$(document).ready(function(){
	$("input[type='radio']").change(function () {
		var dynamicId=$(this).val();		
		getVarietyType(dynamicId);
	});
	
	$("select#selectVarType").change(function() {
		$('#selectVarType option:selected').each(function() {
			var varietyTypeId = $(this).val();
			getSizeUp(varietyTypeId);
		});
	});
	
	$("select#selectCustType").change(function() {
		
		$('#selectCustType option').each(function() {
			var customerValue = $(this).val();
			console.log(customerValue);
			var closeDivId = '#' + customerValue;
			$(closeDivId).hide();
			//getSizeUp(varietyTypeId);
		});
		
		$('#selectCustType option:selected').each(function() {
			var customerValue = $(this).val();
			console.log(customerValue);
			var divId = '#' + customerValue;
			$(divId).show();
			//getSizeUp(varietyTypeId);
		});
		
		// Uncheck all the checkboxes
		$('input:checked').each(function() {
			var checkId = '#' + $(this).attr('id');
			$(checkId).attr('checked', false);
		});
		
	});
	
	$("#calculatePrice").click(function(e){
	    e.preventDefault();	    
	    
	    var makeCall = true;
	    var varietyType = $("#selectVarType option:selected").attr("id");
	    var varietyRadio = $("input[type='radio']:checked").val();
	    var custType = $('#selectCustType option:selected').attr("id");
	    var varType = $('#selectVarType option:selected').attr("id");
	    var sizeUp = $('#selectSizeUp option:selected').attr("id");
	    var quantitys = $('#inputQty').val();
	    
	    var numberOfUp = getUps();
	    
	    if(varietyType == 'undefined' || varietyType == null || varietyType == '') {
	    	$('#formVarietyType').addClass("has-error");
	    	makeCall = false;
	    } else {
	    	$('#formVarietyType').removeClass("has-error");
	    }
	    
	    if(varietyRadio == 'undefined' || varietyRadio == null || varietyRadio == '') {
	    	$('#divVariety').addClass("has-error");
	    	makeCall = false;
	    } else {
	    	$('#divVariety').removeClass("has-error");
	    }
	    
	    if(custType == 'undefined') {
	    	$('#divCustType').addClass("has-error");
	    	makeCall = false;
	    } else {
	    	$('#divCustType').removeClass("has-error");
	    }
	    
	    if(varType == 'undefined') {
	    	$('#divCustType').addClass("has-error");
	    	makeCall = false;
	    } else {
	    	$('#divCustType').removeClass("has-error");
	    }
	    
	    if(sizeUp == 'undefined' || sizeUp == null || sizeUp == '') {
	    	$( "#formSizeUp" ).addClass( "has-error" );	 
	    	makeCall = false;
	    } else {
	    	$( "#formSizeUp" ).removeClass( "has-error" );
	    }
	    
	    if(quantitys == null || quantitys == '') {
	    	$( "#divQty" ).addClass( "has-error" );
	    	makeCall = false;
	    } else {
	    	$( "#divQty" ).removeClass( "has-error" );
	    }
	    
	    var calcPrice = new Object();
	    calcPrice.custTypeId = $('#selectCustType option:selected').attr("id");
	    calcPrice.varTypeId = $('#selectVarType option:selected').attr("id");
	    calcPrice.sizeUpId = $('#selectSizeUp option:selected').attr("id");
	    calcPrice.qty = $('#inputQty').val();
	    calcPrice.noOfUp = numberOfUp;
	    
		
		
		// Calculation extras
		var selectedExtras = new Array();
		$('input[type="checkbox"]:checked').each(function() {
			var inputId = '#input' + $(this).attr('id');
			console.log(inputId);
			var inputVal = $(inputId).val();
			console.log("inputvalue");
			console.log(inputVal);
			var liTag = "#li" + $(this).attr('id');
			var extraObject = new Object();
			if(inputVal == 'undefined' || inputVal == null || inputVal == '') {
				$(liTag).addClass("has-error");
				makCall = false;
			} else {
				$(liTag).removeClass("has-error");
				var extId = $(this).attr('id');
				extraObject.id = extId;
				extraObject.qty= inputVal;
				selectedExtras.push(extraObject);
			}
		});
		console.log(selectedExtras);
		
		calcPrice.selectedExtraArray = selectedExtras;
		var calcPriceJson= JSON.stringify(calcPrice);
		
		console.log(calcPriceJson);
		if(makeCall == true) {
			calculatePricing(calcPriceJson);	
		}
		
	 });
});

function getVarietyType(varId) {
	$('.loadingBar').show();
	$.ajax({
		  url: "http://localhost:8080/PrintShop/rest/print/varietytype/get/" + varId,
		  type: 'GET',
		  dataType: 'json',
		  contentType: 'application/json',
		  success:function(response){
			  console.log(response);
			  $('.loadingBar').hide();
			  $('#selectVarType').find('option').remove();
			  $('#selectVarType').append($('<option>').text('Select Variety Type'));
			  $.each(response, function(i, value) {
		            $('#selectVarType').append($('<option>').text(value.name).attr('value', value.id).attr('id', value.id));
		      });
			  $('#formVarietyType').show();
		  },
		  error:function(xhr,status){
			  console.log('Some error occured');
			  $('.loadingBar').hide();
		  }
		});
};

function getSizeUp(varTypeId) {
	$('.loadingBar').show();
	$.ajax({
		  url: "http://localhost:8080/PrintShop/rest/print/varietytype/sizeup/" + varTypeId,
		  type: 'GET',
		  dataType: 'json',
		  contentType: 'application/json',
		  success:function(response){
			  console.log(response);
			  $('.loadingBar').hide();
			  $('#selectSizeUp').find('option').remove();
			  $('#selectSizeUp').append($('<option>').text('Select Size'));
			  $.each(response, function(i, value) {
		            $('#selectSizeUp').append($('<option>').text(value.name).attr('value', value.id).attr('id', value.id).attr('data-height_up',value.height_up).attr('data-width_up',value.width_up));
		      });
			  $('#formSizeUp').show();
		  },
		  error:function(xhr,status){
			  console.log('Some error occured');
			  $('.loadingBar').hide();
		  }
		});
};	
	
function getNumberOfUps(numberOfUpJson) {
	var result = null;
	$('.loadingBar').show();
	$.ajax({
		  url: "http://localhost:8080/PrintShop/rest/print/numberofups/",
		  type: 'POST',
		  dataType: 'json',
		  contentType: 'application/json',
		  data: numberOfUpJson,
		  success:function(response){
			  console.log(response);
			  $('.loadingBar').hide();
			  $(response);
			  result = response;
			  $('#inputNumberUps').val(result);
		  },
		  error:function(xhr,status){
			  console.log('Some error occured');
			  $('.loadingBar').hide();
		  }
		});
	return(result);
};

function calculatePricing(calcPriceJson) {
	$('.loadingBar').show();
	$.ajax({
		  url: "http://localhost:8080/PrintShop/rest/print/pricing/calculateprice/",
		  type: 'POST',
		  dataType: 'json',
		  contentType: 'application/json',
		  data: calcPriceJson,
		  success:function(response){
			  console.log(response);
			  $('.loadingBar').hide();
			  $(response);
			  var result = response;
			  $('#printAmt').html('&#8377;' + result.printingPrice);
			  $('#extrasAmt').html('&#8377;' + result.extraPrice);
			  $('#subtotalAmt').html('&#8377;' + result.totalPrice);
			  $('#inputSheet').val(result.noOfSheets);
		  },
		  error:function(xhr,status){
			  console.log('Some error occured');
			  $('.loadingBar').hide();
		  }
		});
};

function getUps(){
    var makeCall = true;
    var varietyRadio = $("input[type='radio']:checked").val();
    var varietyType = $("#selectVarType option:selected").attr("id");
    var sizeUp = $('#selectSizeUp option:selected').attr("id");
    var height = $('#inputHeight').val();
    var width = $('#inputWidth').val();
    var heightup = $('#selectSizeUp option:selected').data("height_up");
    var widthup = $('#selectSizeUp option:selected').data("width_up");
    
    if(varietyType == 'undefined' || varietyType == null || varietyType == '') {
    	$('#formVarietyType').addClass("has-error");
    	makeCall = false;
    } else {
    	$('#formVarietyType').removeClass("has-error");
    }
    
    if(varietyRadio == 'undefined' || varietyRadio == null || varietyRadio == '') {	    	
    	$('#divVariety').addClass("has-error");
    	makeCall = false;
    } else  {
    	$('#divVariety').removeClass("has-error");
    }
    
    if(sizeUp == 'undefined' || sizeUp == null || sizeUp == '') {
    	$( "#formSizeUp" ).addClass( "has-error" );	 
    	makeCall = false;
    } else {
    	$( "#formSizeUp" ).removeClass( "has-error" );
    }
    
    if(height == null || height == '') {
    	$( "#divHeightWidth" ).addClass( "has-error" );
    	makeCall = false;
    } else {
    	$( "#divHeightWidth" ).removeClass( "has-error" );
    }
    
    if(width == null || width == '') {
    	$( "#divHeightWidth" ).addClass( "has-error" );
    	makeCall = false;
    } else {
    	$( "#divHeightWidth" ).removeClass( "has-error" );
    }
    
    
    var noOfUP = new Object();
    noOfUP.sizeId = $('#selectSizeUp option:selected').attr("id");
    noOfUP.height = $('#inputHeight').val();
    noOfUP.width = $('#inputWidth').val();
    noOfUP.height_up = $('#selectSizeUp option:selected').data("height_up");
    noOfUP.width_up = $('#selectSizeUp option:selected').data("width_up");
	
	//var noOfUps = 0;
	//if(makeCall == true) {
	//	noOfUps = getNumberOfUps(numberOfUpJson);	
	//}
	
	//alert("getUps" + noOfUps);
	return noOfUP;
 }
