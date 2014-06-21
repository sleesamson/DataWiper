package com.datawiper.services;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.datawiper.models.Handset;

  
@Path("/metadata")
public class MetadataService 
{
    ObjectMapper mapper = new ObjectMapper();

    @GET
    @Path("/handset")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBookMetadata() {
        Handset hs = new Handset();
        String hsJSON;
        try {
            hsJSON = mapper.writeValueAsString(hs);
            return Response.status(200).entity(hsJSON).build();
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Response.status(500).build();
    }

}


