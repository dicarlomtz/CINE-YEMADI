function init() {
    retrieveCinemas();
    retrieveRooms();
}

function retrieveCinemas() {
    getJSON('CinemaService', {}, updateMenu);
}

function retrieveRooms() {
    getJSON('RoomService', {}, updateTable);
}

function updateTable(data) {
    let refTbody = document.getElementById("bodyRoomsT");

    if (refTbody) {
        let rooms = data['room-list'];

        rooms.forEach((room) => {
            
            let cinema = document.createElement('P');
            cinema.appendChild(document.createTextNode(room['cinema']['name']));
            
            let roomP = document.createElement('P');
            roomP.appendChild(document.createTextNode(room['number']));
            
            let capacity = document.createElement('P');
            capacity.appendChild(document.createTextNode(room['capacity']));

            let newRow = refTbody.insertRow(-1);
            let newCell;
            
            newCell = newRow.insertCell(-1);
            newCell.appendChild(cinema);
            
            newCell = newRow.insertCell(-1);
            newCell.appendChild(roomP);
            
            newCell = newRow.insertCell(-1);
            newCell.appendChild(capacity);
        });
    }
}

function updateMenu(data) {
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

function collectFormData() {
    let data = new FormData();

    let refMenu = document.getElementById('menuCinema');
    let refNumber = document.getElementById('number');
    let refCapacity = document.getElementById('capacity');

    if (refMenu && refNumber && refCapacity) {

        if (refMenu.value !== "null" && refNumber.value !== 'null' && refCapacity.value !== 'null') {

            //var room = new Room(refMenu.value, refNumber.value.refCapacity.value);
            let room = {"cinema": refMenu.value, "number": refNumber.value, "capacity": refCapacity.value};
            data.append("room", JSON.stringify(room));

            sendForm(data);
            init();
        } else {
            console.error("Faltan agregar datos");
            alert("Faltan agregar datos");
        }

    }
}

function sendForm(data) {
    getJSON('BuildRoomService', data, manageResponse);
}

function manageResponse(data) {
    console.log(data['result']);
    alert(data["result"]);
}

function Room(cinema, number, capacity) {
    this.cinema = cinema;
    this.number = number;
    this.capacity = capacity;
}