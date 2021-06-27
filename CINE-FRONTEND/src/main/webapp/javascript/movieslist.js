/*
    Universidad Nacional de Costa Rica
    Escuela de Informática
    EIF209 Programación IV, ciclo I 2021
    Mauricio Gutiérrez Vásquez 118260119
    Adolfo Di Carlo Martínez Martínez 118050228
    Yeikol Villalobos Herrera 702670531
    Proyecto #2, Cine
*/

function init()
{
    setUser();
    validateAdmin();
    retrieveMovies();
}

function retrieveMovies() {
    getJSON('MovieService', {}, cargarCartelera);
}

function cargarCartelera(datos)
{
    var ref = document.getElementById('movieslist');

    if (ref)
    {
        datos['movie-list'].forEach((fila) => {

            var nuevaFila = ref.insertRow(-1);
            var nuevaCelda;

            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.textContent = fila.id;
            nuevaCelda.setAttribute('class', 'd1');

            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.textContent = fila.title;
            nuevaCelda.setAttribute('class', 'd1');

            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.textContent = fila.data;
            nuevaCelda.setAttribute('class', 'd1');

            var boton = document.createElement("INPUT");
            boton.setAttribute('type', 'checkbox');
            boton.setAttribute("id", `${fila.id}`);
            boton.checked = fila.billboard;
            boton.addEventListener("click", changeMovieStatus);

            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.appendChild(boton);
            nuevaCelda.setAttribute('class', 'd1');
        });
    }
}

function changeMovieStatus() {
    if (this) {
        let newVal = this.checked;
        let data = new FormData();
        let movie = {"id": this.getAttribute("id"), "billboard": newVal};
        data.append("movie", JSON.stringify(movie));
        sendForm(data);
    }
}

function sendForm(data) {
    getJSON('ChangeBillboardMovieStatus', data, handleResponse);
}

function handleResponse(result) {
    console.log(result["result"]);
    alert(result["result"]);
}

window.onload = init;