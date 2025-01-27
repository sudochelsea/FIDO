package Test;

import io.qameta.allure.*;
import io.qameta.allure.restassured.AllureRestAssured;
import Base.BaseTests;
import Base.UtilsTests;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;



public class VideoGamesTests extends BaseTests {


        @Test
        @Description("Verify that we can retrieve all Video games")
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
        @Description("Verify that we can add a new video game")
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
                    .statusCode(200)
                  //  .body("id", equalTo(1))
                    .body("name", equalTo("Test Game"));
        }

        @Test
        @Description("Verify that we can delete a  video game")
        public void testDeleteVideoGame() {
            RestAssured.given()
                    .header("Authorization", "Bearer " + UtilsTests.authenticate("admin", "admin"))
                    .when()
                    .delete("/videogame/1" +"")
                    .then()
                    .statusCode(200);
        }

    @Test
    @Description("Verify that we can update an existing video game")
    public void testUpdateVideoGame() {
        // Define the updated payload
        String updatedPayload = "{\n" +
                "  \"category\": \"Action\",\n" +
                "  \"id\": 1,\n" +
                "  \"name\": \"Updated Test Game\",\n" +
                "  \"rating\": \"Teen\",\n" +
                "  \"releaseDate\": \"2024-01-01\",\n" +
                "  \"reviewScore\": 85\n" +
                "}";

        // Send the PUT request to update the video game
        RestAssured.given()
                .header("Authorization", "Bearer " + UtilsTests.authenticate("admin", "admin"))
                .header("Content-Type", "application/json")
                .body(updatedPayload)
                .when()
                .put("/videogame/1")
                .then()
                .statusCode(200) // Verify that the status code is 200
                .body("id", equalTo(1)) // Verify the ID is unchanged
                .body("name", equalTo("Updated Test Game")) // Verify the updated name
                .body("category", equalTo("Action")) // Verify the updated category
                .body("rating", equalTo("Teen")) // Verify the updated rating
                .body("releaseDate", equalTo("2024-01-01")) // Verify the updated release date
                .body("reviewScore", equalTo(85)); // Verify the updated review score
    }





}

