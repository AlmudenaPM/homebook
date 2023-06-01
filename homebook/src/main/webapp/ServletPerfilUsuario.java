import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

/**
 * <h1> Servler Perfil de usuario</h1>
 * @author Almudena Planchuelo Marquez
 * @version 2.0
 * @param Se hace una consulta a la base de datos para mostrar los datos del usuario
 */
public class ServletPerfilUsuario extends HttpServlet {
  private PerfilUsuarioDao PerfilUsuarioDao;

  public void init() throws ServletException {
	  PerfilUsuarioDao = new PerfilUsuarioDao();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
	  DatosUsuario DatosUsuario = PerfilUsuarioDao.obtenerUsuario();
    String jsonUsuario = new Gson().toJson(DatosUsuario);

    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(jsonUsuario);
  }
}
