package webservice;

import java.util.Iterator;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.sun.jersey.api.core.InjectParam;
import com.sun.jersey.spi.inject.Inject;

import handler.ResourceHandler;
import model.IResource;
import resourcerer.IResourcerer;
import resourcerer.Resourcerer;

@Path("/resources")
public class ResourceService {

	@Context
	Resourcerer sourcerer;
	
	static final Logger LOGGER = Logger.getLogger(TestService.class.getName());
    
	@GET
	@Produces("application/json")
	public Response getResources()
	{
		LOGGER.info("#");
		LOGGER.info("GET resources/");
		LOGGER.info("#");
		
		JSONObject json = new JSONObject();
		try {
			
			Iterator<IResource> iterator = sourcerer.getResourceSet().iterator();
			JSONArray arr = new JSONArray(sourcerer.getResourceSet());
			json.put("Resources", arr);
			
			return Response.ok(
			        json.toString(),
			        MediaType.APPLICATION_JSON_TYPE
			).build();
	    
		} catch (JSONException e1) {
			LOGGER.error("Json Request failed with: " + e1);
			return Response.serverError().build();
		}
		
	}

	@GET
    @Path("/id")
    @Produces("application/json")
    public Response getResourceById(@QueryParam("id") long id) {
		LOGGER.info("#");
		LOGGER.info("GET resources/id with: " + id);
		LOGGER.info("#");
		
		JSONObject json = new JSONObject();
		try {
			json = sourcerer.getResourceById(id).toJSON();
			//TODO check how to send a List of Objects with JSON -> TestService.java
			return Response.ok(
			        json.toString(),
			        MediaType.APPLICATION_JSON_TYPE
			).build();
        
		} catch (JSONException e1) {
			LOGGER.error("Json Request failed with: " + e1);
			return Response.serverError().build();
		}
    }
	
}
