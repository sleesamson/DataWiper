package com.datawiper.commands;

import java.util.ArrayList;

import org.bson.types.ObjectId;
import org.codehaus.jackson.map.ObjectMapper;

import com.datawiper.models.Handset;
import com.datawiper.mongo.ConnectionProvider;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;
import com.mongodb.util.JSON;

public class CreateHandsetCommand {

    public boolean execute(Handset hs) {
        ConnectionProvider conn = new ConnectionProvider();
        DBCollection booksCollection = conn.getCollection("handsets");

        ObjectMapper mapper = new ObjectMapper();
        
        try {
            DBObject dbObject = (DBObject) JSON.parse(mapper
                    .writeValueAsString(hs));
            booksCollection.insert(dbObject);
        } 
        catch (Exception e) {
            System.out.println("ERROR during mapping book to Mongo Object");
            return false;
        }

        return true;
    }
}