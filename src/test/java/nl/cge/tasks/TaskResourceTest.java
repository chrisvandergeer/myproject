package nl.cge.tasks;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

@QuarkusTest
class TaskResourceTest {

    @Test
    void testGetTasks() {
        given()
          .when().get("/api/tasks")
          .then()
             .statusCode(200);
    }

    @Test
    void testPostTask() {
        given()
            .contentType("application/json")
            .body("{\"name\":\"Test\",\"description\":\"Beschrijving\",\"endDate\":\"2030-01-01\"}")
            .when().post("/api/tasks")
            .then()
                .statusCode(200)
                .body(hasSize(1))
                .body("[0].name", equalTo("Test"))
                .body("[0].description", equalTo("Beschrijving"))
                .body("[0].endDate", equalTo("2030-01-01"));
    }
}
