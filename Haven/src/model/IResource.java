package model;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

public interface IResource {
	public long getId();
	public Date getStart();
	
	public JSONObject toJSON() throws JSONException;
}
