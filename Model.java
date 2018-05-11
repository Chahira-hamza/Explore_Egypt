package Software;
import com.mysql.jdbc.Connection;
import java.sql.Blob;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;
import java.util.Properties;

public class Model {

    private static final String dbClassName = "com.mysql.jdbc.Driver";

    private static final String CONNECTION =
			"jdbc:mysql://127.0.0.1/Software?autoReconnect=true&useSSL=false";
    
    private static final int BUFFER_SIZE = 4096;
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
         protected int getid(String user, String pass)
    {
        int id=-1;
        try{
     
            con.setAutoCommit(false);
            String query = "SELECT user_ID from Software.users where username = '"+ user + "'and password_ = '"+ pass + "'" ;;
            PreparedStatement std =  con.prepareStatement(query);
            ResultSet result = std.executeQuery();
            con.commit();
            
            if (result.next()) {
              id=result.getInt("user_ID");
               }
    
        }
        catch(Exception sqle) {
            System.out.println(sqle.getMessage());
        
         }
        return id;
    }
    //////////////////////////////////////For Admin
  protected boolean addSite(String name, String address, String content,Date Stime,Date Etime,String savepath,String type1,String type2)
    {
        
            try
            {
                con.setAutoCommit(false);
                PreparedStatement stp;
               Time Starttimestamp = new java.sql.Time(Stime.getTime());
               Time endtimestamp = new java.sql.Time(Etime.getTime());
                String query = "Insert into software.sites (Sname, Description, Address,Stime,Etime,photo,type1,type2) Values (?,?,?,?,?,?,?,?)";
                stp =  con.prepareStatement(query);
                stp.setString(1, name);
                stp.setString(2, content);
                stp.setString(3, address);
                stp.setTime(4, Starttimestamp);
                stp.setTime(5, endtimestamp);
                stp.setString(6, savepath);
                stp.setString(7, type1);
                stp.setString(8, type2);
                stp.executeUpdate();
                con.commit();
                return true;
            }
            catch (Exception e)
            {
                return false;
            }
        }
    protected Site viewSite(Site s)
    {
        
        try{
     
            con.setAutoCommit(false);
            String query = "SELECT * from software.sites where SName = '"+ s.name + "'";
            PreparedStatement std =  con.prepareStatement(query);
        ResultSet rs = std.executeQuery();
       
       rs.next();
            
              s.name=rs.getString("SName");
              System.out.println(s.name);
              s.address=rs.getString("Address");
              System.out.println(s.address);
              s.description=rs.getString("Description");
              s.start=rs.getTime("Stime").toString();
              s.close=rs.getTime("Etime").toString();
              s.photo=rs.getString("photo");
              s.type1=rs.getString("type1");
              s.type2=rs.getString("type2");
        
   
    } catch (Exception e) {
        System.out.println(s.name);
        System.out.println(s.address);
        System.out.println(e.getMessage());
    }
      
  return s;
    }
   
