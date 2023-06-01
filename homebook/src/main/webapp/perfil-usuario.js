// Obtener los elementos de la tabla y el formulario
var tablaDatos = document.getElementById("tablaDatos");
var formModificarDatos = document.getElementById("formularioModificarDatos");

// Cargar los datos del usuario al cargar la página
window.addEventListener("load", function() {
  cargarDatosUsuario();
});

// Función para cargar los datos del usuario
function cargarDatosUsuario() {
  // Realizar una solicitud AJAX para obtener los datos del usuario
  var xhr = new XMLHttpRequest();
  xhr.open("GET", "obtener_datos_usuario", true);
  xhr.onreadystatechange = function() {
    if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
      // Procesar la respuesta del servidor
      var usuario = JSON.parse(xhr.responseText);
      mostrarDatosUsuario(usuario);
    }
  };
  xhr.send();
}

// Función para mostrar los datos del usuario en la tabla
function mostrarDatosUsuario(usuario) {
  var html = "";
  html += "<tr>";
  html += "<td>" + usuario.username + "</td>";
  html += "<td>" + usuario.correo + "</td>";
  html += "<td>" + usuario.biografia + "</td>";
  html += "</tr>";
  tablaDatos.innerHTML = html;

  // Llenar los campos del formulario con los datos actuales
  document.getElementById("nombre").value = usuario.username;
  document.getElementById("correo").value = usuario.correo;
  document.getElementById("biografia").value = usuario.biografia;
}

// Manejar la modificación de datos
formModificarDatos.addEventListener("submit", function(event) {
  event.preventDefault(); // Evita que se envíe el formulario por defecto

  // Obtén los valores de los campos
  var username = document.getElementById("username").value;
  var correo = document.getElementById("correo").value;
  var biografia = document.getElementById("biografia").value;

  // Crea un objeto con los datos del usuario
  var usuario = {
    nombre: username,
    apellido: correo,
    correo: biografia
  };

  // Realiza una solicitud AJAX para guardar los cambios
  var xhr = new XMLHttpRequest();
  xhr.open("POST", "guardar_datos_usuario", true);
  xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
  xhr.onreadystatechange = function() {
    if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
      // Procesa la respuesta del servidor si es necesario
      console.log(xhr.responseText);
    }
  };
  xhr.send(JSON.stringify(usuario));
});
