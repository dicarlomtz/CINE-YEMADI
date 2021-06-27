/*
    Universidad Nacional de Costa Rica
    Escuela de Informática
    EIF209 Programación IV, ciclo I 2021
    Mauricio Gutiérrez Vásquez 118260119
    Adolfo Di Carlo Martínez Martínez 118050228
    Yeikol Villalobos Herrera 702670531
    Proyecto #2, Cine
*/

function init()
{
    retrieveInvoice();
    setUser();
    validateCustomer();
}

function retrieveInvoice() {
    getJSON('UserHistoryService', {}, createSelectInvoice);
}

function retrieveTickets()
{
    getJSON('TicketListService', {}, cargarTickets);
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
    var refSelect = document.getElementById('selectInvoice');

    if (refSelect)
    {
        if (refSelect.value !== 'null')
        {
            retrieveTickets();
        }
        else
        {
            alert('Debe seleccionar una factura');
        }
    }
}

function cargarTickets(datos)
{
    var refSelect = document.getElementById('selectInvoice');
    var refTable = document.getElementById('ticketList');
    var refFoot = document.getElementById('ticketFoot');
    var precioTotal = 0.0;

    if (refTable && refFoot)
    {
        if(refTable.rows.length !== 0 && refFoot.rows.length !== 0)
        {
            for(let i = 0; i < refTable.rows.length; i++)
            {
                refTable.deleteRow(-1);
            }
        
            refTable.deleteRow(-1);
            refFoot.deleteRow(-1);
        }
        
        datos['ticket-list'].forEach((fila) => {

            if(parseInt(refSelect.value) === fila['invoice']['id'])
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
        nuevaCelda.setAttribute('colspan', '5');

        nuevaCelda = nuevaFila.insertCell(-1);
        nuevaCelda.textContent = precioTotal;
    }
}

window.onload = init;