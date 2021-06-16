function init() {
    retrieveBillboard();
}

function retrieveBillboard() {
    getJSON('BillboardService', {}, updateBillboard);
}

function updateBillboard(data) {
    ref = document.getElementById("billboard");
    if (ref) {

        let movies = data["movie-list"];

        movies.forEach((movie) => {

            let pdiv = document.createElement("DIV");
            pdiv.setAttribute("class", "movie-box dropdown");
            pdiv.setAttribute("id", movie["id"]);

            pdiv.addEventListener("mouseover", addFunctions);

            let mdiv = document.createElement("DIV");
            mdiv.setAttribute("class", "movie-card");

            let img = document.createElement("IMG");
            let src = `MovieImageService?id_movie=${movie["id"]}`;
            img.setAttribute("src", src);

            mdiv.appendChild(img);

            let fdiv = document.createElement("DIV");
            fdiv.setAttribute("class", "dropdown-schedules");
            fdiv.setAttribute("id", movie["id"] + "funcs");

            //addFunctions(fdiv, movie["id"]);

            pdiv.appendChild(mdiv);
            pdiv.appendChild(fdiv);

            ref.appendChild(pdiv);

        });

    }
}

function addFunctions() {
    let ref = document.getElementById(this.id + "funcs");
    if (ref && this) {

        fetch(`FunctionBillboardService?id_movie=${this.id}`)
                .then(result => {
                    if (result.status === 200) {
                        return result.json();
                    } else {
                        console.error(`HTTP error: ${result.status}`);
                    }
                })
                .then((data) => {
                    ref.innerHTML = "";
                    let functions = data["function-list"];
                    functions.forEach((f) => {
                        fdiv = document.createElement("DIV");
                        fdiv.setAttribute("class", "desc");

                        enF = document.createElement("A");
                        enF.appendChild(document.createTextNode(f["date"]));
                        enF.addEventListener("click", ventanaSecundaria);

                        fdiv.appendChild(enF);
                        ref.appendChild(fdiv);
                    });
                }
                ).catch(errCode => {
            console.error(errCode);
        });

    }
}