function init() {
    setUser();
}

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
    fetch('LoginService', {
        method: 'POST',
        body: data
    }).then(result => {
        alert("here");
        if (result.status === 200) {
            return result.json();
        } else {
            console.error(`HTTP error: ${result.status}`);
        }
    }).then(
             (re) => {   
        console.log(re["result"]);
        if (re["result"] === "valid") {
            sessionStorage.setItem("user", JSON.stringify(re["user"]));
            //hacer JSON.parse(sessionStorage.getSession("user")) para obtener el JSON del objeto
            setUser();
            window.location.replace("index.html");
        }
        alert(re["result"]);
    }
    ).catch(errCode => {
        console.error(errCode);
    });
}

