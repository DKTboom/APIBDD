package resources;

import java.util.ArrayList;
import java.util.List;

import POJO.AddPlace;
import POJO.Location;

public class TestData {
	
	public static AddPlace getPayload(String Name, String accuracy) {
		
		AddPlace p = new AddPlace();
		Location l =new Location();
		
		p.setAccuracy(accuracy);
		p.setAddress("24 nabanagar");
		p.setLanguage("Bengali");
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("https://rahulshettyacademy.com");
		p.setName(Name);
		List<String> myList =new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");

		p.setTypes(myList);
		
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);
		
		return p;
		
		
	}
	
	

}
