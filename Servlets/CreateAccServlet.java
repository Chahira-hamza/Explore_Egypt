
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
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
@WebServlet(urlPatterns = {"/CreateAccServlet"})
public class CreateAccServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String username, password, passwordre;
        
        username = request.getParameter("uname");
        password = request.getParameter("psw");
        passwordre = request.getParameter("pswre");
        
        PrintWriter out = response.getWriter();
         
       
       
        Model myModel = new Model();
        
        boolean result = myModel.createAccount(username, password, passwordre);
        
        if (result)
        {
            User user = myModel.getUserDetails(username);
            
            if (user.getUserType() == 1)
            {
                // guest
                request.getSession().setAttribute("user", user);
                response.sendRedirect("guest.jsp");
            }
        }
        else
        {
            RequestDispatcher rd = request.getRequestDispatcher("homepage.jsp");
            out.println("<font color=red> Username Taken or incorecct password </font>");
            rd.include(request, response);
        }
        
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
