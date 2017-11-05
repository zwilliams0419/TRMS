"use strict";

window.onload = function() {
	var url = new URL(location.href);
	var reqId = url.searchParams.get("id");

	if(!reqId) {
		$.getJSON("ReimbursementFormServlet", configureNewForm);
	} else {
		$.getJSON("ReimbursementFormServlet", { id: JSON.stringify(reqId) }, configureExistingForm);
	}
	
}



function configureNewForm(data) {
	$("#requesterName").val(data.firstName + " " + data.lastName);
	$("#requesterName").prop('readonly', true);
	$("#requesterId").val(data.requesterId);
	$("#requesterId").prop('readonly', true);
	$("#email").val(data.email);

	populateDropdowns(data);
}

function configureExistingForm(data) {
	
}

function updateReimbursementAmount() {

}

function updateDefaultPassing() {

}

function populateDropdowns(data) {
	$.each(data.gradeFormats, function (i, grade) {
	    $('#gradeFormat').append($('<option>', { 
	        value: grade.id,
	        text : grade.name 
	    }));
	});

	$.each(data.eventTypes, function (i, event) {
	    $('#eventType').append($('<option>', { 
	        value: event.id,
	        text : event.name 
	    }));
	});
}