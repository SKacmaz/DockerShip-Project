package resourcerer;

import java.net.URL;
import java.util.Set;

import model.IResource;

/**
 * Representation of an Endpoint that gives information about resources.
 *
 * @author Kim R.
 */
public abstract class AbstractEndpoint {
	
	private final String resourceName;
	
	private final URL resourceUrl;
	
	private static IdCreator creator;
	
	public AbstractEndpoint(final String name, final URL url)
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
	
	public void setCounter(final IdCreator creator)
	{
		this.creator = creator;
	}
	
	/**
	 * Acquires and returns all active resources.
	 * @return all active resources of the called endpoint.
	 */
	public abstract Set<IResource> getResources();

	/**
	 * A method to test the if the endpoint is working.
	 * Makes http-request to stored endpoint and returns
	 * json as string.
	 * @return a string from the requested json.
	 */
	public abstract String testEnpoint();
	
	protected IdCreator getCrator()
	{
		return creator;
	}
}
