import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * <h1>Datos de Usuario</h1>
 * <p>En este apartado podras visualizar tus datos de usuario y modificarlos</p>
 * @author Almudena Planchuelo Marquez
 * @version 2.0
 *
 */

public class PerfilUsuarioDao {
  public DatosUsuario obtenerUsuario() {
	  DatosUsuario DatosUsuario = null;

	 /**
	  * @param Se hara una consulta a la base de datos para que nos muestre los daros de perfil del usuario
	  */
    try (Connection conn = DBConnectionDatosUsuario.obtenerConexion();
        PreparedStatement stmt = conn.prepareStatement("SELECT username, correo, biografia FROM usuario WHERE id = ?")) {
      stmt.setInt(1, 1);

      ResultSet rs = stmt.executeQuery();

      if (rs.next()) {
    	DatosUsuario = new DatosUsuario();
    	DatosUsuario.setUsername(rs.getString("username"));
    	DatosUsuario.setCorreo(rs.getString("correo"));
    	DatosUsuario.setBiografia(rs.getString("biografia"));
      }

      rs.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return usuario;
  }

  public boolean actualizarDatos(DatosUsuario DatosUsuario) {
		 /**
		  * @param Se actualizara la base de datos con lo que se ha modificado y se mostrara los datos actualizados
		  */
    try (Connection conn = DBConnectionDatosUsuario.obtenerConexion();
        PreparedStatement stmt = conn.prepareStatement("UPDATE usuario SET username=?, correo=?, biografia=? WHERE id=?")) {
      stmt.setString(1, DatosUsuario.getUsername());
      stmt.setString(2, DatosUsuario.getCorreo());
      stmt.setString(3, DatosUsuario.getBiografia());
      stmt.setInt(4, 1);

      int filasActualizadas = stmt.executeUpdate();
      return filasActualizadas > 0;
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return false;
  }
}
