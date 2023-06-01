document.getElementById("FormularioInicioSesion").addEventListener("submit", function(event) {
  event.preventDefault(); // Evita el envío del formulario por defecto
  var username = document.getElementById("username").value;
  var password = document.getElementById("password").value;

  // Realiza la validación de usuario y contraseña utilizando una petición AJAX
  var xhr = new XMLHttpRequest();
  xhr.open("POST", "ServletInicioSesion", true);
  xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
  xhr.onreadystatechange = function() {
    if (xhr.readyState === 4 && xhr.status === 200) {
      var response = JSON.parse(xhr.responseText);
      if (response.success) {
        window.location.href = "perfil-usuario.html"; // Redirecciona al perfil del usuario si el inicio de sesión es exitoso
      } else {
        alert(response.message); // Muestra un mensaje de error si las credenciales son incorrectas
      }
    }
  };
  xhr.send("username=" + username + "&password=" + password);
});
