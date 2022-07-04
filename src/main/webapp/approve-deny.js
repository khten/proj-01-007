//NEED TO SET THE BUTTONS UP THIS WAY....

/*let b1 = document.getElementById('approve');
let b2 = document.getElementById('deny');

b1.addEventListener('click', approveTicket);
b2.addEventListener('click', denyTicket);
*/

import * as pending from './admin-pending-table.js';
import * as resolved from './admin-resolved-table.js';



function buildApproveDenyTable(status) {


	let p1 = document.getElementById('statuschange');
	console.log("p1 before: " + p1.innerHTML.toString());
	p1.innerHTML = "Ticket " + document.getElementById('acct-id').value + " has been " + status;
	console.log("p1: after " + p1.innerHTML.toString());


	function buildApproveDenyTable(status) {
		let p1 = document.getElementById('statuschange');
		console.log('p1 before: ' + p1.innerHTML.toString());
		p1.innerHTML =
			'Ticket ' +
			document.getElementById('acct-id').value +
			' has been ' +
			status;
		console.log('p1: after ' + p1.innerHTML.toString());

	}

	function approveTicket() {
		// Fetch API is modern interface that allows you
		// to make HTTP requests to a server and process the results that
		// you get back asynchrnously
		let hostname = window.location.hostname;
		const p1 = document.getElementById('acct-id').value;

		console.log('approveTicket called');

		// Get ticket as JSON objects
		fetch(`http://${hostname}:8080/proj-01-team07/approve_ticket`, {
			method: 'POST',
			body: JSON.stringify({
				ticketId: p1,
			}),
			headers: {
				'Content-type': 'application/json; charset=UTF-8',


			}

		})
			.then(response => response.json())
			.then(data => {
				buildApproveDenyTable("Approved")
				pending.buildTable6(data);
				resolved.buildTable7(data);

			})




	}

	function denyTicket() {
		let hostname = window.location.hostname;
		const p1 = document.getElementById('acct-id').value;

		console.log('denyTicket called');

		// Get ticket that has been approved
		fetch(`http://${hostname}:8080/proj-01-team07/deny_ticket`, {
			method: 'POST',
			body: JSON.stringify({
				ticketId: p1,
			}),
			headers: {
				'Content-type': 'application/json; charset=UTF-8',

			}

		}) //get response as json
			.then(response => response.json())  //pars eth jason
			.then(data => {
				buildApproveDenyTable("Approved")
				pending.buildTable6(data);
				resolved.buildTable7(data);

			})
	}

}

