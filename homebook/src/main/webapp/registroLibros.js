document.getElementById('FormularioRegistroLibro').addEventListener('submit', function(event) {
  event.preventDefault(); // Evita el envío del formulario

  /* Se obtienen los valores del formulario para poder rellenarlos 
     con la informacion del libro*/
  var titulo = document.getElementById('titulo').value;
  var autor = document.getElementById('autor').value;
  var genero = document.getElementById('genero').value;
  var publicacion = document.getElementById('publicacion').value;
  var editorial = document.getElementById('editorial').value;
  var idioma = document.getElementById('idioma').value;
  var isbn = document.getElementById('isbn').value;
  
  // Se crea un objeto var para los libros
  var libro = {
    titulo: titulo,
    autor: autor,
    genero: genero,
    publicacion: publicacion,
    editorial: editorial,
    idioma: idioma,
    isbn: isbn
  };

  // Realiza una solicitud POST al servidor
  fetch('ServletRegistroLibros', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(libro)
  })
  .then(function(response) {
    if (response.ok) {
      alert('Libro registrado exitosamente');
      // Restablece o pone a cero los campos del formulario para que se vuelvan a rellenar
      document.getElementById('titulo').value = '';
      document.getElementById('autor').value = '';
      document.getElementById('genero').value = '';
      document.getElementById('publicacion').value = '';
      document.getElementById('editorial').value = '';
      document.getElementById('idioma').value = '';
      document.getElementById('isbn').value = '';
    } else {
      throw new Error('Error al registrar el libro');
    }
  })
  .catch(function(error) {
    console.error(error);
    alert('Error al registrar el libro');
  });
});
