function ventanaSecundaria (){ 
    window.open("eje.html","ventana1","width=500,height=500,scrollbars=NO") 
 } 
 
 
function inite() {
   

    fetch('LoginService').then(function (resultado) {
        return resultado.json();
    }).then(function (datos) {
        console.log(datos);
    });

    console.log("Aplicación inicializada..");
}