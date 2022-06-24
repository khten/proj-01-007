console.log('Hello Javascript world!!!');

//grab the table elements from the page so we can modify it and add elements

let table = document.querySelector('table');
//this saves the table element to the variable

let button = document.getElementById('all-emps');
//when the button is clicked, we make a call to the server
// fetch the json data
// parse the data
// append to the table
button.addEventListener('click', fetchEmps);

function buildTable(data){
    console.log("buildTable method triggered");
    console.log(data);

    let header = document.createElement('thead');
    let headerRow = document.createElement('tr');

    header.appendChild(headerRow);

    //append the header to the table
    table.appendChild(header);

    //create a header column for firstname, lastname, and username
    let th1= document.createElement('th');
    th1.innerHTML= 'First Name';

    let th2= document.createElement('th');
    th1.innerHTML= 'Last Name';

    let th3= document.createElement('th');
    th1.innerHTML= 'Username';
    
    headerRow.appendChild(th1);
    headerRow.appendChild(th2);
    headerRow.appendChild(th3);
    
    data.forEach(e=> {
         console.log(e);
         let row = document.createElement('tr');
         let td1 = document.createElement('td');
         let td2 = document.createElement('td');
         let td3 = document.createElement('td');

         //set inner HTML of each cell to the diff properties
         td1.innerHTML=e.firstName;
         td2.innerHTML=e.lastName;
         td3.innerHTML=e.userName;

         //finally append values to the row
          row.appendChild(td1);
          row.appendChild(td2);
          row.appendChild(td3);
          
         //append the row to the table
          table.appendChild(row);

    });
}
function fetchEmps(){
    //fetch API is a modern interface that allows you to make HTTP requests
    let hostname=window.location.hostname; //grabs ip with where its located
    //FOR LOCALHOST we need the port :8080 after hostname...but remove the port number because when it is deployed it won't need it  (provided by ec2)
    fetch(`http://${hostname}/proj-01-team07/employees`)
    .then(response => response.json()) //takes a json string and transforms it
                                       //to a javascript object
    .then(obj => console.log(obj))
    .then(buildTable); //automatically passes the data that's been parsed
                       //passes to the build table
}
/*let user = {
    firstName: "fn",
    lastName: "ln",
    username:"bob",
    password: "secret",
    role: "emp"
}
*/
function sayHello(){
    console.log('Hello there!');
}
