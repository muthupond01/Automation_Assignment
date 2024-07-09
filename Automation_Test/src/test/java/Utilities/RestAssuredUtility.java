package Utilities;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class RestAssuredUtility {

    // Base URI for the API
    private String baseUri;

    public RestAssuredUtility(String baseUri) {
        this.baseUri = baseUri;
    }

   
    // POST request
    public Response postRequest(String endpoint, Object body) {
    	System.out.println("endpoint"+endpoint);
    	System.out.println("body"+body);
        return RestAssured.given()
                .header("Content-Type", "application/json")
                .body(body.toString())
                .post();
    }

    
}
