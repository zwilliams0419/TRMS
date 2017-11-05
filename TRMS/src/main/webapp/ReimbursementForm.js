"use strict";

var gradeFormats;
var eventTypes;
var role;

window.onload = function() {
	$("#passingGradeDiv").hide();
	$("body").hide();

	var url = new URL(location.href);
	var reqId = url.searchParams.get("id");

	if(!reqId) {
		$("#requestId").hide();
		$.getJSON("ReimbursementFormServlet", configureNewForm);
	} else {
		$.getJSON("ReimbursementFormServlet", { id: JSON.stringify(reqId) }, configureExistingForm);
	}

	$("#cost").on("change", updateReimbursementAmount);
	$("#eventType").on("change", updateReimbursementAmount);
	$("#gradeFormat").on("change", updatePassingGrade);
}



function configureNewForm(data) {
	setGlobals(data);

	$("#requesterName").val(data.firstName + " " + data.lastName);
	$("#requesterName").prop('readonly', true);
	$("#requesterId").val(data.requesterId);
	$("#requesterId").prop('readonly', true);
	$("#email").val(data.email);

	populateDropdowns(data);
	$("body").show();
}

function configureExistingForm(data) {
	setGlobals(data);

	$("#requestForm").prop('method', 'PUT');
	$("#requestFormBtn").html("Save");

	$("#requestId").val(data.requestId);
	$("#requesterName").val(data.firstName + " " + data.lastName);
	$("#requesterName").prop('readonly', true);
	$("#requesterId").val(data.requesterId);
	$("#requesterId").prop('readonly', true);
	$("#email").val(data.email);
	$("#gradeFormat").val(data.gradeFormat);
	$("#eventType").val(data.eventType);
	$("#zip").val(data.zip);
	//$("#approval").val(data.approval);
	$("#passingGrade").val(data.passingGrade);
	$("#cost").val(data.cost);
	$("#address").val(data.address);
	$("#city").val(data.city);
	$("#state").val(data.state);
	$("#desc").val(data.description);
	$("#workJustification").val(data.justification);
	$("#date").val(data.eventDate);
	$("#reimbursement").val(data.reimbursementAmount);
	//$("#finalGrade").val(data.finalGrade);
	//$("#creationDate").val(data.creationDate);

	configureByRole();
	populateDropdowns(data);
	$("body").show();
}

function updateReimbursementAmount() {
	if(isNaN($("#cost").val())) {
		$("#reimbursement").val(null);
		return;
	}

	$.each(eventTypes, function(i, event) {
		if (event.id == $('#eventType').val()) {
			$("#reimbursement").val($('#cost').val() * event.rate / 100);
		}
	})
	
}

function updatePassingGrade() {
	$.each(gradeFormats, function(i, grade) {
		//Find the grade that matches the currently selected grade format
		if (grade.id == $('#gradeFormat').val()) {
			$("#passingGrade").val(grade.defaultPassing);

			//Hide the passing grade if the max is 1 or 0, or display it otherwise
			if(grade.maxGrade == 1 || grade.maxGrade == 0) {
				$("#passingGradeDiv").hide(400);
			}
			else {
				$("#passingGradeDiv").show(400);
			}
		}
	})
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

function configureByRole() {
	if(role == 1) {
		$("#gradeFormat").prop('readonly', true);
		$("#eventType").prop('readonly', true);
		$("#zip").prop('readonly', true);
		$("#address").prop('readonly', true);
		$("#city").prop('readonly', true);
		$("#cost").prop('readonly', true);
		$("#state").prop('readonly', true);
		$("#description").prop('readonly', true);
		$("#justification").prop('readonly', true);
		$("#date").prop('readonly', true);
	}
}

function setGlobals(data) {
	gradeFormats = data.gradeFormats;
	eventTypes = data.eventTypes;
	role = data.role;
}