/*
 Nav bar
 
 */
let toggleButton = document.getElementsByClassName('toggle-button')[0];
let navbarLinks = document.getElementsByClassName('navbar-links')[0];

if (toogleButton && navbarLinks) {
    toggleButton.addEventListener('click', () => {
        navbarLinks.classList.toggle('active');
    })

}
