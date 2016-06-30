package webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import resourcerer.Resourcerer;

@Path("/resources")
public class ResourceService {

	@Context
	Resourcerer sourcerer;
	
	static final Logger LOGGER = Logger.getLogger(ResourceService.class.getName());
    
	@GET
	@Path("/all")
	@Produces("application/json")
	public Response getResources()
	{
		LOGGER.info("#");
		LOGGER.info("GET resources/");
		LOGGER.info("#");
		
//		JSONObject json = new JSONObject();
		Gson gson = new Gson();
		
		JsonResourceContainer jsonContainer = new JsonResourceContainer();
		jsonContainer.setResourceList(sourcerer.getResourceSet());
		String json = gson.toJson(jsonContainer);
		
		return Response.ok(
		        json,
		        MediaType.APPLICATION_JSON_TYPE
		).build();
		
	}

	@GET
    @Path("/id")
    @Produces("application/json")
    public Response getResourceById(@QueryParam("id") long id) {
		
		LOGGER.info("#");
		LOGGER.info("GET resources/id with: " + id);
		LOGGER.info("#");
		
//		JSONObject json = new JSONObject();
//		
//		try {
//			json = sourcerer.getResourceById(id).toJSON();
//			
//			return Response.ok(
//			        json.toString(),
//			        MediaType.APPLICATION_JSON_TYPE
//			).build();
//        
//		} catch (JSONException e1) {
//			LOGGER.error("Json Request failed with: " + e1);
//			return Response.serverError().build();
//		}
		return null;
    }
	
}