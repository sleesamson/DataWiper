package com.datawiper.models;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Manufacturer {
  private String name;
  private MongoID _id;
  private String id;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  
  @JsonIgnore
  public MongoID get_id() {
      return _id;
    }
  
  public void set_id(MongoID _id) {
      this._id = _id;
      setId(_id.get$oid());
  }

  public String getId() {
     return id;
  }

  public void setId(String id) {
      this.id = id;
  }  

}