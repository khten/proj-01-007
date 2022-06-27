// grab the table  element from the page so
// we can modify how it looks and add elements

// var let const
let table = document.querySelector('table');
// this saves teh table element to the variable

let button = document.getElementById('all-tickets')
// WHEN the button is clicked, we
// make a call to the server, fetch the JSON
// DATA and pasrse it and append it to the table

// button.addEventListener('click', sayHello)
button.addEventListener('click', fetchTicket());

function buildTable(data) {

   
    let header = document.createElement('thead'); // these are HTML elements
    let headerRow = document.createElement('tr');

    header.appendChild(headerRow);
    table.appendChild(header); 

    // create a header column for FirstName
    let th1 = document.createElement('th');
    th1.innerHTML = 'Employee Id';

    // create a header column for last Name
    let th2 = document.createElement('th');
    th2.innerHTML = 'Description';

    // create a header column for username
    let th3 = document.createElement('th');
    th3.innerHTML = 'Status';

    // apend the child nodes onto the header
    headerRow.appendChild(th1);
    headerRow.appendChild(th2);
    headerRow.appendChild(th3);

    data.forEach(t => {

       

        let row = document.createElement('tr');
        let td1 = document.createElement('td');
        let td2 = document.createElement('td');
        let td3 = document.createElement('td');

        // set the inner HTML of each cell to the diff propertie s (firstname, lastname, usewrnam )
        td1.innerHTML = t.employeeId;
        td2.innerHTML = t.description;
        td3.innerHTML = t.status;

        // finally append each table cell to the row
        row.appendChild(td1);
        row.appendChild(td2);
        row.appendChild(td3);

        // append the row to table
        table.appendChild(row);
    });
}

function fetchTicket() {

    // Fetch API is modern interface that allows you
    // to make HTTP requests to a server and process the results that 
    // you get back asynchrnously
    let hostname = window.location.hostname;

    //console.log('fetchEmps triggered')

    fetch(`http://${hostname}:8080/proj-01-team07/admin`)
    .then(response => response.json()) 
    //.then(obj => console.log(obj))
    .then(data => buildTable(data)); // this automatically passes the data that's been parsed
                      // The JS object is an array of Employee objects
                      // passes to the build table
}
