/**
 *
/* Alterna entre agregar y eliminar la clase "responsiva" a topnav cuando el usuario hace clic en el ícono */
function myFunction() {
    var x = document.getElementById("myTopnav");
    if (x.className === "topnav") {
        x.className += " responsive";
    } else {
        x.className = "topnav";
    }
}