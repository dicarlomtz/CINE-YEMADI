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
            nuevaCelda.textContent = fila.poster-patch;
            nuevaCelda.setAttribute('class', 'd1');
            
            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.textContent = fila.data;
            nuevaCelda.setAttribute('class', 'd1');
        });
    }
}

window.onload = init;