package handler;

import java.util.ArrayList;
import java.util.List;

import model.DockerResource;

public class ResourceHandler {
	private static List<DockerResource> resources = new ArrayList<DockerResource>();


	public DockerResource getResourceById(long id) {
		for(DockerResource r : resources){
			if(r.getId() == id){
				return r;
			}
		}
		
		return null;
	}
	
	public List<DockerResource> getResources() {
		return resources;
	}
	public void setResources(List<DockerResource> resources) {
		this.resources = resources;
	}

}