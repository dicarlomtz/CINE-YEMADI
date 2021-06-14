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
            nuevaCelda.textContent = fila.seat;
            nuevaCelda.setAttribute('class', 'd1');
            
            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.textContent = fila.amount;
            nuevaCelda.setAttribute('class', 'd1');
            
            var boton = document.creatElement("INPUT");
            boton.setAttribute('type', 'button');
            boton.setAttribute('value', 'PDF');
            boton.setAttribute('onclick', function() {
                var doc = new jsPDF();
    
                doc.text(10, 10, fila.id);
                doc.text(10, 10, fila.invoice);
                doc.text(10, 10, fila.cinema);
                doc.text(10, 10, fila.room);
                doc.text(10, 10, fila.date);
                doc.text(10, 10, fila.seat);
                doc.text(10, 10, fila.amount);
    
                doc.save("tickets.pdf");
            });
            
            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.appendChild(boton);
            nuevaCelda.setAttribute('class', 'd1');
        });
    }
}

window.onload = init;