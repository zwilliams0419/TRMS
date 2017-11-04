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
	$("#empName").val(data.firstName + " " + data.lastName);
	$("#empName").prop('readonly', true);
	$("#empId").val(data.employeeId);
	$("#empId").prop('readonly', true);
	$("#email").val(data.email);
}

function configureExistingForm(data) {
	
}