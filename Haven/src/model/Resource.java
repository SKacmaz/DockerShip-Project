package model;

import org.json.JSONException;
import org.json.JSONObject;

public class Resource {
	private long id;
	private String type;
	private User user;
	
	public Resource(){super();}
	public Resource(long id, String type, User user){
		super();
		this.id = id;
		this.type = type;
		this.user = user;
	}
	
	public JSONObject toJSON() throws JSONException{
		JSONObject json = new JSONObject();
		
		json.put("id", this.id);
		json.put("type", this.type);
		json.put("user", this.user);
		
		return json;
	}
	
	public void loadFromJSON(JSONObject json) throws JSONException{
		this.id = json.getLong("id");
		this.type = json.getString("type");
		this.user = (User) json.get("user");
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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

}