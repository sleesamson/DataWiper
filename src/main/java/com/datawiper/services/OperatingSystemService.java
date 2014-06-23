package com.datawiper.services;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;

import com.datawiper.commands.GetInstructionsCommand;
import com.datawiper.commands.ListAllManufacturersCommand;
import com.datawiper.commands.ListAllOS;
import com.datawiper.models.Manufacturer;
import com.datawiper.models.OperatingSystem;
import com.wordnik.swagger.annotations.*;


@Path("/OS")
@Api(value = "/OS", description = "Lists all Supported Operating Systems")
public class OperatingSystemService {
  ObjectMapper mapper = new ObjectMapper();
  
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response listOperatingSystems() {
      ListAllOS listOS = new ListAllOS();
      ArrayList<OperatingSystem> list = listOS.execute();
      String osString = null;
      try {
          osString = mapper.writeValueAsString(list);
      } catch (Exception e) {
          e.printStackTrace();
      }
      return Response.status(200).entity(osString).build(); 
  }

  @GET
  @Path("/{value}/instructions")
  @Produces(MediaType.APPLICATION_JSON)
  public Response listInstructions(@PathParam("value") String value) {
      GetInstructionsCommand instruct = new GetInstructionsCommand();
      Object instructions = instruct.searchOS(value);
      String instructString = null;
      try {
          instructString = mapper.writeValueAsString(instructions);
      } catch (Exception e) {
          e.printStackTrace();
      }
      return Response.status(200).entity(instructString).build();
  
  }
}
