//NEED TO SET THE BUTTONS UP THIS WAY....
let table4 = document.getElementById('approve-deny-table');
l/*et b1 = document.getElementById('approve');
let b2 = document.getElementById('deny');

b1.addEventListener('click', approveTicket);
b2.addEventListener('click', denyTicket);
*/
function buildApproveDenyTable(data){
	
	let header = document.createElement('thead'); // these are HTML elements
	let headerRow = document.createElement('tr');
	table4.innerHTML = "";

	header.appendChild(headerRow);
	table4.appendChild(header);

	// create a header column for FirstName
	let th1 = document.createElement('th');
	th1.innerHTML = 'Ticket Id';

	let th2 = document.createElement('th');
	th2.innerHTML = 'Requested By';

	let th3 = document.createElement('th');
	th3.innerHTML = 'Amount';

	// create a header column for last Name
	let th4 = document.createElement('th');
	th4.innerHTML = 'Description';

	// create a header column for username
	let th5 = document.createElement('th');
	th5.innerHTML = 'Status';

	// apend the child nodes onto the header
	headerRow.appendChild(th1);
	headerRow.appendChild(th2);
	headerRow.appendChild(th3);
	headerRow.appendChild(th4);
	headerRow.appendChild(th5);

	data.forEach(t => {



		let row = document.createElement('tr');
		let td1 = document.createElement('td');
		let td2 = document.createElement('td');
		let td3 = document.createElement('td');
		let td4 = document.createElement('td');
		let td5 = document.createElement('td');

		// set the inner HTML of each cell to the diff propertie s (
		//TODO figure out how to get the employee of the ticket ....
		td1.innerHTML = t.transactionId;
		td2.innerHTML = t.requestedBy;
		td3.innerHTML = t.amount;
		td4.innerHTML = t.description;
		td5.innerHTML = t.status;

		// finally append each table cell to the row
		row.appendChild(td1);
		row.appendChild(td2);
		row.appendChild(td3);
		row.appendChild(td4);
		row.appendChild(td5);

		// append the row to table
		table4.appendChild(row);
	});
   }
    
/*	let p1 = document.getElementById('approve-deny');
	let s1 = "Ticket " + data.transactionId + " submitted by " + data.requestdBy + " for $" +
	         data.amount + " has been Approved";
	p1.innerHTML = s1;
*/

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
function denyItem(data){
//TODO MAY NEED TO DELETE THIS	
	
	//console.log("inside denyItem ")
	//console.log("inside approveItem()");
/*	let p1 = document.getElementById('approve-deny');
	let s1 = "Ticket " + data.transactionId + " submitted by " + data.requestdBy + " for $" +
	         data.amount + " has been DENIED";
	p1.innerHTML = s1;
*/
}
function denyTicket(){
	let hostname = window.location.hostname;

	console.log("denyTicket called");

    // Get ticket that has been approved
    fetch(`http://${hostname}:8080/proj-01-team07/deny_ticket`) //get response as json
    .then(response => response.json())  //pars eth jason
    .then(data => buildApproveDenyTable(data));    //submit the data to the function for rendering
 
 
}