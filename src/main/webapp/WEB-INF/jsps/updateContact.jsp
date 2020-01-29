<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html> 
<head>
<meta charset="ISO-8859-1">
<title>Update Contact</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.3/jquery.min.js" type="text/javascript"></script>

<script>
$(document).ready(function() {
	  
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
<form action="updateContact" method="post">
<pre>
<h2>Create Contact:</h2>
<div class="form">
ID :<input type="text" name="id" value="${contact.id}" readonly="readonly" />
First Name:<input type="text" name="firstname" value="${contact.firstname}" />
Last Name:<input type="text" name="lastname"  value="${contact.lastname}"  />
User Name:<input type="text" name="email"  id="email" value="${contact.email}"  />
Phone Number :<input type="text" name="phone" id="phone"  value="${contact.phone}"  />
Address :<input type="text" name="address"  value="${contact.address}"  />
Version :<input type="text" name="version"  value="${contact.version}"  readonly="readonly"/>
</div>
<input type="submit" id="submitBtn" value="Save" />
</pre>
</form>
${msg}
</body>
</html>