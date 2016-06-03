package resourcerer;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;

public class DockerResourceEndpoint extends AbstractResourceEndpoint 
{
	static final Logger LOGGER = Logger.getLogger(DockerResourceEndpoint.class.getName());
	
	public DockerResourceEndpoint() throws MalformedURLException
	{
		super("DockerSwarm", new URL ("http://ec2-52-28-184-227.eu-central-1.compute.amazonaws.com:2375/images/json"));
		LOGGER.info("Initializing Docker Resource with URL: " + this.getResourceUrl().toString());
	}

}
