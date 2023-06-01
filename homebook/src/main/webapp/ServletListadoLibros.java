import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Almudena Planchuelo Marquez
 * @version 2.0
 * 
 * 
 * Esta clase proporciona métodos para interactuar con la base de datos de libros.
 */

@WebServlet("/ServletListadoLibros")
public class ServletListadoLibros extends HttpServlet {
  private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
  private static final String DB_URL = "jdbc:mysql://localhost:3360/homebook";
  private static final String USER = "root";
  private static final String PASS = "1234";

  /**
   *
   * @param titulo el nombre del libro
   * @param autor  el nombre del autor
   * @param genero tipo de genero literario
   * @param editorial que lo publico
   * @param publicacion año en el que se publico
   * @param idioma en el que esta escrito el libro
   * @param isbn el numero identificador del libro
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("application/json");
    PrintWriter out = response.getWriter();

    Connection conn = null;
    Statement stmt = null;
    try {
      Class.forName(JDBC_DRIVER);
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      stmt = conn.createStatement();
      String sql = "SELECT * FROM libros";
      ResultSet rs = stmt.executeQuery(sql);

      JSONArray librosArray = new JSONArray();
      while (rs.next()) {
        JSONObject libroObj = new JSONObject();
        libroObj.put("titulo", rs.getString("titulo"));
        libroObj.put("autor", rs.getString("autor"));
        libroObj.put("genero", rs.getString("genero"));
        libroObj.put("editorial", rs.getString("editorial"));
        libroObj.put("publicacion", rs.getString("publicacion"));
        libroObj.put("idioma", rs.getString("idioma"));
        libroObj.put("isbn", rs.getString("isbn"));

        librosArray.put(libroObj);
      }

      out.print(librosArray.toString());
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      out.close();
      try {
        if (stmt != null) stmt.close();
        if (conn != null) conn.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
