package login;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;

public class Login {

	@Test
	public void test() {

		Header h1= new Header("Accept", "*/*");
		Header h2 = new Header("Accept-Language", "en-US");
		Header h3 = new Header("User-Agent", "PostmanRuntime/7.28.4");
		Header h4= new Header("Content-Type", "application/json");
		Header h5= new Header("Host", "int-gcloud-stage.oto.com");
		Header h6= new Header("Content-Length", "<calculated when request is sent>");
		Header h7= new Header("Accept-Encoding", "gzip, deflate, br");
		Header h8= new Header("Connection", "keep-live");
		Header h9 = new Header("Postman-Token"," bf2fc289-9ada-43c5-8a29-1fac66da7f86");

		List<Header> list = new ArrayList<Header>();
		list.add(h1);
		list.add(h2);
		list.add(h3);
		list.add(h4);
		list.add(h5);
		//  list.add(h6);
		//list.add(h7);
		//list.add(h8);
		//list.add(h9);

		Headers headers = new Headers(list);

		HashMap<String, String>  map = new HashMap<String,String>();
		map.put("mobile", "8888888888");

		JSONObject json = new JSONObject(map);
		System.out.println(json);

		RestAssured.baseURI="http://int-backend-stage.oto.com/";
	String response = 	given().
		//formParams(map).
		//contentType(ContentType.JSON).
		
		headers(headers).
		body(map).
		when().log().all().
		post("loan/user/send_otp").
		//		then().log().all();
		then()
		/*
		 * .and().contentType(ContentType.JSON).and(). body("status",equalTo("OK"))
		 */.log().all().extract().asString();
	System.out.println(response);
	}

//	@Test
//	public void test_ResponseHeaderData_ShouldBeCorrect() {
//	        
//	    given().
//	    when().
//	        get("http://ergast.com/api/f1/2017/circuits.json").
//	    then().
//	        assertThat().
//	        statusCode(200).
//	    and().
//	        contentType(ContentType.JSON).log().all();
//	}

}
