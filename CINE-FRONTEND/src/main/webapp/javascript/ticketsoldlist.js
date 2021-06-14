function init()
{
    fetch('TicketsSoldService').then(function(resultado) {
        return resultado.json();
    }).then(function(datos){
        cargarTickets(datos['ticket-list']);
    });
}

function cargarTickets(datos)
{
    var ref = document.getElementById('ticketList');
    
    if(ref)
    {
        datos.forEach((fila) => {
            
            var nuevaFila = ref.insertRow(-1);
            var nuevaCelda;
            
            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.textContent = fila.id;
            nuevaCelda.setAttribute('class', 'd1');
            
            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.textContent = fila.invoice;
            nuevaCelda.setAttribute('class', 'd1');
            
            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.textContent = fila.cinema;
            nuevaCelda.setAttribute('class', 'd1');
            
            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.textContent = fila.room;
            nuevaCelda.setAttribute('class', 'd1');
            
            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.textContent = fila.date;
            nuevaCelda.setAttribute('class', 'd1');
            
            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.textContent = fila.function-seat;
            nuevaCelda.setAttribute('class', 'd1');
            
            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.textContent = fila.amount;
            nuevaCelda.setAttribute('class', 'd1');
        });
    }
}

window.onload = init;