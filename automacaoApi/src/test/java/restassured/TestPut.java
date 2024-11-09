package restassured;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.junit.Test;

public class TestPut {
    String baseURI = "https://reqres.in";

    @Test
    public void testPut() {
        JSONObject myJson = new JSONObject();
        myJson.put("updatedAt", "String Alterada");

        given().
                when().
                contentType("application/json").
                body(myJson.toString()).
                put(baseURI+"/api/users/1").
                then().
                statusCode(200).body(containsString("updatedAt"));
    }

}
