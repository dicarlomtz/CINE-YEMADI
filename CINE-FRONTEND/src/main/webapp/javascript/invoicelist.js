function init()
{
    fetch('TicketsSoldService').then(function(resultado) {
        return resultado.json();
    }).then(function(datos){
        cargarTickets(datos['invoice-list']);
    });
}

function cargarTickets(datos)
{
    var ref = document.getElementById('invoiceList');
    
    if(ref)
    {
        datos.forEach((fila) => {
            
            var nuevaFila = ref.insertRow(-1);
            var nuevaCelda;
            
            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.textContent = fila['id'];
            nuevaCelda.setAttribute('class', 'd1');
            
            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.textContent = fila['date'];
            nuevaCelda.setAttribute('class', 'd1');
            
            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.textContent = fila['customer']['name'];
            nuevaCelda.setAttribute('class', 'd1');
            
            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.textContent = fila['payment-card']['number'];
            nuevaCelda.setAttribute('class', 'd1');
            
            var boton = document.creatElement("INPUT");
            boton.setAttribute('type', 'button');
            boton.setAttribute('value', 'PDF');
            boton.setAttribute('onclick', function() {
                var doc = new jsPDF();
    
                doc.text(10, 10, 'ID de factura: ' + fila['id']);
                doc.text(10, 10, 'Fecha: ' + fila['date']);
                doc.text(10, 10, 'Nombre del cliente: ' + fila['customer']['name']);
                doc.text(10, 10, 'Tarjeta: ' + fila['payment-card']['number']);
    
                doc.save("ticket.pdf");
            });
            
            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.appendChild(boton);
            nuevaCelda.setAttribute('class', 'd1');
        });
    }
}

window.onload = init;