
import com.mysql.jdbc.Connection;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import newpackage.Tourist;
import newpackage.User;

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
        p.put("password","Gam3aStuff*");

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
    
    protected User getUserDetails(String username)
    {
        User user = new User();
        user.setUsername(username);
        
         try{
     
            con.setAutoCommit(false);
            String query = "SELECT user_ID, user_type from users where username = '"+ username + "'";
            PreparedStatement std =  con.prepareStatement(query);
            ResultSet result = std.executeQuery();
            con.commit();
            
            result.next();
            
            user.setUserID(result.getInt(1));
            user.setUserType(result.getInt(2));
            
            return user;
    
        }
        catch(Exception sqle) {
            System.out.println(sqle.getMessage());
            return null;
         }
        
        
    }
    
    
     protected boolean addSite(String name, String address, String content,int Stime,int Etime,InputStream inputStream)
    {
        try
        {
            con.setAutoCommit(false);
            PreparedStatement stp;

            String query = "Insert into sites (Sname, Description, Address,Stime,Etime,photo) Values (?,?,?,?,?,?)";
            stp =  con.prepareStatement(query);
            stp.setString(1, name);
            stp.setString(2, content);
            stp.setString(3, address);
            stp.setInt(4,Stime);
            stp.setInt(5,Etime);
            
            if (inputStream != null) 
            {
                // fetches input stream of the upload file for the blob column
                stp.setBlob(6, inputStream);
            }
            
            stp.executeUpdate();
            con.commit();
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
    
    protected boolean addTourist(int mobile,String email, String country, String L1, String L2, String L3,int ID)
    {
        try
        {
            con.setAutoCommit(false);
            PreparedStatement stp;
            String query = "Insert into tourists (mobile_num, email, country, L1,L2,L3, tourist_ID ) Values (?,?,?,?,?,?,?)";
            stp =  con.prepareStatement(query);
            stp.setInt(1, mobile);
            stp.setString(2, email);
            stp.setString(3, country);
            stp.setString(4, L1);
            stp.setString(5, L2);
            stp.setString(6, L3);
            stp.setInt(7, ID);

            stp.executeUpdate();
            con.commit();
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    protected boolean rateSite(int ID, String siteName, int rate)
    {
        try
        {
            con.setAutoCommit(false);
            PreparedStatement stp;
            String query = "Insert into site_rating (int user_ID, String site_name, int rating) Values (?,?,?)";
            stp =  con.prepareStatement(query);
            stp.setInt(1, ID);
            stp.setString(2, siteName);
            stp.setInt(3, rate);

            stp.executeUpdate();
            con.commit();
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
}

