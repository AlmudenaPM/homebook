import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <h1> Parte Servlet del registro de usuarios</h1>
 * @author Almudena Planchuelo Marquez
 * @version 2.0
 *
 */

@WebServlet("/ServletRegistroUsuario")
public class ServletRegistroUsuario extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  /**
   * @IOException hace que se le devuelvan los parametros pedidos a la base de datos para indicar si se ha registrado o no
   * Verifica en la base de datos que no esten los mismos datos ya registrados
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String username = request.getParameter("username");
    String email = request.getParameter("email");
    String password = request.getParameter("password");

    UsuarioDAO UsuarioDAO = new UsuarioDAO();
    boolean success = UsuarioDAO.registroUsuario(username, email, password);

    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    if (success) {
      out.println("Registro exitoso");
    } else {
      out.println("Error al registrar");
    }
    out.close();
  }
}
