function login() {
    let refId = document.getElementById("identification");
    let refPsw = document.getElementById("password");

    if (refId && refPsw) {
        if (refId.value !== "null" && refPsw !== "null") {
            let data = new FormData();
            let user = {"identification": refId.value, "password": refPsq.value};
            data.append("user", JSON.stringify(user));
            sendForm(data);
        }
    }
}

function sendForm(data) {
    getJson('LoginService', data, handleResponse);
}

function handleResponse(data) {
    console.log(data["result"]);
    alert(data["resuelt"]);
}