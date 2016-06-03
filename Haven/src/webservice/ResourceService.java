package webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import handler.ResourceHandler;

@Path("/resource")
public class ResourceService {

	static final Logger LOGGER = Logger.getLogger(TestService.class.getName());
    private ResourceHandler resources = new ResourceHandler();
    
	@GET
    @Path("/id")
    @Produces("application/json")
    public Response getResourceById(@QueryParam("id") long id) {
		JSONObject json = new JSONObject();
		try {
			json = resources.getResourceById(id).toJSON();
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
