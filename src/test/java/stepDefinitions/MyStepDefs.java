package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;

import java.io.File;

import static io.restassured.RestAssured.*;
public class MyStepDefs {
    @Given("The API endpoint is {string}")
    public void theAPIEndpointIs(String url) {
        baseURI = url;
    }
    @When("A GET request is sent")
    public void aGETRequestIsSent() {
        given().get("/users?page=2")
                .then().log().all();
    }
    @Then("Status response is {int}")
    public void statusResponseIs(int arg0) {
        Response response = given().baseUri("https://reqres.in/api").and().basePath("/users?page=2").when().get().thenReturn();
        int status = response.getStatusCode();
        if(status == arg0)
        {
            Assert.assertTrue(true);
            System.out.println(status + " Is the code of the response");
        } else
        {
            Assert.assertTrue(false);
        }
    }
    @When("A POST request is sent")
    public void aPOSTRequestIsSent() {
        //Json File
        File file = new File("C:\\Users\\dialt\\OneDrive\\Desktop\\APITest.json");
        JsonPath jsonPath = new JsonPath(file);
        String Name = jsonPath.getString("Name");
        String Title = jsonPath.getString("Title");
        System.out.println(Name);
        System.out.println(Title);
        given()
                .body(Name)
                .body(Title)
                .when()
                .post("/users")
                .then()
                .log()
                .status();
    }
    @Then("Status response is {int} for post")
    public void statusResponseIsForPost(int arg0) {
        File file = new File("C:\\Users\\dialt\\OneDrive\\Desktop\\APITest.json");
        JsonPath jsonPath = new JsonPath(file);
        String Name = jsonPath.getString("Name");
        String Title = jsonPath.getString("Title");
        Response response = given().body(Name).body(Title).when().post("/users").thenReturn();
        int status = response.getStatusCode();
        if(status == arg0)
        {
            Assert.assertTrue(true);
            System.out.println(status + " Is the code of the response");
        } else
        {
            Assert.assertTrue(false);
            System.out.println("Invalid status code");
        }
    }
}