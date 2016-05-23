package resourcerer;

import java.net.MalformedURLException;
import java.net.URL;

public class DockerResource extends AbstractResource 
{
	
	public DockerResource() throws MalformedURLException
	{
		super("DockerSwarm", new URL ("http://ec2-52-29-189-238.eu-central-1.compute.amazonaws.com:2375/images/json"));
	}

}
