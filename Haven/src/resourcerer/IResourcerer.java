package resourcerer;

import java.util.Set;

import model.IResource;
import model.User;

/**
 * This interface offers access to the resources, Haven is supposed to handle.
 * 
 * @author Kim
 */
public interface IResourcerer {
	
	/**
	 * Gets a set of all known resources.
	 * @return a set of all running resources known to IResourcerer
	 */
	public Set<IResource> getResourceSet();
	
	/**
	 * Returns a resource with the matching id.
	 * @param id the id of the resource.
	 * @return the resource matching the id.
	 */
	public IResource getResourceById(long id);
}
