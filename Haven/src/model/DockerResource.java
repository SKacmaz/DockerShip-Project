package model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
/**
 * This class models the resources specific to Docker.
 * @author Kim Reichert
 *
 */
public class DockerResource extends AbstractResource{

	private String type;
	private User user;
	private String dockerId;
	private Map<String, Object> custom = new HashMap<>();
	
	public DockerResource(long id, String type, User user, String dockerId){
		this.id = id;
		this.type = type;
		this.user = user;
		this.dockerId = dockerId;
	}
	
	public DockerResource(long id, String type, User user, String dockerId, Date start){
		this.id = id;
		this.type = type;
		this.user = user;
		this.dockerId = dockerId;
		this.start = start;
	}
	
	@Override
	public JSONObject toJSON() throws JSONException
	{
		JSONObject json = new JSONObject();

		json.put("id", this.id);
		json.put("start", this.start);
		json.put("type", this.type);
		json.put("user", this.user);
		
		return json;
	}
	
	public void fromJSON(JSONObject json) throws JSONException{
		this.id = json.getLong("id");
		this.type = json.getString("type");
		this.user = (User) json.get("user");
		this.start = (Date) json.get("start");
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Map<String, Object> getCustom() {
		return custom;
	}
	public void setCustom(Map<String, Object> custom) {
		this.custom = custom;
	}
	
	@Override
	public String toString()
	{
		String result = 
			"id: " + this.id + " start: " + this.start + " type: " + this.type + " user: " + this.user;
		return result.toString();
	}
}