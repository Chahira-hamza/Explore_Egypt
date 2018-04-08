
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chahira
 */
public class Model {

    private static final String dbClassName = "com.mysql.jdbc.Driver";

    private static final String CONNECTION =
			"jdbc:mysql://127.0.0.1/new_schema?autoReconnect=true&useSSL=false";
    
    private static Connection con;
    
    public Model() {
        
    try
    {
        Class.forName(dbClassName);

        // Properties for user and password. Here the user and password are both 'paulr'
        Properties p = new Properties();
        p.put("user","root");
        p.put("password","");

        // Now try to connect
        con =  (Connection) DriverManager.getConnection(CONNECTION,p);
    }
    
    catch(Exception e)
    {
        System.out.println(e.getMessage());
    }
    }
    
    
    protected boolean createAccount(String user, String pass1, String pass2)
    {
        if (pass1.equals(pass2))
        {
            try
            {
                con.setAutoCommit(false);
                PreparedStatement stp;

                String query = "Insert into users (username, password_, user_type) Values (?,?,?)";
                stp =  con.prepareStatement(query);
                stp.setString(1, user);
                stp.setString(2, pass1);
                stp.setInt(3,1);
                
                stp.executeUpdate();
                con.commit();
                return true;
            }
            catch (Exception e)
            {
                return false;
            }
        }
        else
            return false;
    }
 
    
    protected boolean authenticate(String user, String pass)
    {
        try{
     
            con.setAutoCommit(false);
            String query = "SELECT password_ from users where username = '"+ user + "'";
            PreparedStatement std =  con.prepareStatement(query);
            ResultSet result = std.executeQuery();
            con.commit();
            
            result.next();
            
            return  (result.getString(1).equals(pass));
    
        }
        catch(Exception sqle) {
            System.out.println(sqle.getMessage());
            return false;
         }
    }
}
