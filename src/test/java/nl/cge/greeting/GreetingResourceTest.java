package nl.cge.greeting;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
class GreetingResourceTest {

    @Test
    void testGetGreeting() {
        String partOfDay = calculatePartOfDay();

        given()
          .queryParam("name", "Codex")
          .when().get("/api/greeting")
          .then()
             .statusCode(200)
             .body("message", containsString("Codex, een hele goede " + partOfDay));
    }

    @Test
    void testPostName() {
        String partOfDay = calculatePartOfDay();

        given()
            .contentType("application/json")
            .body("{\"name\":\"Codex\"}")
            .when().post("/api/greeting")
            .then()
                .statusCode(200)
                .body("message", containsString("Codex, een hele goede " + partOfDay));
    }

    private String calculatePartOfDay() {
        int hour = java.time.LocalTime.now().getHour();
        if (hour < 12) return "ochtend";
        if (hour < 18) return "middag";
        return "avond";
    }
}
