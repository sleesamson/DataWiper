package com.datawiper.mongo;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.datawiper.properties.PropertiesLookup;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

public class ConnectionProvider {

    public DBCollection getCollection(String coll) {
        PropertiesLookup pl = new PropertiesLookup();
        String dbURL = pl.getProperty("mongodbURL");
        int port = Integer.parseInt(pl.getProperty("mongodbPORT"));
        String dbname = pl.getProperty("mongodbDB");
        String user = pl.getProperty("mongodbUSER");
        String pw = pl.getProperty("mongodbPW");

        try {
            MongoClient mongo = new MongoClient(dbURL, port);

            DB db = mongo.getDB(dbname);
            if (db == null) {
                System.out.println("Could not connect to Database");
            }

            boolean auth = db.authenticate(user, pw.toCharArray());
            if (auth == false) {
                System.out.println("Could not authenticate");
            }

            DBCollection booksColl = db.getCollection(coll);
            return booksColl;

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (MongoException e) {
            e.printStackTrace();
        }
        return null;
    }

}