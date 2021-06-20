let purchaseData;

function purchase() {
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
}



function fillForm() {
    setUser();
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
    button.setAttribute("type", "submit");
    button.setAttribute("class", "btn-submit");
    button.appendChild(document.createTextNode("Confirm"));
    form.appendChild(button);
}

function confirmBuy() {
    doPurchase();
}

function doPurchase() {
    getJSON("PurchaseService", purchaseData, invoice);
    
}

function invoice(data) {
    setCookie("selectedSeats", -1, 1);
    setCookie("seats", null, 1);
    purchaseData = null;
    console.log(data);
    alert("llegó");
    //hacer la factura en pdf
}