let id = document.getElementById('acct-id').value;


let button = document.getElementById('approve-button');

button.addEventListener('click', approveTicket());

function approveTicket() {

    // Fetch API is modern interface that allows you
    // to make HTTP requests to a server and process the results that 
    // you get back asynchrnously
    let hostname = window.location.hostname;

	console.log("approveTicket called")

    // Get employee table as array of JSON objects
    fetch(`http://${hostname}:8080/proj-01-team07/approve_ticket`)
    .then(response => response.json()) 
    .then(data => buildTable(data)); 
}