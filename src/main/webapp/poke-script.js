//grab elements form the page to work with
//grab input, id, name, sprite, button
const pokeId = document.getElementById('poke-id');
const respId = document.getElementById('resp-id');
const pokeName = document.getElementById('resp-name');
const pokeImg = document.getElementById('resp-sprite');
const button = document.querySelector('button');


//create a function to fetch poke object
function fetchPokemon(){
    //capture input from the doc
    let idNum = pokeId.value;


    //send fetch call to the pokeApi and concat the value of the poke we want
    fetch(`https://pokeapi.co/api/v2/pokemon/${idNum}`)
    .then(response => response.json())
    .then(renderPokemon)
    //chain functions to our promise -> parse JSON into an object, then call a function on the object
    
}
//create a function to render response
function renderPokemon(data){
    pokeName.innerHTML=`Name: ${data.name}`;
    respId.innerHTML = `Id: ${data.id}`;

    pokeImg.setAttribute('src',data.sprites.front_default);
    pokeImg.setAttribute('height', 300);
}
//add the event listener to the button
button.addEventListener('click', fetchPokemon);