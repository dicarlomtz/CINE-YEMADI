function init()
{
    fetch('MoviesListService').then(function(resultado) {
        return resultado.json();
    }).then(function(datos) {
       cargarCartelera(datos['room-list']);
    });
}

function cargarCartelera(datos)
{
    var ref = document.getElementById('movielist');
    
    if(ref)
    {
        datos.forEach((fila) => {
            
            var nuevaFila = ref.insertRow(-1);
            var nuevaCelda;
            
            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.textContent = fila.id;
            nuevaCelda.setAttribute('class', 'd1');
            
            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.textContent = fila.title;
            nuevaCelda.setAttribute('class', 'd1');
            
            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.textContent = fila.poster-path;
            nuevaCelda.setAttribute('class', 'd1');
            
            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.textContent = fila.data;
            nuevaCelda.setAttribute('class', 'd1');
            
            var boton = document.creatElement("INPUT");
            boton.setAttribute('type', 'button');
            boton.setAttribute('value', 'Cambiar Estado');
            boton.setAttribute('onclick', `ChangeBillboardMovieStatus?movie=${fila.id}`);
            
            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.appendChild(boton);
            nuevaCelda.setAttribute('class', 'd1');
        });
    }
}

window.onload = init;