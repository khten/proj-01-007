let num = document.getElementById('acct-id').value;


function approveItem(){
	console.log("inside approveItem()");
	document.getElementById('approve-deny').innerHTML = "Attempting to approve Ticket";
}
function approveTicket() {

   
    // Fetch API is modern interface that allows you
    // to make HTTP requests to a server and process the results that 
    // you get back asynchrnously
    let hostname = window.location.hostname;

	console.log("approveTicket called")

    // Get employee table as array of JSON objects
    fetch(`http://${hostname}:8080/proj-01-team07/approve_ticket`)
    .then(response => response.json())
    .then(approveTicket());
 
   
  
}
function denyItem(){
	
	
	console.log("inside denyItem ")
	console.log("inside approveItem()");
	p1.innerText = "Ticket " + num + " has been DENIED.";
	
}
function denyTicket(){
	let hostname = window.location.hostname;

	console.log("denyTicket called");

    // Get employee table as array of JSON objects
    fetch(`http://${hostname}:8080/proj-01-team07/deny_ticket`);
  
 
}