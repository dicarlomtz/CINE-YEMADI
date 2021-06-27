/*
    Universidad Nacional de Costa Rica
    Escuela de Informática
    EIF209 Programación IV, ciclo I 2021
    Mauricio Gutiérrez Vásquez 118260119
    Adolfo Di Carlo Martínez Martínez 118050228
    Yeikol Villalobos Herrera 702670531
    Proyecto #2, Cine
*/

function init() {
    setUser();
    validateAdmin();
    retrieveCinemas();
    retrieveMovies();
    retrieveFunctions();
    roomMenuInit();
}

function retrieveMovies() {
    getJSON('MovieService', {}, updateMenuMovies);
}

function retrieveCinemas() {
    getJSON('CinemaService', {}, updateMenuCinema);
}

function retrieveFunctions() {
    getJSON('FunctionService', {}, updateTable);
}

function updateTable(data) {
    let refTbody = document.getElementById("bodyFunctionsT");

    if (refTbody) {
        let functions = data['function-list'];

        functions.forEach((fun) => {
            
            let cinema = document.createElement('P');
            cinema.appendChild(document.createTextNode(fun['cinema']['name']));
            
            let roomP = document.createElement('P');
            roomP.appendChild(document.createTextNode(fun['room']['number']));
            
            let date = document.createElement('P');
            date.appendChild(document.createTextNode(fun['date']));
            
            let movie = document.createElement('P');
            movie.appendChild(document.createTextNode(fun['movie']['title']));

            let newRow = refTbody.insertRow(-1);
            let newCell;
            
            newCell = newRow.insertCell(-1);
            newCell.appendChild(cinema);
            
            newCell = newRow.insertCell(-1);
            newCell.appendChild(roomP);
            
            newCell = newRow.insertCell(-1);
            newCell.appendChild(date);
            
            newCell = newRow.insertCell(-1);
            newCell.appendChild(movie);
        });
    }
}

function updateMenuCinema(data) {
    let refMenu = document.getElementById('menuCinema');

    if (refMenu) {
        refMenu.options.length = 0;

        {
            let opt = document.createElement('OPTION');
            opt.setAttribute("value", "null");
            opt.appendChild(document.createTextNode("(Seleccione el cine)"));
            refMenu.appendChild(opt);
        }

        let cinemas = data['cinema-list'];

        cinemas.forEach((cinema) => {
            let opt = document.createElement('OPTION');
            opt.setAttribute('value', cinema.id);
            opt.appendChild(document.createTextNode(cinema.name));
            refMenu.appendChild(opt);
        });

        refMenu.selectedIndex = 0;
    }
}

function updateMenuMovies(data) {
    let refMenu = document.getElementById("menuMovie");

    if (refMenu) {
        refMenu.options.length = 0;

        {
            let opt = document.createElement('OPTION');
            opt.setAttribute("value", "null");
            opt.appendChild(document.createTextNode("(Seleccione la pelicula)"));
            refMenu.appendChild(opt);
        }

        let movies = data['movie-list'];

        movies.forEach((movie) => {
            let opt = document.createElement('OPTION');
            opt.setAttribute('value', movie.id);
            opt.appendChild(document.createTextNode(movie.title));
            refMenu.appendChild(opt);
        });

        refMenu.selectedIndex = 0;
    }
}

function retrieveCinemaRooms() {
    let refMenu = document.getElementById("menuCinema");
    if (refMenu) {
        if (refMenu.value !== "null" && refMenu.value !== "") {
            let data = new FormData();
            let cinema = {"id": refMenu.value};
            data.append("cinema", JSON.stringify(cinema));
            getJSON('CinemaRoomService', data, updateMenuRooms);
        }
    }
}

function roomMenuInit() {

    let refMenu = document.getElementById("menuRoom");
    if (refMenu) {
        let opt = document.createElement('OPTION');
        opt.setAttribute("value", "null");
        opt.appendChild(document.createTextNode("(Seleccione la sala)"));
        refMenu.appendChild(opt);
    }
}

function updateMenuRooms(data) {
    let refMenu = document.getElementById("menuRoom");

    if (refMenu) {
        refMenu.options.length = 0;

        let rooms = data['room-list'];

        rooms.forEach((room) => {
            let opt = document.createElement('OPTION');
            opt.setAttribute('value', room.number);
            opt.appendChild(document.createTextNode(room.number));
            refMenu.appendChild(opt);
        });
    }
}

function collectFormData() {
    let data = new FormData();

    let refMenuCinema = document.getElementById('menuCinema');
    let refMenuRoom = document.getElementById('menuRoom');
    let refMenuMovie = document.getElementById('menuMovie');
    let refDate = document.getElementById('date');
    let refTime = document.getElementById('time');

    if (refMenuCinema && refMenuRoom && refMenuMovie && refDate && refTime) {

        if (refMenuCinema.value !== "null" && refMenuRoom.value !== "null" && refMenuMovie.value !== "null" && refDate !== "" && refTime !== "") {

            let screening = {"cinema": refMenuCinema.value, "room": refMenuRoom.value, "date": refDate.value + " " + refTime.value, "movie": refMenuMovie.value};
            data.append("screening", JSON.stringify(screening));

            sendForm(data);

        } else {
            console.error("Faltan agregar datos");
            alert("Faltan agregar datos");
        }

    }
}

function sendForm(data) {
    getJSON('ScheduleMovieService', data, manageResponse);
}

function manageResponse(data) {
    console.log(data['result']);
    alert(data["result"]);
}
