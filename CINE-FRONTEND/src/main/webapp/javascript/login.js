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
}

function login() {
    let refId = document.getElementById("identification");
    let refPsw = document.getElementById("password");
    if (refId && refPsw) {
        if (refId.value !== null && refPsw !== null) {
            let data = new FormData();
            let user = {"identification": refId.value, "password": refPsw.value};
            data.append("user", JSON.stringify(user));
            sendForm(data);
        } else {
            alert("Faltan agregar datos");
        }
    }
}

function sendForm(data) {
    getJSON('LoginService', data, handleResponse);
    /*fetch('LoginService', {
        method: 'POST',
        body: data
    }).then(result => {
        if (result.status === 200) {
            return result.json();
        } else {
            console.error(`HTTP error: ${result.status}`);
        }
    }).then(
            (re) => {
        if (re["result"] === "valid") {
            sessionStorage.setItem("user", JSON.stringify(re["user"]));
            //hacer JSON.parse(sessionStorage.getSession("user")) para obtener el JSON del objeto
            setUser();
            window.location.replace("index.html");
        }
        alert(re["result"]);
        console.log(re["result"]);
    }
    ).catch(errCode => {
        console.error(errCode);
    });*/
}

function handleResponse(data) {
    if (data["result"] === "valid") {
        sessionStorage.setItem("user", JSON.stringify(data["user"]));
        //hacer JSON.parse(sessionStorage.getSession("user")) para obtener el JSON del objeto
        //setUser();
        window.location.replace("index.html");
    }
    alert(data["result"]);
    console.log(data["result"]);
}

