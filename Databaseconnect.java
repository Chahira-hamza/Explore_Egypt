import java.sql.*;
import java.util.Properties;

// need to add to the project's properties, libraries, add external jar files, select mysql connector

public class Databaseconnect
{
    // The JDBC Connector Class.
    private static final String dbClassName = "com.mysql.jdbc.Driver";

    private static final String CONNECTION =
                    "jdbc:mysql://127.0.0.1/new_schema?autoReconnect=true&useSSL=false";

    // new_schema should be changed to ur database name
    public static void main(String[] args) throws
    ClassNotFoundException,SQLException
    {
            System.out.println(dbClassName);
            Class.forName(dbClassName);

            // Properties for user and password. Here the user and password are both 'paulr'
            Properties p = new Properties();
            p.put("user","root");
            p.put("password","");

            // Now try to connect
            Connection c = DriverManager.getConnection(CONNECTION,p);
            try {

                    String query = "Select * from users";

                    Statement st = c.createStatement();
                    ResultSet rt = st.executeQuery(query);

                    while (rt.next())
                    {
                            String name = rt.getString(1);

                            String password = rt.getString(2);

                            System.out.println("username = " + name + " password = " + password);

                    }
            }

                catch (Exception e)
                {
                        System.err.println("Got an exception! ");
                        System.err.println(e.getMessage());
                }

                System.out.println("It works !");
                c.close();
        }
}

