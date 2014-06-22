package com.datawiper.models;

public class OperatingSystem {
  private String name;
  private String [] instructions;
  
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String[] getInstructions() {
    return instructions;
  }
  public void setInstructions(String[] instructions) {
    this.instructions = instructions;
  }

}