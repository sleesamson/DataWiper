package com.datawiper.commands;

import org.bson.types.ObjectId;
import org.codehaus.jackson.map.ObjectMapper;

import com.datawiper.models.Manufacturer;
import com.datawiper.mongo.ConnectionProvider;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class GetManufacturerCommand {
  ObjectMapper mapper = new ObjectMapper();

  public Manufacturer execute(String key, String value) {
      ConnectionProvider conn = new ConnectionProvider();
      DBCollection manufCollection = conn.getCollection("manufacturers");

      BasicDBObject searchQuery = new BasicDBObject();
      if (key.equals("_id")) {
          searchQuery.put(key, new ObjectId(value));
      } else {
          searchQuery.put(key, value);
      }

      DBObject manuf = manufCollection.findOne(searchQuery);

      Manufacturer manufact = null;
      try {
          manufact = mapper.readValue(manuf.toString(), Manufacturer.class);
      } catch (Exception e) {
          e.printStackTrace();
      }
      return manufact;
  }
}