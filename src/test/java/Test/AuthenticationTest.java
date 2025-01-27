package Test;

import Base.BaseTests;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;


public class AuthenticationTest extends BaseTests {


    @Test
    public void authenticate() {
        // Hardcoded JSON payload with admin credentials
        String payload = "{\n" +
                "  \"username\": \"admin\",\n" +
                "  \"password\": \"admin\"\n" +
                "}";

        // Send the POST request with the JSON payload
        Response response = RestAssured.given()
                .header("Content-Type", "application/json") // Set Content-Type to application/json
                .body(payload) // Include the JSON body
                .post("/authenticate"); // Authentication endpoint

        // Validate the response status code
        response.then().statusCode(200);

        // Return the token from the response
        //return response.jsonPath().getString("token");
        //return payload;
    }

}

