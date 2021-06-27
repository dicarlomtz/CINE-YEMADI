/*
    Universidad Nacional de Costa Rica
    Escuela de Informática
    EIF209 Programación IV, ciclo I 2021
    Mauricio Gutiérrez Vásquez 118260119
    Adolfo Di Carlo Martínez Martínez 118050228
    Yeikol Villalobos Herrera 702670531
    Proyecto #2, Cine
*/

window.jsPDF = window.jspdf.jsPDF;
let purchaseData;

function init(){
    setUser();
    fillForm();
}

function purchase() {
    if (parseInt(getCookie("selectedSeats")) > 0) {
         purchaseData = new FormData();
        let user = sessionStorage.getItem("user");
        if (user) {
            let data = {};
            data["registered"] = true;
            data["user"] = JSON.parse(user);
            user = data;
        } else {
            let name = document.getElementById("name");
            let id = document.getElementById("id");
            let surname = document.getElementById("surname");
            if (name && id && surname) {
                if (name.value !== null && id.value !== null && surname.value !== null) {
                    user = {};
                    user["user"] = {"id": id.value, "name": name.value, "surname": surname.value};
                    user["registered"] = false;
                } else {
                    window.location.replace("index.html");
                    alert("No data entered");
                }
            } else {
                window.location.replace("index.html");
                alert("No data entered");
            }
        }

        let card = document.getElementById("card");
        if (card) {
            if (card.value !== null) {
                user["card"] = card.value;
                user["seats"] = JSON.parse(getCookie("seats"));
                purchaseData.append("user", JSON.stringify(user));
                doPurchase();
            } else {
                window.location.replace("index.html");
                alert("No data entered");

            }
        } else {
            window.location.replace("index.html");
            alert("No data entered");
        }
    } else {
        window.location.replace("index.html");
        alert("No data entered");
    }
}



function fillForm() {
    let form = document.getElementById("conForm");

    if (sessionStorage.getItem("user") === null) {

        let name = document.createElement("INPUT");
        name.setAttribute("type", "text");
        name.setAttribute("id", "name");
        name.setAttribute("placeholder", "Name");
        name.setAttribute("class", "input-style");
        name.setAttribute("required", true);
        form.appendChild(name);

        let surname = document.createElement("INPUT");
        surname.setAttribute("type", "text");
        surname.setAttribute("id", "surname");
        surname.setAttribute("placeholder", "Surname");
        surname.setAttribute("class", "input-style");
        surname.setAttribute("required", true);
        form.appendChild(surname);

        let iden = document.createElement("INPUT");
        iden.setAttribute("type", "number");
        iden.setAttribute("id", "id");
        iden.setAttribute("placeholder", "Identification");
        iden.setAttribute("class", "input-style");
        iden.setAttribute("required", true);
        form.appendChild(iden);

    }

    let card = document.createElement("INPUT");
    card.setAttribute("type", "number");
    card.setAttribute("id", "card");
    card.setAttribute("placeholder", "Card");
    card.setAttribute("class", "input-style");
    card.setAttribute("required", true);
    form.appendChild(card);

    let button = document.createElement("BUTTON");
    button.setAttribute("type", "button");
    button.setAttribute("class", "btn-submit");
    button.appendChild(document.createTextNode("Confirm"));
    button.addEventListener("click", purchase);
    form.appendChild(button);
}

function confirmBuy() {
    doPurchase();
}

function doPurchase() {
    getJSON("PurchaseService", purchaseData, invoice);
}

function invoice(data) {
    setCookie("selectedSeats", 0, 1);
    setCookie("seats", {}, 1);
    purchaseData = null;
    console.log(data);
    if (data["result"] === "valid") {
        toPdfInvoice(data["purchase"]);
    }
}

function toPdfInvoice(data) {

    let row = 10;
    let doc = new jsPDF();
    row += 10;
    doc.text('ID de factura: ' + data["invoice"]["id"], 10, row);
    row += 10;
    doc.text('Fecha: ' + data["invoice"]["date"], 10, row);
    row += 10;
    doc.text('Nombre del cliente: ' + data["invoice"]['customer']['name'] + " " + data["invoice"]['customer']['surnames'], 10, row);
    row += 10;
    doc.text('Tarjeta: ' + data["invoice"]['payment-card']['number'], 10, row);

    data["tickets"].forEach(fila => {
        row += 10;
        doc.text('--------------------------------------------------', 10, row);
        row += 10;
        doc.text('Cine: ' + fila['cinema']['name'], 10, row);
        row += 10;
        doc.text('Sala: ' + fila['room']['number'], 10, row);
        row += 10;
        doc.text('Asiento: ' + fila['seat']['row'] + fila['seat']['position'], 10, row);
        row += 10;
        doc.text('Precio: ' + fila['amount'], 10, row);
        row += 10;
        doc.text('--------------------------------------------------', 10, 10, row);
    });

    doc.save(`invoice_${data["invoice"]["id"]}.pdf`);

    window.location.replace("index.html");
}