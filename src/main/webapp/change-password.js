
function displayPwdChangeSuccess() {
let p1 = document.getElementById('pwd-change');
let newPwd = document.getElementById("pwd-field").value;

p1.innerHTML = "Successfully changed password to: " + newPwd;

//reset(newPwd);
	
}

function fetchChangePassword() {
	// Fetch API is modern interface that allows you
	// to make HTTP requests to a server and process the results that
	// you get back asynchrnously
	let hostname = window.location.hostname;
    const findPasswordTxt = document.getElementById("pwd-field").value;
   

	console.log('fetchChangePassword called');
    console.log('new password: ' + findPasswordTxt);
    
	// Get employee table as array of JSON objects
	fetch(`http://${hostname}:8080/proj-01-team07/change_password`,{
		method: 'POST',
		body: JSON.stringify({
			password: findPasswordTxt  
			
		}),
		
		headers: {
			'Content-type': 'application/json; charser=UTF-8',
		}
		})
		 displayPwdChangeSuccess();
		//displayPwdChangeSuccess();
			
			
    }
