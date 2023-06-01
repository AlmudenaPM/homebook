import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * <h1> Se crea la conexion con la base de datos para poder acceder </h1>
 * @author Almudena Planchuelo Marquez
 * @version 2.0
 *
 */
public class DBConnectionDatosUsuario {
  private static final String URL = "jdbc:mysql://localhost:3360/homebook";
  private static final String USUARIO = "root";
  private static final String CONTRASENA = "1234";

  public static Connection obtenerConexion() throws SQLException {
    return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
  }
}
