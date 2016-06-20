package resourcerer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.DockerResource;
import model.IResource;
import model.User;

public class DockerEndpoint extends AbstractEndpoint 
{
	private static final String CONTAINER_KEY = "container";

	static final Logger LOGGER = Logger.getLogger(DockerEndpoint.class.getName());
	
	private static final String CURRENT_URL = 
			"http://ec2-52-28-184-227.eu-central-1.compute.amazonaws.com:2375";
	
	private static final String PATH_IMAGES = "/images/json";
	
	private static final String PATH_CONTAINER_ALL = "/containers/json?all=1";
	
	private static final String PATH_CONTAINER_ACTIVE = "/containers/json?all=0";
	
	private User defaultUser;
	
	/**
	 * 
	 * @throws MalformedURLException
	 */
	public DockerEndpoint() throws MalformedURLException
	{
		super("DockerSwarm", new URL(CURRENT_URL));
		LOGGER.info("Initializing Docker Resource with URL: " + CURRENT_URL);
		
		defaultUser = new User(0,"Test","User");
	}
	
	@Override
	public Set<IResource> getResources()
	{
		Set<IResource> set = new HashSet<>();
		
		JSONObject r = getJsonFrom(PATH_CONTAINER_ALL);
		
		try {
			JSONArray containerArray = r.getJSONArray(CONTAINER_KEY);
			
			for (int i = 0; i < containerArray.length(); i++) {
				JSONObject resource = containerArray.getJSONObject(i);
				
				IResource res = new DockerResource(
						getCreator().getNewId(),
						"default",
						defaultUser, 
						
						resource.get("Id").toString());
				set.add(res);
			}
		} catch (JSONException e) {
			LOGGER.warn("Could not get object from json",e);
		}
		
		return set;
	}
	
	@Override
	public String testEnpoint()
	{
		JSONObject r = getJsonFrom(PATH_IMAGES);
			
		if(r == null)
		{
			LOGGER.error("could not reach Endpoint in test");
		}
		return r.toString();
	}

	/**
	 * Gets the json from the given path and the set url of this endpoint.
	 * @param path the path to get the json from.
	 * @return A string made from json result.
	 * @throws IOException in case URL is not reachable.
	 */
	private JSONObject getJsonFrom(String path) 
	{
		LOGGER.debug("getting Json from Url: " + path);
		
		StringBuilder result = new StringBuilder();
		try {
			URL url = new URL(getResourceUrl().toString() + path);
		
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			rd.close();
		} catch (MalformedURLException e) {
			LOGGER.error("URL could not be formed from path: " + path, e);
		} catch (ProtocolException e) {
			LOGGER.error("Protocol could not be set for connection", e);
		} catch (IOException e) {
			LOGGER.error("Problem with connection while getting Json for path " + path,  e); 
		}
		
		try {
			String r = result.toString();
			
			//if json result string is array, not object
			if(r.startsWith("[") && r.endsWith("]"))
			{
				JSONObject j = new JSONObject();
				JSONArray arr = new JSONArray(r);
				j.put(CONTAINER_KEY, arr);
				return j;
			}
			
			return new JSONObject(r);
			
		} catch (JSONException e) {
			LOGGER.error("could not cast GET result to String", e);
		}
		return null;
	}
}
