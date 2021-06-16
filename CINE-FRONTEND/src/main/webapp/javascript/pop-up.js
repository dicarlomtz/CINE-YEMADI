
function inite() {
    fetch('LoginService').then(function (resultado) {
        return resultado.json();
    }).then(function (datos) {
        console.log(datos);
    });

    console.log("Aplicación inicializada..");
}
/**CONTROL DE ASIENROS***/

 const container = document.querySelector('.container');
 const seats = document.querySelectorAll('.row .seat:not(.occupied)');
 const count = document.getElementById('count');
 const total = document.getElementById('total');
 const movieSelect = document.getElementById('movie');
 
 let ticketPrice = +movieSelect.value;
 
 //Update total and count
 function updateSelectedCount() {
   const selectedSeats = document.querySelectorAll('.row .seat.selected');
   const selectedSeatsCount = selectedSeats.length;
   count.innerText = selectedSeatsCount;
   total.innerText = selectedSeatsCount * ticketPrice;
 }
 
 //Movie Select Event
 movieSelect.addEventListener('change', e => {
   ticketPrice = +e.target.value;
   updateSelectedCount();
 });
 
 //Seat click event
 container.addEventListener('click', e => {
   if (e.target.classList.contains('seat') &&
      !e.target.classList.contains('occupied')) {
     e.target.classList.toggle('selected');
   }
   updateSelectedCount();
 });
    
/**MODAL ASIENTOS***/
function seatSelector(){
var modal = document.getElementById("myModal");
  modal.style.display = "block";
}
function closeSeatSelector(){
     // When the user clicks on <span> (x), close the modal
        var span = document.getElementsByClassName("close")[0];
        var modal = document.getElementById("myModal");
        span.onclick = function() {
          modal.style.display = "none";
        };
} 