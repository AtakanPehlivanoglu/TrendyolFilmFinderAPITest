package trendyol.filmfinder.apitest.operations;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import java.util.HashMap;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import trendyol.filmfinder.apitest.repository.FilmContent;


public class FindFilmOperations extends FilmContent {

	String api = "http://www.omdbapi.com/";
	String apikey;
	String filmSeriesName;
	String filmName;
	FilmContent filmContent;
	
	public FindFilmOperations() {
		// TODO Auto-generated constructor stub
	}
	
	@Before
	public void init() {
	
		apikey = "7c6659fe";
		filmSeriesName = "Harry Potter";
		filmName = "Harry Potter and the Sorcerer's Stone";
		filmContent = new FilmContent();
		
	}
	
	@Before
	public void GetFilmdId() {
	
		int statusCode = get(api).getStatusCode();
		Assert.assertEquals(statusCode, 200);
		
		Response res = given().
				queryParam("s",filmName).
				queryParam("apikey", apikey).
				when().
				get(api).
				then().
				body("$", hasKey("Search")).
				contentType(ContentType.JSON).extract().response();

		List<HashMap<String, String>> responseMap = res.jsonPath().getList("Search");

		for (HashMap<String, String> singleObject : responseMap) {
			if( singleObject.get("Title").toString().contentEquals(filmName)) 
			{
				filmContent.setimdbID(singleObject.get("imdbID").toString());
				filmContent.setTitle(singleObject.get("Title").toString());
			}	
		}
		assertEquals(filmContent.getTitle(), filmName);
	}
	
	@Test
	public void SearchFilmById() {
		
		String filmId = filmContent.getimdbID();
		
		Response res = given().
				queryParam("i", filmId).
				queryParam("apikey", apikey).
				when().
				get(api).
				then().
				body("$", hasKey("Title")).
				body("$", hasKey("Year")).
				body("$", hasKey("Released")).
				contentType(ContentType.JSON).extract().response();

		String response = res.asString();
		System.out.println("Film found by ID: ");
		System.out.println(response);
	}
	
	@Test
	public void SearchFilmByTitle() {
		
		String filmTitle = filmContent.getTitle();
		
		Response res = given().
				queryParam("t",filmTitle).
				queryParam("apikey", apikey).
				when().
				get(api).
				then().
				body("$", hasKey("Title")).
				body("$", hasKey("Year")).
				body("$", hasKey("Released")).
				contentType(ContentType.JSON).extract().response();

		String response = res.asString();
		System.out.println("Film found by Title: ");
		System.out.println(response);
		
	}

}
