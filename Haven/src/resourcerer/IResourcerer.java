package resourcerer;

import java.util.Set;

import model.Resource;
import model.User;

/**
 * This interface offers access to 
 * 
 * @author Kim
 */
public interface IResourcerer {
	
	/**
	 * Gets a set of all known resources.
	 * @return a set of all running resources known to IResourcerer
	 */
	public Set<Resource> getResourceSet();
	
	/**
	 * Returns a resource with the matching id.
	 * @param id the id of the resource.
	 * @return the resource matching the id.
	 */
	public Resource getResourceById(long id);
}
