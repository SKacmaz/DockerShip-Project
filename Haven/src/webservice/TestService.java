package webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.*;

@Path("/test")
public class TestService {
    
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
			e1.printStackTrace();
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
			e1.printStackTrace();
            return Response.serverError().build();
		}
    }
}
