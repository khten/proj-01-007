function displayNewTicketSuccess(){
  let p1 = document.getElementById('ticket-submitted');
  
  
  p1.innerHTML = "Ticket Submitted: ";
}

function makeNewTicket(){
   let hostname = window.location.hostname;
   const amount = document.getElementById("amount").value;
   const description = document.getElementById("description").value;
   fetch(`http://${hostname}:8080/proj-01-team07/make_new_ticket`,{
		method: 'POST',
		body: JSON.stringify({
			amount: amount,
			description: description
			
		}),
		
		headers: {
			'Content-type': 'application/json; charser=UTF-8',
		}
		})
		
		displayNewTicketSuccess();
   
   


}