package model;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;
/**
 * This interface the defines the communication with all the resources.
 * 
 * @author Jochen Joswig
 */
public interface IResource {
	public long getId();
	public Date getStart();
	
	public JSONObject toJSON() throws JSONException;
}