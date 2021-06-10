function init()
{
    fetch('MoviesListService').then(function(resultado) {
        return resultado.json();
    }).then(function(datos) {
       cargarCartelera(datos['datos_peliculas']);
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
            nuevaCelda.textContent = fila.secuencia;
            nuevaCelda.setAttribute('class', 'd1');
            
            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.textContent = fila.nombre;
            nuevaCelda.setAttribute('class', 'd1');
        });
    }
}

window.onload = init;