window.jsPDF = window.jspdf.jsPDF;

function init()
{
    fetch('UserHistoryService').then(function(resultado) {
        return resultado.json();
    }).then(function(datos){
        createSelectInvoice(datos['invoice-list']);
    });
}

function createSelectInvoice(datos)
{
    var refSelect = document.getElementById('selectInvoice');
    
    if(refSelect)
    {
        {
            let opt = document.createElement('OPTION');
            opt.setAttribute("value", "null");
            opt.appendChild(document.createTextNode("(Seleccione la compra)"));
            refSelect.appendChild(opt);
        }
        
        datos.forEach(fila => {
            let opt = document.createElement('OPTION');
            opt.setAttribute('value', fila['id']);
            opt.appendChild(document.createTextNode(
                    fila['id'] + ' - ' + 
                    fila['date'] + ' - ' + 
                    fila['customer']['name'] + fila['customer']['surnames']));
            refSelect.appendChild(opt);
        });
    }
}

function searchInvoice()
{
    var selectValue = document.getElementById('selectInvoice').value;
    
    if(selectValue !== 'null')
    {
        fetch('TicketListService').then(function(resultado) {
            return resultado.json();
        }).then(function(datos){
            cargarTickets(datos['ticket-list'], selectValue);
        });
    }
    else
    {
        alert('Debe seleccionar una factura');
    }
}

function cargarTickets(datos, invoice)
{
    var refTable = document.getElementById('ticketList');
    var refFoot = document.getElementById('ticketList');
    var precioTotal = 0.0;
    
    if(refTable && refFoot)
    {
        datos.forEach((fila) => {
            
            if(invoice === fila['invoice']['id'])
            {            
                var nuevaFila = refTable.insertRow(-1);
                var nuevaCelda;
            
                nuevaCelda = nuevaFila.insertCell(-1);
                nuevaCelda.textContent = fila['invoice']['id'];
                
                nuevaCelda = nuevaFila.insertCell(-1);
                nuevaCelda.textContent = fila['cinema']['name'];
                
                nuevaCelda = nuevaFila.insertCell(-1);
                nuevaCelda.textContent = fila['room']['number'];
                
                nuevaCelda = nuevaFila.insertCell(-1);
                nuevaCelda.textContent = fila['date'];
                
                nuevaCelda = nuevaFila.insertCell(-1);
                nuevaCelda.textContent = fila['seat']['row'] + fila['seat']['position'];
                
                nuevaCelda = nuevaFila.insertCell(-1);
                nuevaCelda.textContent = fila['amount'];
                precioTotal += fila['amount'];
            }
        });
        
        var nuevaFila = refFoot.insertRow(-1);
        var nuevaCelda;
        
        nuevaCelda = nuevaFila.insertCell(-1);
        nuevaCelda.setAttribute('colspan', '6');
        nuevaCelda.textContent = precioTotal;
    }
}

function generatePDF(datos, invoice)
{
    var selectValue = document.getElementById('selectInvoice').value;
    var doc = new jsPDF();
    var precioTotal = 0.0;
    
    invoice.forEach(fila => {
        if(selectValue === fila['id'])
        {
            doc.text('ID de factura: ' + fila['id'], 10, 10);
            doc.text('Fecha: ' + fila['date'], 10, 10);
            doc.text('Nombre del cliente: ' + fila['customer']['name'] + fila['customer']['surnames'], 10, 10);
            doc.text('Tarjeta: ' + fila['payment-card']['number'], 10, 10);
        }
    });
    
    datos.forEach(fila => {
        if(selectValue === fila['invoice']['id'])
        {
            doc.text('--------------------------------------------------', 10, 10);
            doc.text('Cine: ' + fila['cinema']['name'], 10, 10);
            doc.text('Sala: ' + fila['room']['number'], 10, 10);
            doc.text('Asiento: ' + fila['seat']['row'] + fila['seat']['position'], 10, 10);
            doc.text('Precio: ' + fila['amount'], 10, 10);
            doc.text('--------------------------------------------------', 10, 10, );
            precioTotal += fila['amount'];
        }
    });
    
    doc.text('Precio total: ' + precioTotal, 10, 10);
    doc.save("ticket.pdf");
}

function ticketsPDF()
{
    var selectValue = document.getElementById('selectInvoice').value;
    var invoice;
    var tickets;
    
    if(selectValue !== 'null')
    {
        fetch('UserHistoryService').then(function(resultado) {
            return resultado.json();
        }).then(function(datos){
            invoice = datos['invoice-list'];
        });
        
        fetch('TicketListService').then(function(resultado) {
            return resultado.json();
        }).then(function(datos){
            tickets = datos['ticket-list'];
        });
        
        generatePDF(tickets, invoice);
    }
    else
    {
        alert('Debe seleccionar una factura');
    }
}

window.onload = init;