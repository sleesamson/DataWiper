package com.datawiper.models;

import java.io.IOException;

import org.bson.types.ObjectId;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.datawiper.mongo.ConnectionProvider;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class MongoID {
    private String $oid;

    public MongoID() {
    }

    public String get$oid() {
        return $oid;
    }

    public void set$oid(String $oid) {
        this.$oid = $oid;
    }

    @JsonCreator
    public static String fromJSON(String val) throws JsonParseException,
            JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        MongoID a = mapper.readValue(val, MongoID.class);
        return a.get$oid();
    }
    
}