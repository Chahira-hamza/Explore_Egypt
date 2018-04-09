
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import newpackage.User;

/**
 *
 * @author chahira
 */
@WebServlet(urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        String username, password;

        username = request.getParameter("uname");
        password = request.getParameter("psw");
        
        Model myModel = new Model();
        
        boolean result = myModel.authenticate(username, password);
        
        if (result)
        {
            User user = myModel.getUserDetails(username);
            
            if (user.getUserType() == 0)
            {
                // admin
                request.getSession().setAttribute("user", user);
                response.sendRedirect("AddSite.jsp");
            }
            else if (user.getUserType() == 1)
            {
                // guest
                request.getSession().setAttribute("user", user);
                response.sendRedirect("guest.jsp");
            }
        }
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
