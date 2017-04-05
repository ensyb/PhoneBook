var Register = (function(){
	
	var defaults = {
			registerBefore : '<span class="glyphicon glyphicon-transfer"></span> &nbsp; sending ...',
	}
	
	function submit(){
		var data = $("#register-form").serialize();
	     $.ajax({
	            type : 'POST',
	            url  : 'register.html',
	            data : data,
	            beforeSend: function(){
	                $("#error").fadeOut();
	                $("#registerDugme").html(defaults.registerBefore);
	            },
	            success :  function(data){
	               if(data.trim() == "success"){
	                    $("#register-form").fadeOut(500, function(){ 
	                    	$("#register-main-message").html(
	                    			'<div class="alert alert-success"> <h3>Uspješno ste se registrovali</h3> </div>');
	                    }); 
	                    setTimeout(function(){
	                    	$(".registracijaModal").modal('toggle');
	                    }, 2000);

	                }
	                else if(data != "success"){
	                    $("#error").fadeIn(1000, function(){
	                        $("#error").html('<div class="alert alert-danger"><span class="glyphicon glyphicon-info-sign"></span> &nbsp; '+data+' !</div>');
	                        $("#btn-submit").html('<span class="glyphicon glyphicon-log-in"></span> &nbsp; Create Account');
	                    });
	                }
	            }
	        });
	}
	
	function handleRegistration(){
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
			submitHandler : submit
			
			})
		};
		return {
			handle : handleRegistration
		};
})();
	