$('document').ready(function() {
	$("#register-form").validate({
		rules : {
			email : {
				required : true,
				email : true
			},
			lozinka : {
				required : true,
				minlength : 5
			}
		},
		messages : {
			email : {
				required : "morate unijeti email adresu",
				email : "morate unijeti validnu email adresu"
			},
			lozinka : {
				required : "morate unijeti lozinku",
				minlength : "lozinka mora imati minilano 5 karaktera"
			}
		},
		submitHandler : RegisterModule.register
		
		})
});

var RegisterModule = (function(){
	
	var _beforeFormSend = function(){
	    $("#error").fadeOut();
	    $("#registerDugme").html('<span class="glyphicon glyphicon-transfer"></span> &nbsp; sending ...');
	}

	var _succesPostRequest =  function(data){
	    if(data.trim() == "success"){
	        $("#register-form").fadeOut(600, function(){ 
	        	$("#register-main-message").html(
	        			'<div class="alert alert-success"> <h3>Uspješno ste se registrovali</h3> </div>');
	        }); 
	        setTimeout(function(){
	        	$(".registracijaModal").modal('toggle');
	        }, 1500);

	    }
	    else {
	        $("#error").fadeIn(1000, function(){
	        	$("#registerDugme").html('Submit');
	            $("#error").html('<div class="alert alert-danger"><span class="glyphicon glyphicon-info-sign"></span> '+data+' !</div>');
	        });
	    }
	}

	var _error = function(xhr, textStatus, error){
		  $("#error").fadeIn(1000, function(){
		  	$("#registerDugme").html('Submit');
	      $("#error").html('<div class="alert alert-danger"><span class="glyphicon glyphicon-info-sign"></span> registration failed !</div>');
	  });
	}
	
	return {
		register : function submit(){
			var data = $("#register-form").serialize();
		     $.ajax({
		            type : 'POST',
		            url  : 'register.html',
		            data : data,
		            beforeSend: _beforeFormSend,
		            success : _succesPostRequest,
		            error: _error
		        });
		     return false;
		}
	};
})();


	


	