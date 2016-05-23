package resourcerer;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import core.Haven;

public class Resourcerer {

	static final Logger LOGGER = LogManager.getLogger(Resourcerer.class.getName());
	private List<AbstractResource> resources;

	public Resourcerer(AbstractResource... resourcesToWatch) 
	{
		resources = new ArrayList<AbstractResource>();
		for (AbstractResource resource: resourcesToWatch)
		{
			resources.add(resource);
		}
		
		
		//TODO Remove Temporary Resource management
		try {
			LOGGER.debug("adding Docker Resource");
			resources.add(new DockerResource());
		} catch (MalformedURLException e) {
			LOGGER.error("Could not create Docker resource: " + e);
		}
	}
	
	/**
	 * 
	 * @param resource
	 * @return
	 */
	public String getJsonFrom(AbstractResource resource)
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
	public AbstractResource getAttachedResource(String name)
	{
		//TODO remove temporary solution
		return resources.get(0);
	}
}
