package postgres;
import java.sql.*;

//Adapted from http://dmitrykrivenko.blogspot.com/2013/05/deploying-java-web-application-using.html

public class PostgresConnectionProvider {   
   private static final String DRIVER = "org.postgresql.Driver";   
   private static final String URL = "jdbc:postgresql://ec2-54-204-45-196.compute-1.amazonaws.com:5432/dfrlm2kpdg4ikg?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";  
   private static final String USERNAME = "hxeihycfldbghe";   
   private static final String PASSWORD = "LcOp0WLhEYpuh8rZuRPVVxaIo9";
   
   public Connection getConnection() throws SQLException {   
    try {   
     Class.forName(DRIVER);   
    } catch (ClassNotFoundException ex) {   
     System.out.println("Where is your PostgreSQL JDBC Driver? "   
       + "Include in your library path!");   
     return null;   
    }   
    Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);   
    return conn;   
   }
   
   public static void main (String [] args){
     
     
   }
}
  