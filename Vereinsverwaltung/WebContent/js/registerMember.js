	  $( function() {
	    var dialog, form,
		  emailRegex = /^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/,
	      first_name = $( "#first_name" ),
	      email_address = $( "#email_address" ),
	      last_name = $( "#last_name" ),
	      birth_date = $("#birth_date"),
	      gender = $("#gender"),
	      phone_number = $("#phone_number"),
	      address_line = $("#address_line"),
	      address_add = $("#address_add"),
	      post_code = $("#post_code"),
	      entry_date = $("#entry_date"),
	      city = $("#city"),
	      password = $("#password"),
	      allFields = $( [] ).add( first_name ).add( last_name ).add( email_address ).add( birth_date).add( gender).add(phone_number).add(address_line).add(address_add).add(post_code).add(entry_date).add(city).add(password),
	      tips = $( ".validateTips" );
	 
	    function updateTips( t ) {
	      tips
	        .text( t )
	        .addClass( "ui-state-highlight" );
	      setTimeout(function(){
	        tips.removeClass( "ui-state-highlight", 1500 );
	      }, 500 );
	    }
	 
	    function checkLength( o, n, min, max ) {
	      if ( o.val().length > max || o.val().length < min ) {
	        o.addClass( "ui-state-error" );
	        updateTips( "Length of " + n + " must be between " +
	          min + " and " + max + "." );
	        return false;
	      } 
	      else {
	        return true;
	      }
	    }
	 
	    function checkRegexp( o, regexp, n ) {
	      if ( !( regexp.test( o.val() ) ) ) {
	        o.addClass( "ui-state-error" );
	        updateTips( n );
	        return false;
	      } else {
	        return true;
	      }
	    }
	 
	    function addUser() {
	      var valid = true;
	      allFields.removeClass( "ui-state-error" );
		  if($("input[type='radio'].radioBtnClass").is(':checked')) {
	      var gender_type = $("input[type='radio'].radioBtnClass:checked").val();
	}
	     valid = valid && checkRegexp(email_address, emailRegex, "max.muster@hof-university.de");	
	      if ( valid ) {
	        $.post("registerMember", 
	        		{first_name : first_name.val(), 
	        		 email_address : email_address.val(),
	        		 last_name : last_name.val(),
	        		 birth_date : birth_date.val(),
	        		 gender : gender_type,
	        		 email_address : email_address.val(),
	        		 phone_number : phone_number.val(),
	        		 address_line : address_line.val(),
	        		 address_add : address_add.val(),
	        		 post_code : post_code.val(),
	        		 entry_date : entry_date.val(),
	        		 city : city.val(),
	        		 });
	        history.go(0);
	        dialog.dialog( "close" );
	      }
	      return valid;
	    }
	 
	    dialog = $( "#dialog-form" ).dialog({
	      autoOpen: false,
	      height: 700,
	      width: 350,
	      modal: true,
	      buttons: {
	        "Create an account": addUser,
	        Cancel: function() {
	          dialog.dialog( "close" );
	        }
	      },
	      close: function() {
	        form[ 0 ].reset();
	        allFields.removeClass( "ui-state-error" );
	      }
	    });
	 
	    form = dialog.find( "form" ).on( "submit", function( event ) {
	      event.preventDefault();
	      history.go(0);
	      addUser();
	    });
	 
	    $( "#create-user" ).button().on( "click", function() {
	      dialog.dialog( "open" );
	    });
	  } );