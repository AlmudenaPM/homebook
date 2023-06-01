import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <h1> Registro de libros </h1>
 * <p></p>
 * @author Almudena Planchuelo Marquez
 * @version 2.0
 * 
 *
 */

@WebServlet("/ServletRegistroLibros")
public class ServletRegistroLibros extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
	  
   	/**
	 * @request obtiene los datos del libro enviados desde el formulario
	 */
	  
    String titulo = request.getParameter("titulo");
    String autor = request.getParameter("autor");
    String genero = request.getParameter("genero");
    String publicacion = request.getParameter("publicacion");
    String editorial = request.getParameter("editorial");
    String idioma = request.getParameter("idioma");
    String ISBN = request.getParameter("isbn");

    /**
     * Inserta los datos del libro en la base de datos
     */
    RegistroLibrosDao RegistroLibrosDao = new RegistroLibrosDao();
    boolean success = RegistroLibrosDao.insertBook(titulo, autor, genero, publicacion, editorial, idioma, isbn);
    
    /**
     * El @response nos devuelve un mensaje de exitus o de error segun si 
     * se haya podido o no registrar el libro
     */
    if (success) {
      response.setContentType("text/plain");
      response.setCharacterEncoding("UTF-8");
      response.getWriter().write("Libro registrado exitosamente");
    } else {
      response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
          "Error al registrar el libro en la base de datos");
    }
  }
}
/**
 * En esta parte dentro del Servelet he hecho la clase DAO del registro de libros
 *
 */
public class RegistroLibrosDao {
  private Connection connection;
  /**
   * Constructor donde se inicializa la la clase Dao para el registro de los libros
   * haciendo la conexion con la base de datos 
   */
  public RegistroLibrosDao() {
    /**
     *  Configurar la conexión a la base de datos para que se inicialice
     *  @url es la ruta de enlace a la base de todos
     *  @user es el usuario principal para acceder a la base de datos
     *  @password es la contraseña que para acceder a la base de datos
     */
    String url = "jdbc:mysql://localhost:3360/homebook";
    String user = "root";
    String password = "1234";

    try {
      Class.forName("com.mysql.jdbc.Driver");
      connection = DriverManager.getConnection(url, user, password);
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
  }
/**
 * Vamos a configurar la llamada que se hace a la hora de registrar el libro 
 * a en la tabla que esta enlazada en la base de datos
 * @statement hara que se vayan añadiendo/actualizando los datos nuevos como nueva 
 * entrada dentro de la base dde datos en la tabla y celda correspondiente
 */
  public boolean insertarLibro(String titulo, String autor, String genero, 
		  Int publicacion, String editorial, String idioma, Int isbn) {
    String sql = "INSERT INTO libros (titulo, autor, genero, publicacion, "
    		+ "editorial, idioma, isbn) VALUES (?, ?, ?, ?, ?, ?, ?)";

    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setString(1, titulo);
      statement.setString(2, autor);
      statement.setString(3, genero);
      statement.setString(4, publicacion);
      statement.setString(5, editorial);
      statement.setString(6, idioma);
      statement.setString(7, isbn);
      int rowsInserted = statement.executeUpdate();

      return rowsInserted > 0;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }
}

