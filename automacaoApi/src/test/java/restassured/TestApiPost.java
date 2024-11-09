package restassured;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.json.JSONObject;
import org.junit.Test;

public class TestApiPost {
    String baseURI = "https://reqres.in";

    @Test
    public void testPost() {
        JSONObject myJson = new JSONObject();
        myJson.put("name", "Willgner");
        myJson.put("job", "QA");

        given().
                contentType("application/json").
                body(myJson.toString()).
                when().
                post(baseURI+"/api/users").
                then().
                statusCode(201).
                body("name", equalTo("Willgner")).
                body("job", equalTo("QA"));
    }

}