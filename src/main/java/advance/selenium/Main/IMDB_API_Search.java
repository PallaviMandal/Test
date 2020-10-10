package advance.selenium.Main;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class IMDB_API_Search {
    private String apiKey = "ff291b8e";
    private String url = "http://www.omdbapi.com";

    public Map<Double, String> searchOnIMDB(String searchTerm) {
	// step 1: fetch all imdb ids so that we can get rating and title form that
	// imbdID
	ArrayList<String> imdbID = given().param("apikey", apiKey).param("s", searchTerm).when().get(url).then()
		.extract().path("Search.imdbID");
	Map<Double, String> map = new HashMap<>();
	Double rating = 0.00;
	String title = "";
	// here using imdb IDs 'title' and 'imdbRating' has been fetched and storing
	// them in a map as key value pair
	for (int i = 0; i < imdbID.size(); i++) {
	    try {
		Response res = given().param("i", imdbID.get(i)).param("apikey", apiKey).when().get(url);
		JsonPath jsonPath = res.jsonPath();
		if (jsonPath.get("imdbRating").toString().contains("N/A"))
		    rating = 0.00;
		else
		    rating = Double.parseDouble(jsonPath.get("imdbRating").toString());
		title = jsonPath.get("Title").toString();
	    } catch (Exception e) {
		e.printStackTrace(System.out);
		throw new RuntimeException();
	    }
	    map.put(rating, title);
	}
	TreeMap<Double, String> sortedMap = new TreeMap<>(Collections.reverseOrder());
	sortedMap.putAll(map);
	System.out.println("Result from API :" + sortedMap);
	return map;
    }
}
