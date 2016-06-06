package model;

import org.json.JSONException;
import org.json.JSONObject;

public interface IResource {
	public long getId();
	public String getType();
	
	public JSONObject toJSON() throws JSONException;
}
