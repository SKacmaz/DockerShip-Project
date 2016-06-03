package resourcerer;

import java.net.URL;

/**
 * 
 * @author Kim R.
 *
 */
public abstract class AbstractResourceEndpoint {
	
	private final String resourceName;
	
	private final URL resourceUrl;
	
	public AbstractResourceEndpoint(final String name, final URL url)
	{
		resourceName = name;
		resourceUrl  = url;
	}

	/**
	 * 
	 * @return name of the resource to monitor.
	 */
	public String getResourceName() {
		return resourceName;
	}

	public URL getResourceUrl() {
		return resourceUrl;
	}

}
