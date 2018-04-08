

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author chahira
 */
@WebServlet(urlPatterns = {"/CreateAccServlet"})
public class CreateAccServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String username, password, passwordre;
        
        username = request.getParameter("uname");
        password = request.getParameter("psw");
        passwordre = request.getParameter("pswre");
       
        Model myModel = new Model();
        
        boolean result = myModel.createAccount(username, password, passwordre);
        
        if (result)
            response.sendRedirect("success.jsp");
        else
            response.sendRedirect("error.jsp");
        
        
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
