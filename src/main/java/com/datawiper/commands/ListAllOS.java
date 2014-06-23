package com.datawiper.commands;

import java.util.ArrayList;

import com.datawiper.models.OperatingSystem;
import com.datawiper.mongo.ConnectionProvider;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

public class ListAllOS {
  public ArrayList<OperatingSystem> execute(){
    ConnectionProvider conn = new ConnectionProvider();
    DBCollection collection = conn.getCollection("operatingsystems");

    DBCursor cursor = collection.find();
    
    ArrayList<OperatingSystem> operatingsystems = new ArrayList<OperatingSystem>();

    GetOperatingSystemCommand getOS = new GetOperatingSystemCommand();
    
    try {
       while(cursor.hasNext()) {
           OperatingSystem os = getOS.execute("_id",
               cursor.next().get("_id").toString());
           operatingsystems.add(os);
       }
    } finally {
       cursor.close();
    }
    System.out.println(operatingsystems);
    return operatingsystems;
  }
}
