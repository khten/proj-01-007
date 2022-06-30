// Get table element
let table3 = document.getElementById('tickets-by-username-table');
//let button3 = document.getElementById('tickets-emp-id')

// Add button listener, call fetchEmps() method
//button.addEventListener("click", fetchTicketByUsername());

function buildTable3(data) {
    let header = document.createElement('thead'); // these are HTML elements
    let headerRow = document.createElement('tr');
    table3.innerHTML = "";

	header.appendChild(headerRow);
	table3.appendChild(header);

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

	data.forEach((t) => {
		let row = document.createElement('tr');
		let td1 = document.createElement('td');
		let td2 = document.createElement('td');
		let td3 = document.createElement('td');
		let td4 = document.createElement('td');
		let td5 = document.createElement('td');

		// set the inner HTML of each cell to the diff propertie s (
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
		table3.appendChild(row);
	});
}

const fetchTicketsByUsername = () => {
	let hostname = window.location.hostname;
	const findUsernameTxt = document.getElementById('txtFindUser').value;

	const u = {
		username: findUsernameTxt,
	};

	fetch(`http://${hostname}:8080/proj-01-team07/tickets_by_username`, {
		method: 'POST',
		body: JSON.stringify({
			username: u.username,
		}),

		headers: {
			'Content-type': 'application/json; charset=UTF-8',
		},
	})
		.then((response) => response.json())
		.then((data) => buildTable3(data));
};
