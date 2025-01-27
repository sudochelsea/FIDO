package Test;

import io.qameta.allure.*;
import io.qameta.allure.restassured.AllureRestAssured;
import Base.BaseTests;
import Base.UtilsTests;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;



public class VideoGamesTests extends BaseTests {


        @Test

        @Epic("REST API Regression Testing using TestNG")
        @Feature("Verify that the Get and POST API returns correctly")
        @Story("GET Request with Valid post id")
        @Severity(SeverityLevel.NORMAL)
        @Description("Test Description : Verify that the GET API returns correctly")
        public void testGetAllVideoGames() {

            RestAssured.given()
                    .header("Authorization", "Bearer " + UtilsTests.authenticate("admin", "admin"))
                    .when()
                    .get("/videogame")
                    .then()
                    .statusCode(200)
                    .body("size()", greaterThan(0));
        }

        @Test
        public void testCreateVideoGame() {
            String payload = "{\n" +

                    "  \"category\": \"Platform\",\n" +
                    "  \"id\": 1,\n" +
                    "  \"name\": \"Test Game\",\n" +
                    "  \"rating\": \"Mature\",\n" +
                    "  \"releaseDate\": \"2023-01-01\",\n" +
                    "  \"reviewScore\": 90\n" +

                    "}";

            RestAssured.given()
                    .header("Authorization", "Bearer " + UtilsTests.authenticate("admin", "admin"))
                    .header("Content-Type", "application/json")
                    .body(payload)
                    .when()
                    .post("/videogame")
                    .then()
                    .statusCode(200);
                    //.body("id", equalTo(101))
                    //.body("name", equalTo("Test Game"));
        }

        @Test
        public void testDeleteVideoGame() {
            RestAssured.given()
                    .header("Authorization", "Bearer " + UtilsTests.authenticate("admin", "admin"))
                    .when()
                    .delete("/videogame/1" +"")
                    .then()
                    .statusCode(200);
        }
    }

