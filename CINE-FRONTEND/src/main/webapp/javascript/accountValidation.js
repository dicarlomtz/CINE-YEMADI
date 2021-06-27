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

/*
 * Se recolectan los datos del formulario para crear un nuevo usuario
 */
function collectData() {
    var data = new FormData();

    var refName = document.getElementById('name');
    var refId = document.getElementById('identification');
    var refPassword = document.getElementById('password');
    var refConfirmPassword = document.getElementById('checkpassword');


    if (refPassword === refConfirmPassword) {

        if (refName.value !== "null" && refId.value !== 'null' && refPassword.value !== 'null') {

            var account = {"name": refName.value, "identification": refId.value, "password": refPassword.value};
            data.append("NewAccount", JSON.stringify(account));

            sendForm(data);
        } else {
            console.error("Faltan agregar datos");
            alert("Faltan agregar datos");
        }

    }
}
/*
*   se envian los datos del nuevo usuario hacia el servlet para crear un nuevo usuario
*/
function sendForm(data) {
    getJSON('NewAccountService', data, manageResponse);
}
/*
* se encarga de manejar la respuesta que recibe del servlet
*/
function manageResponse(data) {
    console.log(data['result']);
    alert(data["result"]);
}
/*
 *   Se recolectan los datos del usuario para funcion de login 
 */
function collectDataLogin() {
    var data = new FormData();

    var refId = document.getElementById('identification');
    var refPassword = document.getElementById('password');

        alert(refId.value + refPassword.value);

        if (refId.value !== 'null' && refPassword.value !== 'null') {

            let account = {"identification": refId.value, "password": refPassword.value};
            data.append("LoginData", JSON.stringify(account));

            sendDataLogin(data);
        } else {
            console.error("Faltan agregar datos");
            alert("Faltan agregar datos");
        }
}
/*
*   se envian los datos del usuario hacia el servlet para verificalos y hacer el login
*/
function sendDataLogin(data) {
    getJSON('LoginService', data, manageResponseLogin);
}
/*
* se encarga de manejar la respuesta que recibe del servlet
* si el usuario es correcto, cambia el contenido del elemento por el id y asigna un enlace segun el rol de la persona que loggea
*/
function manageResponseLogin(data) {
    console.log(data['result']);
    alert(data["result"]);
    
    if(data['result'] === "valid" ){
        var id = data['rol'];
        var account = document.getElementById("account").innerHTML = id;
        account.setAttribute("href", "adminpanel.html");
        
    }else{
        alert("ha ocurrido un error!");
    }
}