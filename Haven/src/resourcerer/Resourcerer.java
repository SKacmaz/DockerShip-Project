package resourcerer;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class Resourcerer {

	private List<AbstractResource> resources;

	public Resourcerer(AbstractResource... resourcesToWatch) 
	{
		
		resources = new ArrayList<AbstractResource>();
		for (AbstractResource resource: resourcesToWatch)
		{
			resources.add(resource);
		}
		
		try {
			resources.add(new DockerResource());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	public String getJsonFrom(AbstractResource resource)
	{
		String result = "";
		try {
			result = getJsonFrom(resource.getResourceUrl().toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public String getJsonFrom(String urlToRead) throws IOException {
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
	
	public AbstractResource getAttachedResource(String name)
	{
		return resources.get(0);
	}
}
