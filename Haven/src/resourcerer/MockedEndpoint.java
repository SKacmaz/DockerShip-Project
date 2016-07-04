package resourcerer;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import model.DockerResource;
import model.IResource;
import model.User;

/**
 * This Endpoint can be used to simulate events for testing.
 *
 * @author Kim
 */
public class MockedEndpoint extends AbstractEndpoint {

	private List<IResource> mocked;
	
	private String[] mockedTypes = {"orange", "blue", "yellow"};
	
	private User userA = new User(1, "Bob", "Marley");
	
	private User userB = new User(2, "Marilyn", "Monroe");
	
	private User[] mockedUsers = {userA, userB};
	
	private int maxNumberOfResources = 8;
	
	private Random randomGenerator = new Random();
	
	
	/**
	 * 
	 * @throws MalformedURLException not expected.
	 */
	public MockedEndpoint() throws MalformedURLException {
		super("MockedEndpoint", new URL("http://mockedUrl.com"));
		mocked = mockSomeResources(maxNumberOfResources);
	}

	@Override
	public Set<IResource> getResources() {
		Set<IResource> subset 
		= new HashSet<>(mocked.subList(0, randomGenerator.nextInt(maxNumberOfResources)+ 1));
		return subset;
	}

	@Override
	public String testEnpoint() {
		return "{mocked Endpoint works: true}";
	}
	
	private List<IResource> mockSomeResources(int numberOfResources)
	{
		List<IResource> mockedResources = new ArrayList<>();
		for(int i = 0; i < numberOfResources; i++)
		{
			DockerResource resource = 
					new DockerResource(getCreator().getNewId(), 
							getRandomType(), getRandomUser(), getRandomDockerId());			
			mockedResources.add(resource);
		}
		
		return mockedResources;
	}
	
	private String getRandomType()
	{
		return mockedTypes[randomGenerator.nextInt(mockedTypes.length)];
	}
	
	private User getRandomUser()
	{
		return mockedUsers[randomGenerator.nextInt(mockedUsers.length)];
	}
	
	private String getRandomDockerId()
	{
		return UUID.randomUUID().toString();
	}

}
