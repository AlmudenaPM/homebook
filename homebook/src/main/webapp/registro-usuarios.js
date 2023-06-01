window.onload = function() {
  var form = document.getElementById('FormularioRegistroUsuario');
  form.addEventListener('submit', function(event) {
    event.preventDefault();
    registroUsuario();
  });
}

function registroUsuario() {
  var username = document.getElementById('username').value;
  var email = document.getElementById('email').value;
  var password = document.getElementById('password').value;

  // Enviar los datos al servlet
  var xhr = new XMLHttpRequest();
  xhr.open('POST', 'ServletRegistroUsuario', true);
  xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
  xhr.onreadystatechange = function() {
    if (xhr.readyState === 4 && xhr.status === 200) {
      alert(xhr.responseText);
    }
  };
  xhr.send('username=' + encodeURIComponent(username) + '&email=' + encodeURIComponent(email) + '&password=' + encodeURIComponent(password));
}
