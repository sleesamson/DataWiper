package com.datawiper.commands;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.bson.types.ObjectId;
import org.codehaus.jackson.map.ObjectMapper;

import postgres.PostgresConnectionProvider;

import com.datawiper.models.Handset;
import com.datawiper.models.OperatingSystem;
import com.datawiper.mongo.ConnectionProvider;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;
import com.mongodb.util.JSON;

public class CreateHandsetCommand {
    ObjectMapper mapper = new ObjectMapper();
    Statement stmt = null;
    
    ConnectionProvider conn = new ConnectionProvider();

    public boolean execute(Handset hs) {
      String hsJSON = null;

      try {
        hsJSON = mapper.writeValueAsString(hs);
      } catch (Exception e) {
        e.printStackTrace();
      }
      
      PostgresConnectionProvider conn = new PostgresConnectionProvider();
      Connection connection = null;   
      try {   
       connection = conn.getConnection();   
      } catch (SQLException ex) {   
       System.out.println("Connection Failed! Check output console");
       ex.printStackTrace();
      }   
      if (connection != null) {   
       System.out.println("You made it, take control your database now!");   
      } else {   
       System.out.println("Failed to make connection!");   
      }
      
      try{
        stmt = connection.createStatement();
        String sql = String.format("INSERT INTO pending (data) VALUES ('%s');", hsJSON);
        stmt.executeUpdate(sql);
        stmt.close();
        connection.close();
     } catch (Exception e) {
        System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        System.exit(0);
     }
      
      return true;
      
     }
    
  
  public static void main (String [] args)
  {
    CreateHandsetCommand hs = new CreateHandsetCommand();
    hs.execute(null);
  }
}