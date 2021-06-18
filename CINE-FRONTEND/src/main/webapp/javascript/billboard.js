const ticketPrice = 3000;
let selectedSeats = 0;
let seats = {};
let purchaseData;

function init() {
    retrieveBillboard();
    setUser();
}

function retrieveBillboard() {
    getJSON('BillboardService', {}, updateBillboard);
}

function updateBillboard(data) {
    ref = document.getElementById("billboard");
    if (ref) {

        let movies = data["movie-list"];

        movies.forEach((movie) => {

            let pdiv = document.createElement("DIV");
            pdiv.setAttribute("class", "movie-box dropdown");
            pdiv.setAttribute("id", movie["id"]);

            pdiv.addEventListener("mouseover", addFunctions);

            let mdiv = document.createElement("DIV");
            mdiv.setAttribute("class", "movie-card");

            let img = document.createElement("IMG");
            let src = `MovieImageService?id_movie=${movie["id"]}`;
            img.setAttribute("src", src);

            mdiv.appendChild(img);

            let fdiv = document.createElement("DIV");
            fdiv.setAttribute("class", "dropdown-schedules");
            fdiv.setAttribute("id", movie["id"] + "funcs");

            pdiv.appendChild(mdiv);
            pdiv.appendChild(fdiv);

            ref.appendChild(pdiv);

        });

    }
}

function addFunctions() {
    let ref = document.getElementById(this.id + "funcs");
    if (ref && this) {

        fetch(`FunctionBillboardService?id_movie=${this.id}`)
                .then(result => {
                    if (result.status === 200) {
                        return result.json();
                    } else {
                        console.error(`HTTP error: ${result.status}`);
                    }
                })
                .then((data) => {
                    ref.innerHTML = "";
                    let functions = data["function-list"];
                    functions.forEach((f) => {
                        fdiv = document.createElement("DIV");
                        fdiv.setAttribute("class", "desc");

                        let enF = document.createElement("A");
                        enF.appendChild(document.createTextNode(f["date"]));
                        enF.setAttribute("id", f["key"]);
                        enF.setAttribute("href", "#");
                        enF.addEventListener("click", seatSelector);

                        fdiv.appendChild(enF);
                        ref.appendChild(fdiv);
                    });
                }
                ).catch(errCode => {
            console.error(errCode);
        });

    }
}


/**CONTROL DE ASIENROS***/



//Update total and count
function updateSelectedCount() {
    document.getElementById("count").innerHTML = selectedSeats;
    document.getElementById("total").innerHTML = selectedSeats * ticketPrice;
}

/**MODAL ASIENTOS***/
function seatSelector() {
    var modal = document.getElementById("myModal");
    loadSeats(this.id);
    modal.style.display = "block";
}
function closeSeatSelector() {
    // When the user clicks on <span> (x), close the modal
    var span = document.getElementsByClassName("close")[0];
    var modal = document.getElementById("myModal");
    span.onclick = function () {
        modal.style.display = "none";
    };
}

function loadSeats(id) {
    let func = {"id": id};
    let data = new FormData();
    data.append("function", JSON.stringify(func));
    getSeatsFunc(data);
}

function getSeatsFunc(data) {
    getJSON('FunctionSeatsService', data, displaySeats);
}

function displaySeats(data) {
    let container = document.getElementById("seats");
    if (container) {
        container.innerHTML = "";
        let functionSeats = data["function-seat-list"];
        let currentRow = "";
        let lastRow = "";
        let row;

        functionSeats.forEach(
                (fs) => {
            currentRow = fs["row"];
            if (currentRow !== lastRow) {

                row = document.createElement("DIV");
                row.setAttribute("id", fs["row"]);
                row.setAttribute("class", "row");

                lastRow = currentRow;
                container.appendChild(row);

            }

            let seat = document.createElement("DIV");
            seat.setAttribute("id", fs["key"]);
            seat.setAttribute("class", "seat");
            seat.setAttribute("value", fs["available"]);
            if (fs["available"]) {
                seat.style.background = "red";
            }
            seat.addEventListener("click", selectSeat);
            row.appendChild(seat);

        });

    }
}

function selectSeat() {
    if (this) {
        if (this.getAttribute("value") !== "true") {
            if (this.getAttribute("value") !== "selected") {
                this.style.background = "blue";
                selectedSeats++;
                seats[this.id] = ticketPrice;
                this.setAttribute("value", "selected");
            } else {
                this.style.background = "#444451";
                this.setAttribute("value", false);
                selectedSeats--;
                delete seats[this.id];
            }
            setCookie("selectedSeats", selectedSeats, 1);
            setCookie("seats", JSON.stringify(seats), 1);
            updateSelectedCount();
        } else {
            alert("Ocuppied");
        }
    }
}

function cleanSeats() {
    selectedSeats = 0;
    seats = {};
    updateSelectedCount();
    closeSeatSelector();
}

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
                user = {"id": id.value, "name": name.value, "surname": surname.value, "registered": false};
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

function validatePurchase() {
    let ss = getCookie("selectedSeats");
    if (parseInt(ss) <= 0) {
        window.location.replace("index.html");
        alert("No seats selected");
    }
}

function fillForm() {
    validatePurchase();
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
    doPurchase(purchaseData);
}

function doPurchase() {
    getJSON("PurchaseService", purchaseData, invoice);
    purchaseData = null;
}

function invoice(data) {
    console.log(data);
    alert("llegó");
    //hacer la factura en pdf
}

