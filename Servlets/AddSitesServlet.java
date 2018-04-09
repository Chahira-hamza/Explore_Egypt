
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
@WebServlet(urlPatterns = {"/AddSites"})
@MultipartConfig(maxFileSize = 16177215) 

public class AddSitesServlet extends HttpServlet {

    public AddSitesServlet(){
    super();
}
        
  
 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  // TODO Auto-generated method stub
  
    String name = request.getParameter("name");
    String addr = request.getParameter("address");
    String desc = request.getParameter("description");
    String start = request.getParameter("Stime");
    String end = request.getParameter("Etime");
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
   // obtains the upload file part in this multipart request
   /////////Image
 
    InputStream inputStream = null; // input stream of the upload file
    Part filePart = request.getPart("photo");
    if (filePart != null) 
    {
        // prints out some information for debugging
        System.out.println(filePart.getName());
        System.out.println(filePart.getSize());
        System.out.println(filePart.getContentType());

        // obtains input stream of the upload file
        inputStream = filePart.getInputStream();
    }
 
    out.println("shhhh@");
  // validate given input
  if (name.isEmpty() || addr.isEmpty() || desc.isEmpty() || start.isEmpty() || end.isEmpty() ) 
  {
   RequestDispatcher rd = request.getRequestDispatcher("AddSite.jsp");
   out.println("<font color=red>Please fill all the fields</font>");
   rd.include(request, response);
  } 
  else 
  {
      Model m = new Model(); // TODO Auto-generated catch block
      m.addSite(name, addr,desc ,Integer.parseInt(start), Integer.parseInt(end),inputStream);
      
      RequestDispatcher rd = request.getRequestDispatcher("AddSite.jsp");
      rd.forward(request, response);
  }

  }
}
