function init()
{
    fetch('InvoiceListService').then(function(resultado) {
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

function cargarTickets(datos, idInvoice)
{
    var ref = document.getElementById('ticketList');
    
    if(ref)
    {
        datos.forEach((fila) => {
            
            if(idInvoice === fila['invoice']['id'])
            {            
                var nuevaFila = ref.insertRow(-1);
                var nuevaCelda;
            
                nuevaCelda = nuevaFila.insertCell(-1);
                nuevaCelda.textContent = fila['invoice'];
                
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
            }
        });
    }
}

window.onload = init;