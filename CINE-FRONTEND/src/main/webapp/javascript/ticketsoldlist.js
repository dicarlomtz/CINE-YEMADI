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
    
}

window.onload = init;