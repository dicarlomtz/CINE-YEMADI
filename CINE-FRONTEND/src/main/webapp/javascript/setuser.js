/*
 Esta funcion setea el id del usuario en lugar del boton de login y cambia su enlace segun el rol del que loggea
 */
function setUser() {
    let user = sessionStorage.getItem("user");
    if (user) {
        user = JSON.parse(user);
        var accountElement = document.getElementById("account"); //se refiere al boton de hacer login
        accountElement.innerHTML = "";
        accountElement.appendChild(document.createTextNode(user["id"])); //cambia el texto por account, deberia decir el id del que loggueo

        if (user["rol"]) {
            accountElement.setAttribute("href", "adminpanel.html"); //cambia el href a admin
        } else {
            accountElement.setAttribute("href", "clientpanel.html"); //cambia el href a clente

        }
    }
}
