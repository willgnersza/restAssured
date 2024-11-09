package restassured;

import static io.restassured.RestAssured.*;

import org.junit.Test;

public class TestDelete {
    String baseURI = "https://reqres.in";

    @Test
    public void testDelete() {

        given().
                when().
                delete(baseURI + "/api/users/1").
                then().
                statusCode(204);
    }

}
