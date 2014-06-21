package com.datawiper.commands;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.bson.types.ObjectId;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONObject;

import com.datawiper.models.Manufacturer;
import com.datawiper.mongo.ConnectionProvider;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;

public class GetInstructionsCommand {
  ObjectMapper mapper = new ObjectMapper();
  
  public Object searchField(String manufacturer, String model) {
    //String [] instruct = new String[1];
    ConnectionProvider conn = new ConnectionProvider();
    DBCollection handsetCollection = conn.getCollection("handsets");
    DBCollection manufacturerCollection = conn.getCollection("manufacturers");

    
    BasicDBObject query = new BasicDBObject();

    manufacturer = Character.toUpperCase(manufacturer.charAt(0)) + manufacturer.substring(1);
    
    query.put("name", manufacturer);
    DBObject manuf = manufacturerCollection.findOne(query);
    
    Object man = manuf.get("_id");
    
    BasicDBObject query1 = new BasicDBObject();
    query1.put("model", model);
    query1.put("_manufacturer_id", man.toString());
        
    DBObject hs = handsetCollection.findOne(query1);
    
    
    Object ins = null;
    
    if (hs != null)
      ins = hs.get("instructions");

    return ins;

  }
}
   

