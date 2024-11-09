package restassured;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

public class TestApiGet {
    String baseURI = "https://reqres.in";

    @Test
    public void testGet() {

        given().
                when().
                get(baseURI+"/api/users").
                then().
                statusCode(200).
                body("data[4].first_name",containsString("Charles"));
    }

}
