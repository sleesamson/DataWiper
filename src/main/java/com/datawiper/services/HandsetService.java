package com.datawiper.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;

import com.datawiper.commands.CreateHandsetCommand;
import com.datawiper.models.Handset;

@Path("/handsets")
public class HandsetService {
  ObjectMapper mapper = new ObjectMapper();
  
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

