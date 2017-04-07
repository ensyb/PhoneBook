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
		submitHandler : loginHandler
		})
		
	function loginHandler(){
		var data = $("#login-form").serialize();
	     $.ajax({
	            type : 'POST',
	            url  : 'login.html',
	            data : data,
	            beforeSend: function(){
	                $("#errorLogin").fadeOut();
	                $("#login-Button").html('<span class="glyphicon glyphicon-transfer"></span> &nbsp; sending ...');
	            },
	            success :  function(data){
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
	            },
	            
	            error: function(xhr, textStatus, error){
	            	  $("#errorLogin").fadeIn(1000, function(){
	            		  	$("#registerDugme").html('Submit');
	                        $("#errorLogin").html('<div class="alert alert-danger"><span class="glyphicon glyphicon-info-sign"></span> registration failed !</div>');
	                    });
	            }
	        });
	     return false;
	}
});