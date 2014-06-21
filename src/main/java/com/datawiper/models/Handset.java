package com.datawiper.models;

import java.net.UnknownHostException;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;


public class Handset {

  @JsonIgnore
  private Manufacturer manufacturer;
  private String _manufacturer_id;
  
  private String model;
  private String [] instructions;
  
  /*@JsonIgnore
  private OperatingSystem os;
  private String _os_id;
  */
  public String getModel() {
    return model;
  }
  public void setModel(String model) {
    this.model = model;
  }
  public String[] getInstructions() {
    return instructions;
  }
  public void setInstructions(String[] instructions) {
    this.instructions = instructions;
  }
  public String get_manufacturer_id() {
    return _manufacturer_id;
  }
  public void set_manufacturer_id(String _manufacturer_id) {
    this._manufacturer_id = _manufacturer_id;
  }
  /*
  public OperatingSystem getOs() {
    return os;
  }
  public void setOs(OperatingSystem os) {
    this.os = os;
  }
  public String get_os_id() {
    return _os_id;
  }
  public void set_os_id(String _os_id) {
    this._os_id = _os_id;
  }
  */
  public static void main(String[] args) {
    try {

        MongoClient mongo = new MongoClient("kahana.mongohq.com", 10026);
        DB db = mongo.getDB("datawiper");
        if (db == null) {
            System.out.println("Could not connect to Database");
        }

        boolean auth = db.authenticate("slee", "unluckypants".toCharArray());
        if (auth == false) {
            System.out.println("Could not authenticate");
        }
        DBCollection coll = db.getCollection("manufacturers");

        BasicDBObject document = new BasicDBObject();
        document.put("name", "Blackberry");
        coll.insert(document);


    } catch (UnknownHostException e) {
        e.printStackTrace();
    } catch (MongoException e) {
        e.printStackTrace();
    }
}

}