package webservice;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.Resource;

@Path("/resource")
public class ResourceService {

	static final Logger LOGGER = Logger.getLogger(TestService.class.getName());
    private List<Resource> resources= new ArrayList<Resource>();
	
	@GET
    @Path("/id")
    @Produces("application/json")
    public Response getResourceById(@QueryParam("param") String param) {
		JSONObject json = new JSONObject();
		try {
			json.put("id", "not yet provided");
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
