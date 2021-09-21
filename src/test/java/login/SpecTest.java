package login;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;

import io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class SpecTest {
	public static ResponseSpecification checkStatusCodeAndContentType = 
		    new ResponseSpecBuilder().
		        expectStatusCode(200).
		        expectContentType(ContentType.JSON).
		        build();
	
	 @BeforeClass
	    public static void setup() {
	        RestAssured.baseURI = "https://int-backend-stage.oto.com";
	    }

	    @Test
	    public static void getRequest() {
	    	Header h1= new Header("Accept", "*/*");
			Header h2 = new Header("Accept-Language", "en-US");
			Header h3 = new Header("User-Agent", "PostmanRuntime/7.28.4");
			Header h4= new Header("Content-Type", "application/json");
			Header h5= new Header("Host", "int-gcloud-stage.oto.com");
			Header h6= new Header("Content-Length", "<calculated when request is sent>");
			Header h7= new Header("Accept-Encoding", "gzip, deflate, br");
			Header h8= new Header("Connection", "keep-live");

			List<Header> list = new ArrayList<Header>();
			list.add(h1);
			list.add(h2);
			list.add(h3);
			list.add(h4);
			list.add(h5);
			//  list.add(h6);
		    //	list.add(h7);
			//  list.add(h8);

			Headers headers = new Headers(list);
			HashMap<String, String>  map = new HashMap<String,String>();
			map.put("mobile", "8888888888");

	    	
	        Response response = 
	        		given()
	        		.body(map)
	                .headers(headers)
	                .when()
	                .post("/loan/user/send_otp")
	                .then().spec(checkStatusCodeAndContentType).assertThat().statusCode(200).log().body()
	                .extract().response();
            //Assertion assertion = new Assertion();
	        //assertion.assertEquals(response.statusCode(), 200);
	          System.out.println(response.asString().contains("Success"));
	          System.out.println(response.asString());
	        
	    }
}
