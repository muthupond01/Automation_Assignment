package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.RestAssured;
import org.junit.Assert;

import Utilities.RestAssuredUtility;

import static org.junit.Assert.assertNotNull;

import org.json.JSONObject;
import static com.jayway.jsonpath.JsonPath.read;


public class AddDeviceStep {
    private String baseUri = "https://api.restful-api.dev";
    private Response response;
    private RequestSpecification request;
    
    RestAssuredUtility restassured=new RestAssuredUtility(this.baseUri);

    @Given("user has access to endpoint {string}")
    public void user_has_access_to_endpoint(String endpoint) {
        RestAssured.baseURI = baseUri + endpoint;
    }

    @When("user adds a new device {string} {string} {string} {string} {string}")
    public void user_adds_a_new_device(String name, String year, String price, String cpumodel, String harddisksize) {
    	JSONObject dataObject = new JSONObject();
        dataObject.put("year", Integer.valueOf(year));
        dataObject.put("price", Float.valueOf(price));
        dataObject.put("CPU model", cpumodel);
        dataObject.put("Hard disk size", harddisksize);

        // Create the main JSON object and put the "data" object inside it
        JSONObject mainObject = new JSONObject();
        mainObject.put("name", name);
        mainObject.put("data", dataObject);
        response= restassured.postRequest(RestAssured.baseURI, mainObject);
 
    }

    @Then("user should get the response code {int}")
    public void user_should_get_the_response_code(int statusCode) {
        Assert.assertEquals(statusCode, response.getStatusCode());
    }
    
    @When("user verifies the new device added {string} {string} {string} {string} {string} in response")
    public void user_verifies_add_a_new_device(String name, String year, String price, String cpumodel, String harddisksize) {
    	String responseBody = response.getBody().asString();
        String res_name = read(responseBody, "$.name");
        int res_year = read(responseBody, "$.data.year");
        double res_price = read(responseBody, "$.data.price");
        String res_cpuModel = read(responseBody, "$.data['CPU model']");
        String res_hardDiskSize = read(responseBody, "$.data['Hard disk size']");
        String res_id=read(responseBody,"$.id");
        String res_createdAt=read(responseBody,"$.createdAt");
        
        Assert.assertEquals(res_name, name);
        Assert.assertTrue(res_year==Integer.valueOf(res_year));
        Assert.assertTrue(res_price==Double.valueOf(price));
        Assert.assertEquals(res_cpuModel, cpumodel);
        Assert.assertEquals(res_hardDiskSize, harddisksize);
        
    	assertNotNull("Booking ID missing", res_id);
    	assertNotNull("Booking ID missing", res_createdAt);

    	
    }

}
