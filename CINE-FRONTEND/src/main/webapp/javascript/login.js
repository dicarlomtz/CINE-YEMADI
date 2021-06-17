function login() {
    let refId = document.getElementById("identification");
    let refPsw = document.getElementById("password");
    if (refId && refPsw) {
        if (refId.value !== "" && refPsw !== "") {
            let data = new FormData();
            let user = {"identification": refId.value, "password": refPsw.value};
            data.append("user", JSON.stringify(user));
            sendForm(data);
        }
    }
}

function sendForm(data) {
    getJSON('LoginService', data, handleResponse);
}

function handleResponse(data) {
    console.log(data["result"]);
    if (data["result"] === "valid") {
        sessionStorage.setItem("user", JSON.stringify(data["user"]));
        //hacer JSON.parse(sessionStorage.getSession("user")) para obtener el JSON del objeto
        sessionStorage.setItem("user", data["user"]);
        setUser();
    }
    alert(data["result"]);
}
/*
    Esta funcion setea el id del usuario en lugar del boton de login y cambia su enlace segun el rol del que loggea
*/
function setUser() {
    var user = JSON.parse(sessionStorage.getItem("user"));
    var accountElement = document.getElementById("account"); //se refiere al boton de hacer login
    accountElement.innerHTML = user["id"]; //cambia el texto por account, deberia decir el id del que loggueo

    if (user["rol"]) {
        accountElement.href = "adminpanel.html"; //cambia el href a admin
    } else {
        accountElement.href = "clientpanel.html"; //cambia el href a clente

    }
}
