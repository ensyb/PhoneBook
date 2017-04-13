$('document').ready(function() {
	$("#login-form").validate({
		rules : {
			email : "required",
			lozinka : "required"
		},
		messages : {
			email : "morate unijeti email adresu",
			lozinka :  "morate unijeti lozinku",
		},
		submitHandler : LoginModule.login
		})
		

});

var LoginModule = (function(){
	var _beforeSend = function(){
	    $("#errorLogin").fadeOut();
	    $("#login-Button").html('<span class="glyphicon glyphicon-transfer"></span> &nbsp; sending ...');
	};

	var _succesFunction =   function(data){
	    if(data.trim() == "success"){
	 	   console.log("called !!");
	         $("#login-form").fadeOut(600, function(){ 
	         	$("#login-main-message").html(
	         			'<div class="alert alert-success"> <h3>Redirecting to homepage...</h3> </div>');
	         }); 
	         setTimeout(function(){
	         	$(".loginModal").modal('toggle');
	         	window.location.href = "/home.html";
	         }, 1000);

	     }
	     else {
	         $("#errorLogin").fadeIn(1000, function(){
	         	$("#login-Button").html('Submit');
	             $("#errorLogin").html('<div class="alert alert-danger"><span class="glyphicon glyphicon-info-sign"></span> '+data+' !</div>');
	         });
	     }
	 }

	var _errorFunction = function(xhr, textStatus, error){
		  $("#errorLogin").fadeIn(1000, function(){
			  	$("#login-Button").html('Submit');
	          $("#errorLogin").html('<div class="alert alert-danger"><span class="glyphicon glyphicon-info-sign"></span> registration failed !</div>');
	      });
	}

	
	return {
		login : function(){
			var data = $("#login-form").serialize();
		     $.ajax({
		            type : 'POST',
		            url  : 'login.html',
		            data : data,
		            beforeSend: _beforeSend,
		            success : _succesFunction,
		            error: _errorFunction
		        });
		     return false;
		}
	}
})();
