//NEED TO SET THE BUTTONS UP THIS WAY....

/*let b1 = document.getElementById('approve');
let b2 = document.getElementById('deny');

b1.addEventListener('click', approveTicket);
b2.addEventListener('click', denyTicket);
*/
function buildApproveDenyTable(data){
	

	let p1 = document.getElementById('statuschange');
	console.log("p1 before: " + p1.innerHTML.toString());
	p1.innerHTML = "Ticket " + data.getId() + " submitted by " + data.getRequestedBy() + " for $" + data.getAmount() + "has been APPROVED" ;
    console.log("p1: after " + p1.innerHTML.toString());
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
    .then(data => buildApproveDenyTable(data));
 
   
  
}

function denyTicket(){
	let hostname = window.location.hostname;

	console.log("denyTicket called");

    // Get ticket that has been approved
    fetch(`http://${hostname}:8080/proj-01-team07/deny_ticket`) //get response as json
    .then(response => response.json())  //pars eth jason
    .then(data => buildApproveDenyTable(data));    //submit the data to the function for rendering
 
}