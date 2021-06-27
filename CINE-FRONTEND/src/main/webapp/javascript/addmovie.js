/*
    Universidad Nacional de Costa Rica
    Escuela de Informática
    EIF209 Programación IV, ciclo I 2021
    Mauricio Gutiérrez Vásquez 118260119
    Adolfo Di Carlo Martínez Martínez 118050228
    Yeikol Villalobos Herrera 702670531
    Proyecto #2, Cine
*/

function init(){
    setUser();
    validateAdmin();
}

function validateForm() {
    var valid = true;
    var refA = document.getElementById("image");
    if (refA.files.length === 0) {
        alert("No ha seleccionado ningúna imagen..");
        valid = false;
    }
    return valid;
}