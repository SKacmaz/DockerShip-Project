package resourcerer;

public class IdCreator {
	
	public static long idCounter;
	public static long userIdCounter;
	
	public IdCreator()
	{
		idCounter = 0;
		userIdCounter = 0;
	}
	
	/**
	 * Creates a new, unique id of type Long.
	 */
	public synchronized Long getNewId()
	{
		idCounter ++;
		Long id = new Long(idCounter);
		
		return id;
	}
	
	/**
	 * Creates a new, unique id of type Long.
	 */
	public synchronized Long getNewUserId()
	{
		userIdCounter ++;
		Long id = new Long(userIdCounter);
		
		return id;
	}

}
