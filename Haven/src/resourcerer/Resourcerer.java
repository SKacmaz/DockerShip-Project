package resourcerer;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import core.Haven;

/**
 * Handles the different resource endpoints and collects the information about the resources.
 *
 * @author Kim
 */
public class Resourcerer {

	static final Logger LOGGER = Logger.getLogger(Resourcerer.class.getName());
	private Map<Long, String> resourceIds;
	private List<AbstractResourceEndpoint> resourceEndpoints;

	public Resourcerer(AbstractResourceEndpoint... resourcesToWatch) 
	{
		resourceIds = new HashMap<>();
		resourceEndpoints = new ArrayList<AbstractResourceEndpoint>();
		for (AbstractResourceEndpoint resource: resourcesToWatch)
		{
			resourceEndpoints.add(resource);
		}
		
		//TODO Remove Temporary Resource management
		try {
			LOGGER.debug("adding Docker Resource");
			resourceEndpoints.add(new DockerResourceEndpoint());
		} catch (MalformedURLException e) {
			LOGGER.error("Could not create Docker resource: " + e);
		}
	}
	
	/**
	 * 
	 * @param resource
	 * @return
	 */
	public String getJsonFrom(AbstractResourceEndpoint resource)
	{
		LOGGER.debug("getting Json from: " + resource.getResourceName());
		String result = "";
		try {
			result = getJsonFrom(resource.getResourceUrl().toString());
		} catch (IOException e) {
			LOGGER.error("Failed to get json, because: " + e);
		}
		return result;
	}

	/**
	 * 
	 * @param urlToRead
	 * @return
	 * @throws IOException
	 */
	public String getJsonFrom(String urlToRead) throws IOException {
		LOGGER.debug("getting Json from Url: " + urlToRead);
		
		StringBuilder result = new StringBuilder();
		URL url = new URL(urlToRead);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line;
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		rd.close();
		return result.toString();
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public AbstractResourceEndpoint getAttachedResource(String name)
	{
		//TODO remove temporary solution
		return resourceEndpoints.get(0);
	}
}
