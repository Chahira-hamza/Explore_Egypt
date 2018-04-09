/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import newpackage.Tourist;
import newpackage.User;

/**
 *
 * @author chahira
 */
@WebServlet(urlPatterns = {"/AddTouristServlet"})
public class AddTouristServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
    String username = request.getParameter("username");
    int mobile = Integer.parseInt(request.getParameter("mobile"));
    String email = request.getParameter("email");
    String country = request.getParameter("country");
    String L1 = request.getParameter("L1");
    String L2 = request.getParameter("L2");
    String L3 = request.getParameter("L3");
    
    Model myModel = new Model();
    
    User u = myModel.getUserDetails(username);
    
    myModel.addTourist(mobile, email, country, L1, L2, L3, u.getUserID());
    
    Tourist t = (Tourist) u;
    t.setMobileNum(mobile);
    t.setCountry(country);
    t.setEmail(email);
    t.setL1(L1);
    t.setL2(L2);
    t.setL3(L3);
    
    request.getSession().setAttribute("tourist", t);
    response.sendRedirect("guest.jsp");
    
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
