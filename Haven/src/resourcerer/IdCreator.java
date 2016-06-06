package resourcerer;

public class IdCreator {
	
	public static long idCounter;
	
	public IdCreator()
	{
		idCounter = 0;
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

}
