function validateForm() {
    var valid = true;
    var refA = document.getElementById("image");
    if (refA.files.length === 0) {
        alert("No ha seleccionado ningúna imagen..");
        valid = false;
    }
    return valid;
}