 protected boolean updateSite(String name, String address, String content,Date Stime,Date Etime,String savepath,String type1,String type2)
    {
        
            try
            {
                con.setAutoCommit(false);
                PreparedStatement stp;
                Time Starttimestamp = new java.sql.Time(Stime.getTime());
                Time endtimestamp = new java.sql.Time(Etime.getTime());
                String query2 = " UPDATE software.sites SET Sname = '"+ name + "', Description = '"+ content + "', Address='"+ address + "',Stime='"+ Starttimestamp + "',Etime='"+ endtimestamp + "',photo='"+ savepath + "',type1='"+ type1 +"',type2='"+ type2 +"' where SName = '"+ name + "'";

                stp =  con.prepareStatement(query2);
                stp.executeUpdate();
                con.commit();
                return true;
            }
            catch (Exception e)
            {
                return false;
            }
        }    
   protected UserForAdmin viewUser( UserForAdmin t ,int type) throws SQLException
    {
        
        try{
     
            con.setAutoCommit(false);
            String query = "SELECT username,Accepted,user_ID from software.users where user_type = '"+ type + "'";
            PreparedStatement std =  con.prepareStatement(query);
        ResultSet rs = std.executeQuery();
    
        if (rs.next()) {
            
            
               t=new UserForAdmin();
              t.name=rs.getString("username");
              t.accepted=rs.getInt("Accepted");
              t.id=rs.getInt("user_ID");
         
           
               }

    } catch (Exception e) {
        e.printStackTrace();
    }
        return t;
    }
    protected UserForAdmin viewUser2( UserForAdmin t,int id ,int type ) throws SQLException
    {
        
        try{
     
            con.setAutoCommit(false);
            String query = "SELECT username,Accepted,user_ID from software.users where user_type = '"+ type + "' and user_ID > '"+ id + "'" ;
            PreparedStatement std =  con.prepareStatement(query);
        ResultSet rs = std.executeQuery();
    
        if (rs.next()) {
           
            
               t=new UserForAdmin();
              t.name=rs.getString("username");
              t.accepted=rs.getInt("Accepted");
              t.id=rs.getInt("user_ID");
         
           
               }

    } catch (Exception e) {
        e.printStackTrace();
    }
        return t;
    }
   protected Site viewSites() throws SQLException
    {
        Site s=new Site();
        try{
     
            con.setAutoCommit(false);
            String query = "SELECT * FROM software.sites ORDER BY SName LIMIT 1";
            PreparedStatement std =  con.prepareStatement(query);
        ResultSet rs = std.executeQuery();
    
        if (rs.next()) {
           
            
               
              s.name=rs.getString("SName");
              s.address=rs.getString("Address");
              s.description=rs.getString("Description");
              s.start=rs.getTime("Stime").toString();
              s.close=rs.getTime("Etime").toString();
              s.photo=rs.getString("photo");   
               s.type1=rs.getString("type1"); 
                s.type2=rs.getString("type2"); 
               }

    } catch (Exception e) {
        e.printStackTrace();
    }
        return s;
    }
   protected Site viewNextSite(String name) throws SQLException
    {
        Site s=new Site();
        try{
     
            con.setAutoCommit(false);
            String query = "SELECT * FROM software.sites  where SName >'"+ name + "'";
            PreparedStatement std =  con.prepareStatement(query);
        ResultSet rs = std.executeQuery();
    
        if (rs.next()) {
              s.name=rs.getString("SName");
              s.address=rs.getString("Address");
              s.description=rs.getString("Description");
              s.start=rs.getTime("Stime").toString();
              s.close=rs.getTime("Etime").toString();
              s.photo=rs.getString("photo");  
              s.type1=rs.getString("type1"); 
              s.type2=rs.getString("type2");
                      }

    } catch (Exception e) {
        e.printStackTrace();
    }
        return s;
    }
   protected int numberOfTourGuide() throws SQLException
    {
         int count=0;
        try{
    
            con.setAutoCommit(false);
            String query = "SELECT COUNT(*) FROM software.users where user_type = '"+ 1 + "'";
            PreparedStatement std =  con.prepareStatement(query);
        ResultSet rs = std.executeQuery();
       
        if (rs.next()) {
            while(rs.next()){
             
              count=(rs.getInt(1));
                   System.out.println(count);
          }  
               }

    } catch (Exception e) {
        e.printStackTrace();
    }
        return count;
    }
   protected boolean updateuser(int id)
    {
        
            try
            {
                con.setAutoCommit(false);
                PreparedStatement stp;
    
         String query2 = " UPDATE software.users SET Accepted = '"+ 1 + "'where user_ID = '"+ id + "'";
               
                  stp =  con.prepareStatement(query2);
                stp.executeUpdate();
                con.commit();
                return true;
            }
            catch (Exception e)
            {
                return false;
            }
        }
   protected boolean deleteuser(int id)
    {
        
            try
            {
                con.setAutoCommit(false);
                PreparedStatement stp;
    
         String query2 = " delete from software.users where  user_ID = '"+ id + "'";
               
                  stp =  con.prepareStatement(query2);
                stp.executeUpdate();
                con.commit();
                return true;
            }
            catch (Exception e)
            {
                return false;
            }
        }
   ////////////////////////////////////////////////ForAdvertiser///////////////////////
    protected boolean addAdvertisement(int Advertiserid,String name1, String content1,int time1,String name2, String content2,int time2,int canAdd)
    {
        
            try
            {
                con.setAutoCommit(false);
                String query = "Insert into software.addadvertisements (Advertiserid, Title1, Description1, Time1, Title2, Description2, Time2, canadd,pay1,pay2) Values (?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement stp;
                stp =  con.prepareStatement(query);
                Advertiserid+=4;
                stp.setInt(1, Advertiserid);
                stp.setString(2, name1);
                stp.setString(3, content1);
                stp.setInt(4, time1);
                stp.setString(5, name2);
                stp.setString(6, content2);
                stp.setInt(7, time2);
                stp.setInt(8, canAdd);
                stp.setInt(9, 0);
                stp.setInt(10, 0);  
                stp.executeUpdate();
                con.commit();
                return true;
            }
            catch (Exception e)
            {
                 System.out.println("SQLException: " + e.getMessage());
                return false;
            }
        }
     protected int getCanAdd(int id)
    {
        int add=0;
        try{
     
            con.setAutoCommit(false);
            String query = "SELECT canadd from software.addadvertisements where Advertiserid = '"+ id + "'";
            PreparedStatement std =  con.prepareStatement(query);
            ResultSet result = std.executeQuery();
            con.commit();
            
            if (result.next()) {
              add=result.getInt("canadd");
               }
    
        }
        catch(Exception sqle) {
            System.out.println(sqle.getMessage());
        
         }
        return add;
    }
     protected int getpay1(int id)
    {
        int pay=0;
        try{
     
            con.setAutoCommit(false);
            String query = "SELECT pay1 from software.addadvertisements where Advertiserid = '"+ id + "'";
            PreparedStatement std =  con.prepareStatement(query);
            ResultSet result = std.executeQuery();
            con.commit();
            
            if (result.next()) {
              pay=result.getInt("pay1");
               }
    
        }
        catch(Exception sqle) {
            System.out.println(sqle.getMessage());
        
         }
        return pay;
    }
      protected int getpay2(int id)
    {
        int pay=0;
        try{
     
            con.setAutoCommit(false);
            String query = "SELECT pay2 from software.addadvertisements where Advertiserid = '"+ id + "'";
            PreparedStatement std =  con.prepareStatement(query);
            ResultSet result = std.executeQuery();
            con.commit();
            
            if (result.next()) {
              pay=result.getInt("pay2");
               }
    
        }
        catch(Exception sqle) {
            System.out.println(sqle.getMessage());
        
         }
        return pay;
    }
     protected int getTime1(int id)
    {
        int time=0;
        try{
     
            con.setAutoCommit(false);
            String query = "SELECT Time1 from software.addadvertisements where Advertiserid = '"+ id + "'";
            PreparedStatement std =  con.prepareStatement(query);
            ResultSet result = std.executeQuery();
            con.commit();
            
            if (result.next()) {
              time=result.getInt("Time1");
               }
    
        }
        catch(Exception sqle) {
            System.out.println(sqle.getMessage());
        
         }
        return time;
    }
     protected int getTime2(int id)
    {
        int time=0;
        try{
     
            con.setAutoCommit(false);
            String query = "SELECT Time2 from software.addadvertisements where Advertiserid = '"+ id + "'";
            PreparedStatement std =  con.prepareStatement(query);
            ResultSet result = std.executeQuery();
            con.commit();
            
            if (result.next()) {
              time=result.getInt("Time2");
               }
    
        }
        catch(Exception sqle) {
            System.out.println(sqle.getMessage());
        
         }
        return time;
    }
      protected String getTitle1(int id)
    {
        String title = null;
        try{
     
            con.setAutoCommit(false);
            String query = "SELECT Title1 from software.addadvertisements where Advertiserid = '"+ id + "'";
            PreparedStatement std =  con.prepareStatement(query);
            ResultSet result = std.executeQuery();
            con.commit();
            
            if (result.next()) {
              title=result.getString("Title1");
               }
    
        }
        catch(Exception sqle) {
            System.out.println(sqle.getMessage());
        
         }
        return title;
    }
      protected String getTitle2(int id)
    {
        String title = null;
        try{
     
            con.setAutoCommit(false);
            String query = "SELECT Title2 from software.addadvertisements where Advertiserid = '"+ id + "'";
            PreparedStatement std =  con.prepareStatement(query);
            ResultSet result = std.executeQuery();
            con.commit();
            
            if (result.next()) {
              title=result.getString("Title2");
               }
    
        }
        catch(Exception sqle) {
            System.out.println(sqle.getMessage());
        
         }
        return title;
    }
       protected boolean updateAdvertisementForAdv1(int id,int pay)
    {
        
            try
            {
                con.setAutoCommit(false);
                PreparedStatement stp;
    
         String query2 = " UPDATE software.addadvertisements SET pay1 = '"+ pay + "'where Advertiserid = '"+ id + "'";
               
                  stp =  con.prepareStatement(query2);
                stp.executeUpdate();
                con.commit();
                return true;
            }
            catch (Exception e)
            {
                return false;
            }
        }
       protected boolean updateAdvertisementForAdv2(int id,int pay)
    {
        
            try
            {
                con.setAutoCommit(false);
                PreparedStatement stp;
    
         String query2 = " UPDATE software.addadvertisements SET pay2 = '"+ pay + "'where Advertiserid = '"+ id + "'";
               
                  stp =  con.prepareStatement(query2);
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