// Función para cargar los libros desde el servidor
function cargarLibros() {
  fetch('/libros') // Ruta para obtener los libros desde el servidor
    .then(response => response.json())
    .then(libros => {
      const librosBody = document.getElementById('libros-body');
      librosBody.innerHTML = '';

      libros.forEach(libro => {
        const fila = document.createElement('tr');
        fila.innerHTML = `
          <td>${libro.titulo}</td>
          <td>${libro.autor}</td>
          <td>${libro.genero}</td>
          <td>${libro.publicacion}</td>
          <td>${libro.editorial}</td>
          <td>${libro.idioma}</td>
          <td>${libro.isbn}</td>d
        `;
        librosBody.appendChild(fila);
      });
    })
    .catch(error => {
      console.error('Error al cargar los libros:', error);
    });
}

// Función para ordenar los libros según la columna seleccionada
function ordenar(columna) {
  // Lógica para ordenar los libros en el servidor y volver a cargarlos
}

// Cargar los libros al cargar la página
document.addEventListener('DOMContentLoaded', cargarLibros);
