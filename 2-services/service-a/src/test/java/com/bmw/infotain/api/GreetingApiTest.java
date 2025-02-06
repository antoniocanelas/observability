package poc.observability.opentelemetry.api;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class GreetingApiTest {

    @Test
    void testHelloEndpoint() {
        given()
                .header("correlationId", "12345")
                .queryParam("language", "en")
                .body("John")
                .when().post("/service-a/local")
                .then()
                .statusCode(200)
                .body(is("* Service A*\n" +
                        "correlationId: 12345\n" +
                        "language: en\n" +
                        "name: John"));
    }

}