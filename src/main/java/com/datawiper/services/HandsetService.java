package com.datawiper.services;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;

import com.datawiper.commands.CreateHandsetCommand;
import com.datawiper.commands.GetInstructionsCommand;
import com.datawiper.models.Handset;
import com.mongodb.DBObject;

@Path("/handsets")
public class HandsetService {
  ObjectMapper mapper = new ObjectMapper();
  
  @GET
  @Path("/{manufacturer}/{model}/instructions")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getInstructions(@PathParam("manufacturer") String field, @PathParam("model") String value) {
      GetInstructionsCommand instruct = new GetInstructionsCommand();
      Object instructions = instruct.searchField(field, value);   
      if (instructions == null)
        return Response.status(404).entity("NOT FOUND").type("text/plain").build();
      return Response.status(200).entity(instructions).build();
  }
  
  @PUT
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN })
  public Response createHandset(String HandsetStr) {

      try {
          CreateHandsetCommand create = new CreateHandsetCommand();
          Handset hs = mapper.readValue(HandsetStr, Handset.class);
          boolean success = create.execute(hs);
          String hsJSON = mapper.writeValueAsString(hs);
          if (success) {
              return Response.status(201).entity(hsJSON).build();
          } else
              return Response.status(500).entity("").build();
      } 
      catch (Exception e) {
          return Response.status(500).entity(e.toString()).build();
      }
  }
  
  

}

