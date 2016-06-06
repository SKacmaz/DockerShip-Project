package model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

public class DockerResource extends AbstractResource{

	private String type;
	private User user;
	private String dockerId;
	private Map<String, Object> custom = new HashMap<>();
	
	public DockerResource(){super();}
	public DockerResource(long id, String type, User user, String dockerId){
		super();

		this.type = type;
		this.user = user;
		this.dockerId = dockerId;
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
}