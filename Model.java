package Software;
import com.mysql.jdbc.Connection;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

/**
 *
 * @author chahira
 */
public class Model {

    private static final String dbClassName = "com.mysql.jdbc.Driver";

    private static final String CONNECTION =
			"jdbc:mysql://127.0.0.1/Software?autoReconnect=true&useSSL=false";
    
    
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
  protected boolean addSite(String name, String address, String content,int Stime,int Etime,InputStream inputStream)
    {
        
            try
            {
                con.setAutoCommit(false);
                PreparedStatement stp;

                String query = "Insert into software.sites (Sname, Description, Address,Stime,Etime,photo) Values (?,?,?,?,?,?)";
                stp =  con.prepareStatement(query);
                stp.setString(1, name);
                stp.setString(2, content);
                stp.setString(3, address);
                stp.setInt(4,Stime);
                stp.setInt(5,Etime);
                 if (inputStream != null) {
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