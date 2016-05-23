package webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.*;

import core.Haven;

@Path("/test")
public class TestService {
	
	static final Logger LOGGER = LogManager.getLogger(TestService.class.getName());
    
	@GET
    @Path("/testDefaultAnswer")
    @Produces("application/json")
    public Response test() {
		JSONObject json = new JSONObject();
		
		try {
			json.put("param", "default");

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
    @Path("/testParamAnswer")
    @Produces("application/json")
    public Response test(@QueryParam("param") String param) {
		JSONObject json = new JSONObject();
		
		try {
			json.put("param", param);

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
