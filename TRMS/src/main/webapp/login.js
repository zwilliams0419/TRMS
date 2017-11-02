window.onload = function() {
	checkForError(location.href);
	
	//document.getElementById("submit_id").addEventListener("click", submit, false);
}

function checkForError(u) {
	let url = new URL(u);
	let a = url.searchParams.get("action");
	if(a == "loginerror") {
		//Display an error message above the login fields
		$('body').prepend("<p>Invalid login credentials</p>");
	}
}

/*function submit() {
	let uName = document.getElementById("inputUsername").value;
	let password = document.getElementById("inputPassword").value;
	
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		
		//if(xhr.readyState == 4 && xhr.status == 200)
	}
	
	xhr.open("POST", "LoginServlet", true);
	xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	let data = "username="+JSON.stringify(uName) + "," +
		"password=" + JSON.stringify(password);
	xhr.send(data);
	
	
}*/