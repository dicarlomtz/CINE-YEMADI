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
        registerElement.remove();
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

            //redirigir pagina de error
        }
    } else {
        //Redirigir pagina de error
    }
}

function validateCustomer() {
    let user = sessionStorage.getItem("user");
    if (user) {
        user = JSON.parse(user);

        if (user["rol"]) {

            //redirigir pagina de error
        }
    } else {
        //Redirigir pagina de error
    }
}
