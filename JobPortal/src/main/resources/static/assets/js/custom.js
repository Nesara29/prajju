var ajxfrm;   

function validatemobile(evt)
 {
  evt = (evt) ? evt : window.event;
   var charCode = (evt.which) ? evt.which : evt.keyCode;
   if (charCode > 31 && (charCode < 48 || charCode > 57)) {
       return false;
   }
   return true;
 } 
   function checkEmail(email) { 
   var re = /\S+@\S+\.\S+/;
       return re.test(email);
 }
 
 function  validatealpha(event)
 { 
    var charCode = event.keyCode;

           if ((charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123) || charCode == 8 || charCode == 32 || charCode == 46)

               return true;
           else
               return false;
 }
 
 function toggleeditfrm(ele){
	var id=$(ele).attr("data-id");
	$('#editfrm'+id).slideToggle();
 }
   
  
 $(function(){
	$("table.dataTable").dataTable();
 	
 	   $("form.ajaxfrm").off( "submit");
 	   	$("form.ajaxfrm").submit(function(e) {

 	   	    e.preventDefault(); // avoid to execute the actual submit of the form.
            ajxfrm=this;
 		   $(this).find('button').attr('disabled',true);
 	   	    var form = $(this);
 	   	    var url = form.attr('action');
			var formData = new FormData($(this)[0]);
 			jQuery('.msg').removeClass('d-none').addClass('d-none');
 	   	    $.ajax({
 	   	           type: "POST",
 	   	           url: url,
 	   	           data: formData,// serializes the form's elements.
 	   			    dataType: "json",
					processData: false,  // tell jQuery not to process the data
					       contentType: false,  // tell jQuery not to set contentType
 	   	           success: function(data)
 	   	           {
 	   			      if(data.success)
 	   	                { 
 							$(ajxfrm).find(".msg.msg-success").html(data.html).removeClass('d-none');
 							if($(ajxfrm).attr("data-reset")=="1") $(ajxfrm).trigger("reset");
 						
 							setTimeout(function() {  
 								if($(ajxfrm).attr("data-reload")=="1")
 								    document.location.reload();
 							}, 2000);
 						} // show response from the php script.
 	   				  else
 					  {

 						$(ajxfrm).find('button').attr('disabled',false);
 	   				     $(ajxfrm).find(".msg.msg-error").html(data.html).removeClass('d-none'); // show response from the php script.
 	   				   }
 						  
 	   				  setTimeout(function() {jQuery('.msg').addClass('d-none');}, 2000);
 	   	           }
 	   	         });
 	   			     });
					 
					 var prevhtml;
					 var dele;
					 $(".dialoganchor").off( "click");
					  $(".dialoganchor").click(function(e) { 
					     e.preventDefault(); // avoid to execute the actual submit of the form.

					     var ele = $(this);
						 dele=this;
					     var url = ele.attr('data-href');
						 prevhtml= $(ele).html();
						 $(ele).html('<strong>Processing.......</strong>');
						 jQuery('.msg').removeClass('d-none').addClass('d-none');
						 $(ele).attr('disabled',true);
					      if(!url) return;
					     $.ajax({
					            type: "POST",
					            url: url,  
					 		    dataType: "json",
					            success: function(data)
					            {
									$(dele).attr('disabled',false);
					 		        if(data.success==1)
										{
											$(".msg.msg-success").html(data.html).removeClass('d-none');
										setTimeout(function(){document.location.reload();},1000);
										}
									else 
									{
									    $(dele).html(prevhtml);
										$(".msg.msg-error").html(data.html).removeClass('d-none');
									}
									}
					          });
							  });
 }) ;