package webservice;

import model.IResource;

public class JsonResourceContainer {
		   private IResource[] resources = {};

		public IResource[] getResourceList() {
			return resources;
		}

		public void setResourceList(IResource[] resources) {
			this.resources = resources;
		}
}
