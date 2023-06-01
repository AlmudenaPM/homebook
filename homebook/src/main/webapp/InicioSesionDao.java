import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author Almudena Planchuelo Marquez
 * @version 2.0
 * 
 * Se crea la conexion con la base de datos para el inicio de sesion
 *
 */
public class InicioSesionDao {
  private static final String URL = "jdbc:mysql://localhost:3360/homebook";
  private static final String USERNAME = "root";
  private static final String PASSWORD = "1234";

  /**
	 *@Connection hara una llamada a la base de datos para comprobar si existe el usuario
	 *@param username     el nombre del usuario
	 *@param password la contraseña del usuario
	 *@return true si el inicio de sesion es exitoso, false en caso contrario
	 */
  public boolean verifyCredentials(String username, String password) {
    try {
    	
      Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      String query = "SELECT * FROM usuario WHERE username = ? AND password = ?";
      PreparedStatement statement = conn.prepareStatement(query);
      statement.setString(1, username);
      statement.setString(2, password);
      ResultSet result = statement.executeQuery();

      if (result.next()) {
        conn.close();
        return true; // Las credenciales son válidas
      }

      conn.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return false; // Las credenciales son inválidas
  }
}
