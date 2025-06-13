package nl.cge;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class GreetingResourceTest {
    @Test
    void testHelloEndpoint() {
        given()
          .when().get("/hello")
          .then()
             .statusCode(200)
             .body(is("Hello from Quarkus REST"));
    }

    @Test
    void testGreetingPost() {
        String partOfDay = calculatePartOfDay();

        given()
            .contentType("application/json")
            .body("{\"name\":\"Codex\"}")
            .when().post("/hello")
            .then()
                .statusCode(200)
                .body("message", is("Hello Codex, een hele goede " + partOfDay));
    }

    private String calculatePartOfDay() {
        int hour = LocalTime.now().getHour();
        if (hour < 12) {
            return "ochtend";
        }
        if (hour < 18) {
            return "middag";
        }
        return "avond";
    }
}
