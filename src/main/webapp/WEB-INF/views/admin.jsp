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
		.loadingBar{position:absolute; left:0; top:0;bottom:0;right:0; background: url(images/ajax-loader.gif) no-repeat center center; z-index:9; display:none;}
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
		<div class="container">
      <!-- Example row of columns -->
      <div class="row">
        <div class="col-md-4">
          <h2>Add Customer Type</h2>
          <p>New customer types can be added/edited/deleted using this module.</p>
          <p> Sample Values are <b>Printer</b>, <b>Premium</b> and <b>Normal</b> </p>
          <p><a class="btn btn-primary" href="addcustomertype" role="button">Add Customer Type &raquo;</a></p>
        </div>
        <div class="col-md-4">
          <h2>Add Variety</h2>
         <p>New Variety types can beadded/edited/deleted using this module.</p>
         <p> Sample Values are <b>Sticker</b> and <b>Paper</b></p>
          <p><a class="btn btn-primary" href="addvariety" role="button">Add Variety &raquo;</a></p>
       </div>
        <div class="col-md-4">
          <h2>Add Variety Type</h2>
          <p>New Variety Types can beadded/edited/deletedusing this module.</p>
          <p> Sample Values are <b>80gsm Maplito</b>, <b>170gsm Art paper</b>, <b>Chrome Sticker</b> etc.</p>
          <p><a class="btn btn-primary" href="addvarietytype" role="button">Add Variety Type &raquo;</a></p>
        </div>
		<div class="col-md-4">
          <h2>Add Size</h2>
          <p>New Sizes can beadded/edited/deleted using this module.</p>
          <p> Sample Values are <b>13x19</b>, <b>14x20</b>, <b>14x26</b> etc.</p>
          <p><a class="btn btn-primary" href="addsize" role="button">Add Size &raquo;</a></p>
        </div>
        <div class="col-md-4">
          <h2>Add Pricing for variety</h2>
          <p>Add new prices for each variety with respect to Customer, Variety Type, Size. </p>
          <p>Pricing can be defined for <b>each item</b>, <b>Above 10</b>, <b>Above 50</b>, <b>Above 100</b>, <b>Above 200</b> and <b>Above 500</b> </p>
          <p><a class="btn btn-primary" href="addpricing" role="button">Add Pricing &raquo;</a></p>
        </div>
		<div class="col-md-4">
          <h2>Add Post Press</h2>
          <p>New Post Press of type <b>Laminations</b>, <b>Binding</b>, <b>Punching</b>, <b>Creasing</b>, <b>Emboss</b> and <b>Foil Stamping</b> can be added or deleted.</p>
          <p> Different types of Pricing are defined like :</p>
          <p><b>Base Price</b> (Eg: &#8377;10 Per sheet/book)</p>
          <p>
          	<b>Quantitative Price</b> (Eg: For Minimum 50 sheets/books price is &#8377;100 and above 50 price will be &#8377;2 per sheet/book )</p>
          <p> 
          	<b>Multi Price</b> (Eg: For books/sheets upto 10(threshold) and For quantity above threshold value price will be &#8377;75 per sheet/book)
          </p>
          <p><b>Price Per Set</b> (Eg: For every set from 1-1000 sheets/books price will be &#8377;200)</p>
          <p><a class="btn btn-primary" href="addextras" role="button">Add Post Press &raquo;</a></p>
        </div>
        <div class="col-md-4">
          <h2>Show Post Press</h2>
          <p>All the different types of Post Press can be deleted from this module</p>
          <p><a class="btn btn-primary" href="showextras" role="button">Show Post Press &raquo;</a></p>
        </div>
      </div>	
	<hr/>
	
	<div class="footer">
       <p>&copy; Print Shop 2014</p>
    </div>
	</div>
	
	 <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="resources/js/jquery.min.js"></script>
	
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
	});
	
	function getVarietyTypes() {
	$('.loadingBar').show();
	var res = $.ajax({
		  url: "customertype",
		  context: document.body,
		  dataType: 'html',
		  type: 'get',
		  data: {
			'username':'hello',
			'password':'test'
		  },
		  beforeSend: function() {
                                $('#selectPaperType'+' .contentarea').html('<img src="/function-demos/functions/ajax/images/loading.gif" />');
          }
		}).done(function() {
		  $( this ).addClass( "done" );
		  $('.loadingBar').hide();
		});
	};
	</script>
	
 </body>
 
</html>
