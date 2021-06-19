function init()
{
    /*fetch('UserHistoryService').then(function(resultado) {
     return resultado.json();
     }).then(function(datos){
     createSelectInvoice(datos['invoice-list']);
     });*/
    retrieveInvoice();
    setUser();
}

function retrieveInvoice() {
    getJSON('UserHistoryService', {}, createSelectInvoice);
}

function createSelectInvoice(datos)
{
    var refSelect = document.getElementById('selectInvoice');

    if (refSelect)
    {
        {
            let opt = document.createElement('OPTION');
            opt.setAttribute("value", "null");
            opt.appendChild(document.createTextNode("(Seleccione la compra)"));
            refSelect.appendChild(opt);
        }

        datos["invoice-list"].forEach(fila => {
            let opt = document.createElement('OPTION');
            opt.setAttribute('value', fila['id']);
            opt.setAttribute("id", fila["id"]);
            opt.appendChild(document.createTextNode(
                    fila['id'] + ' - ' +
                    fila['date'] + ' - ' +
                    fila['customer']['name'] + ' ' + fila['customer']['surnames']));
            refSelect.appendChild(opt);
        });
    }
}

function searchInvoice()
{
    var selectValue = document.getElementById('selectInvoice').value;

    if (selectValue)
    {
        if (selectValue.value !== null) {
            fetch('TicketListService').then(function (resultado) {
                return resultado.json();
            }).then(function (datos) {
                cargarTickets(datos['ticket-list'], selectValue);
            });
        }
    } else
    {
        alert('Debe seleccionar una factura');
    }
}

function cargarTickets(datos, invoice)
{
    var refTable = document.getElementById('ticketList');
    var refFoot = document.getElementById('ticketFoot');
    var precioTotal = 0.0;

    if (refTable && refFoot)
    {
        datos.forEach((fila) => {

            if (invoice == fila['invoice']['id'])
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
        nuevaCelda.setAttribute('colspan', '4');
        
        nuevaCelda = nuevaFila.insertCell(-1);
        nuevaCelda.textContent = 'Total:';
        
        nuevaCelda = nuevaFila.insertCell(-1);
        nuevaCelda.textContent = precioTotal;
    }
}

window.onload = init;