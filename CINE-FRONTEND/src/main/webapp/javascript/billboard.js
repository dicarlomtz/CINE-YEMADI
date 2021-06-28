/*
 Universidad Nacional de Costa Rica
 Escuela de Informática
 EIF209 Programación IV, ciclo I 2021
 Mauricio Gutiérrez Vásquez 118260119
 Adolfo Di Carlo Martínez Martínez 118050228
 Yeikol Villalobos Herrera 702670531
 Proyecto #2, Cine
 */

const ticketPrice = 3000;
let selectedSeats = 0;
let seats = {};
let idSelectMovie = -1;
let funcMovies = {};

function init() {
    retrieveBillboard();
    setUser();
}

function retrieveBillboard() {
    getJSON('BillboardService', {}, updateBillboard);
}

function updateBillboard(data) {
    refBtn = document.getElementById("close");
    if (refBtn) {
        refBtn.addEventListener("click", cleanSeats);
    }
    ref = document.getElementById("billboard");
    if (ref) {

        let movies = data["movie-list"];

        movies.forEach((movie) => {

            let pdiv = document.createElement("DIV");
            pdiv.setAttribute("class", "movie-box dropdown");
            pdiv.setAttribute("id", movie["id"]);

           // pdiv.addEventListener("mouseover", selectMovie);

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
            addFunctions(movie["id"]);

        });

    }
}

function selectMovie(id) {

    idSelectMovie = id;
}

function addFunctions(id) {
    let ref = document.getElementById(id + "funcs");
    if (ref) {

        fetch(`FunctionBillboardService?id_movie=${id}`)
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
                        funcMovies[f["key"]] = id;
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
    selectMovie(funcMovies[this.id]);
    var modal = document.getElementById("myModal");
    loadSeats(this.id);
    modal.style.display = "block";
}

function closeSeatSelector() {

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
        let img = document.getElementById("abtmovieImg");
        let date = document.getElementById("abtmovieDate");
        if (img && date) {
            img.setAttribute("src", `MovieImageService?id_movie=${idSelectMovie}`);
            date.innerHTML = "";
            date.appendChild(document.createTextNode(data["function-seat-list"][0]["date"]));
        }
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
            updateSelectedCount();
        } else {
            alert("Ocuppied");
        }
    }
}

function cleanSeats() {
    selectedSeats = 0;
    seats = {};
    setCookie("selectedSeats", 0, 1);
    setCookie("seats", {}, 1);

    updateSelectedCount();
    closeSeatSelector();
}

function validatePurchase() {
    if (selectedSeats <= 0) {
        window.location.replace("index.html");
        alert("No seats selected");
    } else {
        setCookie("selectedSeats", selectedSeats, 1);
        setCookie("seats", JSON.stringify(seats), 1);
        window.location.replace("confirm.html");
    }
}

