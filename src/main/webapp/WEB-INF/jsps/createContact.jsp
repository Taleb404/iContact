<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create Contact</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.3/jquery.min.js" type="text/javascript"></script>

<script>
$(document).ready(function() {
	   $('#submitBtn').hide();
    $(".form > :input").keyup(function() {
        var $emptyFields = $('.form :input').filter(function() {
        	   $('#submitBtn').hide();
            return $.trim(this.value) === "";
         
        });

        if (!$emptyFields.length) {
           $('#submitBtn').show();
        }
    });
    
    $('#submitBtn').click(function (e) {
    	var validation = {
    		    isEmailAddress:function(str) {
    		        var pattern =/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    		        return pattern.test(str); 
    		    },
    		    isNumber:function(str) {
    		        var pattern = /^\d+$/;
    		        return pattern.test(str); 
    		    }
    		};
    	
    	var emailVal = $('#email').val();
    	var phoneVal = $('#phone').val();
		if(validation.isEmailAddress(emailVal)==false)
			{
			alert('Kindly correct the email address');
			   e.preventDefault();
			return;
			}
		if(validation.isNumber(phoneVal)==false)
		{
		alert('Kindly correct the Phone Number');
		   e.preventDefault();
		return;
		}
    	
   	});
    
});
</script>

</head>
<body>

<form action="saveContact" method="post">
<pre>
<h2>Create Contact:</h2>
<div class="form">
First Name:<input type="text" name="firstname" />
Last Name:<input type="text" name="lastname" />
User Name:<input type="text" name="email" id="email"/>
Phone Number :<input type="text" name="phone"  id="phone"/>
Address :<input type="text" name="address" />
</div>
<input type="submit" value="Add Contact"  id="submitBtn"/>
</pre>
</form>
${msg}
${existed}
<a href="displayContactss">Display All Contacts</a>


</body>
</html>