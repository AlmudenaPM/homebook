import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Almudena Planchuelo Marquez
 * @version 2.0
 * 
 * 
 * Esta clase proporciona métodos para interactuar con la base de datos de usuarios.
 */

public class UsuarioDao {
  private static final String DB_URL = "jdbc:mysql://localhost:3360/homebook";
  private static final String DB_USERNAME = "root";
  private static final String DB_PASSWORD = "1234";

  /**
   * Registra un nuevo usuario en la base de datos.
   *
   * @param username     el nombre del usuario
   * @param email    el correo electrónico del usuario
   * @param password la contraseña del usuario
   * @return true si el registro es exitoso, false en caso contrario
   */
  public boolean registroUsuario(String username, String email, String password) {
    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
      String query = "INSERT INTO usuario (username, email, password) VALUES (?, ?, ?)";
      PreparedStatement statement = conn.prepareStatement(query);
      statement.setString(1, username);
      statement.setString(2, email);
      statement.setString(3, password);
      int rowsInserted = statement.executeUpdate();
      return rowsInserted > 0;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }
}
