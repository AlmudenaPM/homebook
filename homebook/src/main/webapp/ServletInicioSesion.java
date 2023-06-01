import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author Almudena Planchuelo Marquez
 * @version 2.0
 * Instancia de la clase InicioSesionDao para acceder a la base de datos
 */
public class ServletInicioSesion extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private InicioSesionDao InicioSesionDao; 

  public void init() {
	  InicioSesionDao = new InicioSesionDao();
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String username = request.getParameter("username");
    String password = request.getParameter("password");

    boolean CredencialesValidas = InicioSesionDao.registroUsuario(username, password);

    response.setContentType("application/json");
    PrintWriter out = response.getWriter();
    out.println("{ \"success\": " + CredencialesValidas + ", \"message\": \"Credenciales inválidas\" }");
    out.close();
  }
}
