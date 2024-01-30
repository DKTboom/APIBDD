package resources;

public enum APIResources {
	
	getPlaceAPI("/maps/api/place/get/json"),
	addPlaceAPI("/maps/api/place/add/json"),
	deletePlaceAPI("/maps/api/place/delete/json");

private String resource;

	 APIResources(String resource) {
		 this.resource = resource;
		
	}
	
	 public String getResourceURL() {
		 
		return resource;
	 }

}
