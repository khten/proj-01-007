// Get table element
let table8 = document.getElementById('emp-info-table');
//let button = document.getElementById('all-emps')


function buildTable8(data) {
	let header = document.createElement('thead');
	let headerRow = document.createElement('tr');
	table8.innerHTML = "";

	header.appendChild(headerRow);
	table8.appendChild(header);

	// create a header column for FirstName
	let th1 = document.createElement('th');
	th1.innerHTML = 'First Name';

	// create a header column for last Name
	let th2 = document.createElement('th');
	th2.innerHTML = 'Last Name';

	// create a header column for username
	let th3 = document.createElement('th');
	th3.innerHTML = 'Username';

	// apend the child nodes onto the header
	headerRow.appendChild(th1);
	headerRow.appendChild(th2);
	headerRow.appendChild(th3);

	data.forEach((e) => {
		let row = document.createElement('tr');
		let td1 = document.createElement('td');
		let td2 = document.createElement('td');
		let td3 = document.createElement('td');

		//set inner HTML of each cell to the diff properties
		td1.innerHTML = e.firstName;
		td2.innerHTML = e.lastName;
		td3.innerHTML = e.username;

		// finally append each table cell to the row
		row.appendChild(td1);
		row.appendChild(td2);
		row.appendChild(td3);

		// append the row to table
		table8.appendChild(row);
	});
}

function fetchViewEmpInfo() {
	// Fetch API is modern interface that allows you
	// to make HTTP requests to a server and process the results that
	// you get back asynchrnously
	let hostname = window.location.hostname;

	console.log('fetchViewEmpInfo called');

	// Get employee table as array of JSON objects
	fetch(`http://${hostname}:8080/proj-01-team07/viewemps`)
		.then((response) => response.json())
		.then((data) => buildTable8(data));
}
