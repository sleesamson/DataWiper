package com.datawiper.commands;

import org.bson.types.ObjectId;
import org.codehaus.jackson.map.ObjectMapper;

import com.datawiper.models.OperatingSystem;
import com.datawiper.mongo.ConnectionProvider;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class GetOperatingSystemCommand {
  ObjectMapper mapper = new ObjectMapper();

  public OperatingSystem execute(String key, String value) {
      ConnectionProvider conn = new ConnectionProvider();
      DBCollection collection = conn.getCollection("operatingsystems");

      BasicDBObject searchQuery = new BasicDBObject();
      if (key.equals("_id")) {
          searchQuery.put(key, new ObjectId(value));
      } else {
          searchQuery.put(key, value);
      }

      DBObject osObject = collection.findOne(searchQuery);

      OperatingSystem os = null;
      try {
          os = mapper.readValue(osObject.toString(), OperatingSystem.class);
      } catch (Exception e) {
          e.printStackTrace();
      }
      return os;
  }
}

