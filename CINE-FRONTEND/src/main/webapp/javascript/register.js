function getRegisterData() {

    let refId = document.getElementById("identification");
    let refSrnms = document.getElementById("surnames");
    let refNm = document.getElementById("name");
    let refTel = document.getElementById("telephone");
    let refCrd = document.getElementById("card");
    let refPsw = document.getElementById("password");

    if (refId && refSrnms && refNm && refTel && refCrd) {

        if (refId.value !== "" && refSrnms !== "" && refNm !== "" && refPsw !== "") {

            let data = new FormData();
            registerData = {"identification": refId.value, "surnames": refSrnms.value, "name": refNm.value,
                "telephone": refTel.value, "card": refCrd.value, "password": refPsw.value};

            data.append("user", JSON.Stringify(registerData));
            
            sendForm(data);
        }

    }
}

function sendForm(data) {
    getJSON('SignupService', data, handleResponse);
}

function handleResponse(data) {
    console.log(data["result"]);
}