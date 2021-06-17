const container = document.querySelector('.container');
const seats = document.querySelectorAll('.row .seat:not(.occupied)');
const count = document.getElementById('count');
const total = document.getElementById('total');
const movieSelect = document.getElementById('movie');

let ticketPrice = +movieSelect.value;

function init() {
    retrieveBillboard();
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
    const selectedSeats = document.querySelectorAll('.row .seat.selected');
    const selectedSeatsCount = selectedSeats.length;
    count.innerText = selectedSeatsCount;
    total.innerText = selectedSeatsCount * ticketPrice;
}

//Movie Select Event
movieSelect.addEventListener('change', e => {
    ticketPrice = +e.target.value;
    updateSelectedCount();
});

//Seat click event
container.addEventListener('click', e => {
    if (e.target.classList.contains('seat') &&
            !e.target.classList.contains('occupied')) {
        e.target.classList.toggle('selected');
    }
    updateSelectedCount();
});

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
            row.appendChild(seat);
            
        });

    }
}
