$(document).ready(function(){
		
		$('.changeUserStatus').on('click',function(e){
			e.preventDefault();
			var username = $(this).data('username');
			var status = $(this).data('status');
			
			$('#eleUsernameId').val(username);
			$('#eleUserStatus').val(status);			
			
			$('#editStatus').modal('show');
			
		});
		
		$( "#formChangeStatus" ).submit(function( event ) {
			event.preventDefault();
			var user = $('#eleUsernameId').val();
			var status = $('#eleUserStatus').val();
			
			var userObj = new Object();
			userObj.username = user;
			userObj.enabled= status;
			
			var jsonUser= JSON.stringify(userObj);
			
			updateUserStatus(jsonUser);
		});
	});
		
function updateUserStatus(jsonUser) {
	$('.loadingBar').show();
	$.ajax({
		  url: "rest/print/admin/update/useraccess",
		  type: 'PUT',
		  dataType: 'json',
		  contentType: 'application/json',
		  data: jsonUser,
		  success:function(response){
			  console.log(response);
			  console.log(response.username);
			  $('#editStatus').modal('hide');			  
			  var userRowId = '#' + response.username + '_changeUserStatus';
			  var userStatusMsg = '#' + response.username + '_userStatusMsg';
			  $(userRowId).val(response.statusMsg);
			  
			  if(response.enabled) {
				  $(userRowId).removeClass('btn-danger').addClass('btn-success');  
				  $(userStatusMsg).text('Enabled');
			  } else {
				  $(userRowId).removeClass('btn-success').addClass('btn-danger'); 
				  $(userStatusMsg).text('Disabled');
			  }
			  $('.loadingBar').hide();
		  },
		  error:function(xhr,status,err){
			  console.log('Some error occured');
			  console.log(xhr);
			  console.log(status);
			  console.log("error: " + err);
			  $('.loadingBar').hide();
			  $('#editStatus').modal('hide');
		  }
		});
	};
