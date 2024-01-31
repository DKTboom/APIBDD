Feature: Validating Place API's

  @AddPlace
  Scenario Outline: Verify new places is being added successfully using addPlaceAPI
  
   Given Add Place Payload with "<name>"  "<accuracy>"
	When user calls "addPlaceAPI" with "POST" http request
	Then the API call got success with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify place_Id created maps to "<name>" using "getPlaceAPI"

	
Examples:
	|name 	 | accuracy |
	|Alivia1 |  500 |
#	|BBhouse | 100  |


  @deletePlace
Scenario: Verify new place is deleted successfully using deletePlaceAPI

	Given deletePlaceAPI payload
	When user calls "deletePlaceAPI" with "POST" http request
	Then the API call got success with status code 200
	And "status" in response body is "OK"
