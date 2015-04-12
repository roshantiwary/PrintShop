$(document).ready(function(){
		
		$('.changeUserStatus').on('click',function(e){
			e.preventDefault();
			var username = $(this).data('username');
			var status = $(this).data('status');
			
			$('#eleUsernameId').val(username);
			$('#eleUserStatus').val(status);			
			
			$('#editStatus').modal('show');
			
		});
		
		$('.changeUserRole').on('click',function(e){
			e.preventDefault();
			var username = $(this).data('username');
			
			var userdetails = getUserRoles(username);
			console("userdetails");
			console(userdetails);
			console(userdetails.roles);
		    
				
			$('#eleUsernameId').val(username);
//			$('#eleUserRole').val(role);			
			
			
			
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
		
		$( "#formChangeRole" ).submit(function( event ) {
			event.preventDefault();
			var user = $('#eleUsernameId').val();
			var roleAdminId = document.getElementById("roleadmin").checked;
			var roleSuperAdminId = document.getElementById("rolesuperadmin").checked;
			
			var roleAdmin = new Object();
			roleAdmin.roleType = "ROLE_ADMIN";
			roleAdmin.roleValue = roleAdminId;
			
			var roleSuperAdmin = new Object();
			roleSuperAdmin.roleType = "ROLE_ADMINISTRATOR";
			roleSuperAdmin.roleValue = roleSuperAdminId;
			
			var roles = [roleAdmin, roleSuperAdmin];

			var userObj = new Object();
			userObj.username = user;
			userObj.roleVOList = roles;
			
			var jsonUser= JSON.stringify(userObj);
			
			updateUserRole(jsonUser);
		});
		
		
	});
		
function updateUserStatus(jsonUser) {
	$('.loadingBar').show();
	$.ajax({
		  url: "../rest/print/admin/update/useraccess",
		  type: 'PUT',
		  dataType: 'json',
		  contentType: 'application/json',
		  data: jsonUser,
		  success:function(response){
			  console.log(response);
			  console.log(response.username);
//			  $('#editStatus').modal('hide');			  
//			  var userRowId = '#' + response.username + '_changeUserStatus';
//			  var userStatusMsg = '#' + response.username + '_userStatusMsg';
//			  $(userRowId).val(response.statusMsg);
//			  
//			  if(response.enabled) {
//				  $(userRowId).removeClass('btn-danger').addClass('btn-success');  
//				  $(userStatusMsg).text('Enabled');
//			  } else {
//				  $(userRowId).removeClass('btn-success').addClass('btn-danger'); 
//				  $(userStatusMsg).text('Disabled');
//			  }
			  $('.loadingBar').hide();
			  window.location.reload(true);
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
	
	function updateUserRole(jsonUser) {
		$('.loadingBar').show();
		$.ajax({
			  url: "../rest/print/admin/update/userrole",
			  type: 'PUT',
			  dataType: 'json',
			  contentType: 'application/json',
			  data: jsonUser,
			  success:function(response){
				  console.log(response);
				  console.log(response.username);
				  $('#editStatus').modal('hide');			  
//				  var userRowId = '#' + response.username + '_changeUserRole';
//				  var userRoleMsg = '#' + response.username + '_changeUserRole';
//				  $(userRowId).val(response.statusMsg);
//				  
//				  if(response.enabled) {
//					  $(userRowId).removeClass('btn-danger').addClass('btn-success');  
//					  $(userRoleMsg).text('Enabled');
//				  } else {
//					  $(userRowId).removeClass('btn-success').addClass('btn-danger'); 
//					  $(userRoleMsg).text('Disabled');
//				  }
				  
				  $('.loadingBar').hide();
				  window.location.reload(true);
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
	
	function getUserRoles(username) {
		$('.loadingBar').show();
		$.ajax({
			  url: "../rest/print/admin/get/user/roles/" + username,
			  type: 'GET',
			  dataType: 'json',
			  contentType: 'application/json',
			  success:function(response){
				  console.log(response);
				  var roles = response.roles;
				  $("#eleUsernameId").val(username);
				  $("#roleadmin").prop("checked", false);
				  $("#rolesuperadmin").prop("checked", false);
				  for(var i = 0; i < roles.length; ++i){
					   //do something with obj[i]
				        console.log(roles[i]);
				        var role = roles[i];
				        if(role.id === 'ROLE_ADMIN') {
				        	$("#roleadmin").prop("checked", true);
				        }
				        
				        if(role.id === 'ROLE_ADMINISTRATOR') {
				        	$("#rolesuperadmin").prop("checked", true);
				        }
					}
				  
				  $('#editRole').modal('show');
				  
				  $('.loadingBar').hide();
			  },
			  error:function(xhr,status,err){
				  console.log('Some error occured');
				  console.log(xhr);
				  console.log(status);
				  console.log("error: " + err);
				  $('.loadingBar').hide();
			  }
			});
	};