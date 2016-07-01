package model;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class models the resource as an abstract class. All other resources must extends this abstract class.
 * @author Jochen Joswig
 *
 */
public abstract class AbstractResource implements IResource{
	protected long id;
	protected Date start;

	/**
	 * 
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * 
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * 
	 * @return
	 */
	public Date getStart() {
		return start;
	}
	
	/**
	 * 
	 * @param start
	 */
	public void setStart(Date start) {
		this.start = start;
	}
	
	public abstract JSONObject toJSON() throws JSONException;
	public abstract void fromJSON(JSONObject json) throws JSONException;
	
}
