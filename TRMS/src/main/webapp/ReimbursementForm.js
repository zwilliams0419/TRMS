"use strict";

var gradeFormats;
var eventTypes;

window.onload = function() {
	$("#passingGradeDiv").hide();
	$("body").hide();

	var url = new URL(location.href);
	var reqId = url.searchParams.get("id");

	if(!reqId) {
		$.getJSON("ReimbursementFormServlet", configureNewForm);
	} else {
		$.getJSON("ReimbursementFormServlet", { id: JSON.stringify(reqId) }, configureExistingForm);
	}

	$("#cost").on("change", updateReimbursementAmount);
	$("#eventType").on("change", updateReimbursementAmount);
	$("#gradeFormat").on("change", updatePassingGrade);

	$("body").show();
}



function configureNewForm(data) {
	setReferenceTables(data);

	$("#requesterName").val(data.firstName + " " + data.lastName);
	$("#requesterName").prop('readonly', true);
	$("#requesterId").val(data.requesterId);
	$("#requesterId").prop('readonly', true);
	$("#email").val(data.email);

	populateDropdowns(data);
}

function configureExistingForm(data) {
	setReferenceTables(data);
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

function setReferenceTables(data) {
	gradeFormats = data.gradeFormats;
	eventTypes = data.eventTypes;
}