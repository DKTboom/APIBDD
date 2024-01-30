package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestData;
import resources.Utility;

public class stepDefination extends Utility {
	
	RequestSpecification res;
	ResponseSpecification resp;
	Response response;
	static String place_id;  //Static as place_id can be used in multiple Scenarios(TC). Or else it will throw null pointer exception.

	@Given("Add Place Payload with {string}  {string}")
	public void add_place_payload_with(String name, String accuracy) throws IOException {
				
				 res = given().spec(requestSpecification()).body(TestData.getPayload(name, accuracy));
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String APIName, String method) {
		
		APIResources resourcAPI = APIResources.valueOf(APIName);

		
		 //resp = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
				 
				 if(method.equalsIgnoreCase("POST"))
					 response =res.when().post(resourcAPI.getResourceURL());
					else if(method.equalsIgnoreCase("GET"))
						 response =res.when().get(resourcAPI.getResourceURL());
					else if(method.equalsIgnoreCase("DELETE"))
						 response =res.when().delete(resourcAPI.getResourceURL());
				
				
	}
	
	
	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
	   
		assertEquals(response.getStatusCode(),200);
	}
	
	
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyName, String expectedValue) {
		
		assertEquals(rawToJson(response,keyName),expectedValue);
	}
	
	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_created_maps_to_using(String expectedName, String APIName) throws IOException {
		place_id = rawToJson(response,"place_id");
		res = given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_with_http_request(APIName, "GET");
		String acctualName = rawToJson(response,"name");
		assertEquals(acctualName,expectedName);
		
		
	}
	
	@Then("verify place_Id is deleted from DB using {string}")
	public void verify_place_id_is_deleted_from_db_using(String APIName) throws IOException {
		res = given().spec(requestSpecification()).body("{\r\n"
				+ "    \"place_id\": \""+place_id+"\"\r\n"
				+ "}");
		user_calls_with_http_request(APIName, "DELETE");
		
	}
	

	@Given("deletePlaceAPI payload")
	public void delete_place_api_payload() throws IOException {
		res = given().spec(requestSpecification()).body("{\r\n"
				+ "    \"place_id\": \""+place_id+"\"\r\n"
				+ "}");
		
	}


}
