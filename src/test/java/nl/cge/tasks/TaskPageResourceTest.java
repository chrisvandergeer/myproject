package nl.cge.tasks;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
class TaskPageResourceTest {

    @Test
    void testGetPage() {
        given()
          .when().get("/tasks")
          .then()
             .statusCode(200)
             .body(containsString("<form"));
    }

    @Test
    void testPostTask() {
        given()
            .contentType("application/x-www-form-urlencoded")
            .formParam("name", "Test")
            .formParam("description", "Beschrijving")
            .formParam("endDate", "2030-01-01")
            .when().post("/tasks")
            .then()
                .statusCode(200)
                .body(containsString("Test"))
                .body(containsString("Beschrijving"))
                .body(containsString("2030-01-01"));
    }
}
