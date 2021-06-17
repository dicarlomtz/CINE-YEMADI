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
        sessionStorage.setItem("user", data["user"]);
    }
    alert(data["result"]);
}