package com.datawiper.commands;

import java.util.ArrayList;

import com.datawiper.models.Manufacturer;
import com.datawiper.mongo.ConnectionProvider;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class ListAllManufacturersCommand {
  public ArrayList<Manufacturer> execute(){
    ConnectionProvider conn = new ConnectionProvider();
    DBCollection collection = conn.getCollection("manufacturers");

    DBCursor cursor = collection.find();
    
    ArrayList<Manufacturer> manufacturers = new ArrayList<Manufacturer>();

    GetManufacturerCommand getManufacturer = new GetManufacturerCommand();
    
    try {
       while(cursor.hasNext()) {
           Manufacturer m = getManufacturer.execute("_id",
               cursor.next().get("_id").toString());
           manufacturers.add(m);
       }
    } finally {
       cursor.close();
    }
    return manufacturers;
    
  }
}
