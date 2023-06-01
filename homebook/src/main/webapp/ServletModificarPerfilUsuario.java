import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

/**
 * <h1> Servler para modificar Perfil de usuario </h1>
 * @author Almudena Planchuelo Marquez
 * @version 2.0
 * @param Se hace una consulta a la base de datos para modificar los datos del usuario
 */
public class ServletModificarPerfilUsuario extends HttpServlet {
  private PerfilUsuarioDao PerfilUsuarioDao;

  /**
   *@return true si los datos se han modificado correctamente, false en caso contrario
   */
  public void init() throws ServletException {
	  PerfilUsuarioDao = new PerfilUsuarioDao();
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    BufferedReader reader = request.getReader();
    DatosUsuario DatosUsuario = new Gson().fromJson(reader, DatosUsuario.class);
    reader.close();

    boolean exito = PerfilUsuarioDao.actualizarDatos(DatosUsuario);

    if (exito) {
      response.setStatus(HttpServletResponse.SC_OK);
      response.getWriter().write("Los datos del usuario se han actualizado correctamente.");
    } else {
      response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      response.getWriter().write("Error al actualizar los datos del usuario.");
    }
  }
}
