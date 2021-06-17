   function init()
{
    fetch('UserHistoryService').then(function(resultado) {
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
            
            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.textContent = fila['date'];
            
            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.textContent = fila['customer']['name'] + fila['customer']['surnames'];
            
            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.textContent = fila['payment-card']['number'];
            
            var boton = document.creatElement("INPUT");
            boton.setAttribute('type', 'button');
            boton.setAttribute('value', 'PDF');
            boton.setAttribute('onclick', function() {
                var doc = new jsPDF();
    
                doc.text('ID de factura: ' + fila['id'], 10, 10);
                doc.text('Fecha: ' + fila['date'], 10, 10);
                doc.text('Nombre del cliente: ' + fila['customer']['name'] + fila['customer']['surnames'], 10, 10);
                doc.text('Tarjeta: ' + fila['payment-card']['number'], 10, 10);
    
                doc.save("ticket.pdf");
            });
            
            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.appendChild(boton);
        });
    }
}

window.onload = init;