//const response = await fetch('https://jsonplaceholder.typicode.com/albums/6');
//const body = await response.json();
//console.log(body);

async function hentGreier(url) {
    const response = await fetch(url);
    const body = await response.json();
    return body;
}

const svar = await hentGreier('https://jsonplaceholder.typicode.com/albums/6');
console.log(JSON.stringify(svar));
