package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@deletePlace")
	public void beforeDeleteAPITest() throws IOException {
		
		stepDefination m = new stepDefination();
		
		if(stepDefination.place_id==null) {
		m.add_place_payload_with("Alivia", "70");
		m.user_calls_with_http_request("addPlaceAPI", "POST");
		m.verify_created_maps_to_using("Alivia", "getPlaceAPI");
		}
		
	}

}
