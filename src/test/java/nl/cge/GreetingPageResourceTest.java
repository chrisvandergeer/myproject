package nl.cge;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
class GreetingPageResourceTest {

    @Test
    void testGetPage() {
        given()
          .when().get("/greeting")
          .then()
             .statusCode(200)
             .body(containsString("<form"));
    }

    @Test
    void testPostName() {
        String partOfDay = calculatePartOfDay();

        given()
            .contentType("application/x-www-form-urlencoded")
            .formParam("name", "Codex")
            .when().post("/greeting")
            .then()
                .statusCode(200)
                .body(containsString("Hello Codex, een hele goede " + partOfDay));
    }

    private String calculatePartOfDay() {
        int hour = java.time.LocalTime.now().getHour();
        if (hour < 12) return "ochtend";
        if (hour < 18) return "middag";
        return "avond";
    }
}
