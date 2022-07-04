

// Get table element
let table7 = document.getElementById('admin-resolved-table');
//let button3 = document.getElementById('tickets-emp-id')

// Add button listener, call fetchEmps() method
//button.addEventListener("click", fetchTicketByUsername());


export function buildTable7(data) {
    let header = document.createElement('thead'); // these are HTML elements
    let headerRow = document.createElement('tr');
    table7.innerHTML = "";

    header.appendChild(headerRow);
    table7.appendChild(header);

    
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

	let th6 = document.createElement('th');
	th6.innerHTML = 'Resolved By'
    // apend the child nodes onto the header
    headerRow.appendChild(th1);
    headerRow.appendChild(th2);
    headerRow.appendChild(th3);
    headerRow.appendChild(th4);
    headerRow.appendChild(th5);
	headerRow.appendChild(th6);
	
    data.forEach((t) => {
        let row = document.createElement('tr');
        let td1 = document.createElement('td');
        let td2 = document.createElement('td');
        let td3 = document.createElement('td');
        let td4 = document.createElement('td');
        let td5 = document.createElement('td');
		let td6 = document.createElement('td');
        // set the inner HTML of each cell to the diff propertie s (
        //TODO figure out how to get the employee of the ticket ....
        td1.innerHTML = t.transactionId;
        td2.innerHTML = t.requestedBy;
        td3.innerHTML = t.amount;
        td4.innerHTML = t.description;
        td5.innerHTML = t.status;
		td6.innerHTML = t.resolvedBy;
		
        // finally append each table cell to the row
        row.appendChild(td1);
        row.appendChild(td2);
        row.appendChild(td3);
        row.appendChild(td4);
        row.appendChild(td5);
		row.appendChild(td6);
        // append the row to table
        table7.appendChild(row);
    });
    

}

const fetchAllResolved = () => {
	let hostname = window.location.hostname;
	

	console.log('fetchAllResolved triggered');
	


	fetch(`http://${hostname}:8080/proj-01-team07/all_resolved_tickets`)
	
	.then((response) => response.json())
	.then((data) => buildTable7(data))

};
