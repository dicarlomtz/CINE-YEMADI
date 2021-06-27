/*
 Universidad Nacional de Costa Rica
 Escuela de Informática
 EIF209 Programación IV, ciclo I 2021
 Mauricio Gutiérrez Vásquez 118260119
 Adolfo Di Carlo Martínez Martínez 118050228
 Yeikol Villalobos Herrera 702670531
 Proyecto #2, Cine
 */

/*
 Esta funcion setea el id del usuario en lugar del boton de login y cambia su enlace segun el rol del que loggea
 */
function setUser() {
    let user = sessionStorage.getItem("user");
    if (user) {
        user = JSON.parse(user);
        let accountElement = document.getElementById("account"); //se refiere al boton de hacer login
        let registerElement = document.getElementById("register");
        accountElement.innerHTML = "";
        registerElement.innerHTML = "Sign off";
        registerElement.addEventListener("click", removeUser);
        accountElement.appendChild(document.createTextNode(user["id"])); //cambia el texto por account, deberia decir el id del que loggueo

        if (user["rol"]) {
            accountElement.setAttribute("href", "adminpanel.html"); //cambia el href a admin
        } else {
            accountElement.setAttribute("href", "clientpanel.html"); //cambia el href a clente
        }
    }
}

function validateAdmin() {
    let user = sessionStorage.getItem("user");
    if (user) {
        user = JSON.parse(user);

        if (!user["rol"]) {


            window.location.replace("index.html");
            alert("No tiene permiso para acceder");
            //redirigir pagina de error
        }
    } else {
        window.location.replace("index.html");
        alert("No tiene permiso para acceder");
        //Redirigir pagina de error
    }
}

function validateCustomer() {
    let user = sessionStorage.getItem("user");
    if (user) {
        user = JSON.parse(user);

        if (user["rol"]) {

            window.location.replace("index.html");
            alert("No tiene permiso para acceder");
            //redirigir pagina de error
        }
    } else {
        window.location.replace("index.html");
        alert("No tiene permiso para acceder");
        //Redirigir pagina de error
    }
}

function removeUser() {
    sessionStorage.clear();
    window.location.replace("index.html");
    alert("Sesión cerrada");
}
