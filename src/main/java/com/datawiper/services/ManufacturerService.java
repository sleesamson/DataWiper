package com.datawiper.services;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;

import com.datawiper.commands.ListAllManufacturersCommand;
import com.datawiper.models.Manufacturer;


@Path("/manufacturers")
public class ManufacturerService {
  
  ObjectMapper mapper = new ObjectMapper();

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response listManufacturers() {
      ListAllManufacturersCommand listBooks = new ListAllManufacturersCommand();
      ArrayList<Manufacturer> list = listBooks.execute();
      String booksString = null;
      try {
          booksString = mapper.writeValueAsString(list);
      } catch (Exception e) {
          e.printStackTrace();
      }
      return Response.status(200).entity(booksString).build();

  
  }
}
