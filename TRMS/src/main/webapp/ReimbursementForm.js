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
	
}

function configureExistingForm(data) {
	
}