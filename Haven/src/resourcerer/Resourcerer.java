package resourcerer;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.log4j.Logger;

import core.Haven;
import model.IResource;

/**
 * Handles the different resource endpoints and collects the information about the resources.
 *
 * @author Kim
 */
public class Resourcerer implements IResourcerer
{
	static final Logger LOGGER = Logger.getLogger(Resourcerer.class.getName());
	private Map<Long, IResource> resourceMap;
	private List<AbstractEndpoint> resourceEndpoints;
	private Set<IResource> resources;
	
	protected static IdCreator idMaker;
	/**
	 * Creates a new Resourcerer that has access to the given endpoints.
	 * 
	 * @param endpointsToWatch the endpoints that the resourcerer can talk to.
	 */
	public Resourcerer(AbstractEndpoint... endpointsToWatch) 
	{
		LOGGER.info("Initializing resourcerer with " + endpointsToWatch.length + " endpoints");
		resourceMap = new HashMap<>();
		resourceEndpoints = new ArrayList<AbstractEndpoint>();
		resources = new HashSet<>();
		idMaker = new IdCreator();
		
		for (AbstractEndpoint endpoint: endpointsToWatch)
		{
			resourceEndpoints.add(endpoint);
			endpoint.setCounter(idMaker);
		}
	}
	
	@Override
	public Set<IResource> getResourceSet() {
		for(AbstractEndpoint point: resourceEndpoints)
		{
			Set<IResource> pointResources = point.getResources();
			for(IResource res: pointResources)
			{
				resources.add(res);
			}
		}
		return (HashSet<IResource>) resources;
	}

	@Override
	public IResource getResourceById(long id) {
		return resourceMap.get(id);
	}
	
	@Override
	public AbstractEndpoint getEndpoint(String name)
	{
		for(AbstractEndpoint point: resourceEndpoints)
		{
			if(point.getResourceName().equals(name))
			{
				return point;
			}
		}
		return null;
	}

}
