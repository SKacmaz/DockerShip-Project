package resourcerer;

import java.util.Set;

import org.json.JSONObject;

import model.IResource;
import model.User;

/**
 * This interface offers access to the resources, Haven is supposed to handle.
 * 
 * @author Kim
 */
public interface IResourcerer {
	
	/**
	 * Gets an array of all known resources.
	 * @return a set of all running resources known to IResourcerer
	 */
	public IResource[] getResourceSet();
	
	/**
	 * Returns a resource with the matching id.
	 * @param id the id of the resource.
	 * @return the resource matching the id.
	 */
	public IResource getResourceById(long id);
	
	/**
	 * Returns the attached Endpoint, if name matches known list of endpoints.
	 * @param name the name of the Endpoint
	 * @return the resource endpoint, null if not found.
	 */
	public AbstractEndpoint getEndpoint(String name);
}
