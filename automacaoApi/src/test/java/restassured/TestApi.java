package restassured;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class TestApi {

    String baseURI = "https://reqres.in";
    static ExtentReports extent;
    static ExtentTest test;

    @BeforeAll
    public static void setup() {
        // Configurar o reporter HTML
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("Report.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @Test
    public void testGet() {
        test = extent.createTest("Execução do teste de API GET");

        Response response = given().
                when().
                get(baseURI+"/api/users").
                then().
                extract().
                response();

        assertEquals(200, response.getStatusCode());
        assertThat(response.body().path("data[4].first_name"), containsString("Charles"));

        test.pass("API chamada com sucesso.");
        test.info("Status Code: " + response.getStatusCode());
        test.info("Response Body: " + response.getBody().asString());


    }

    @Test
    public void testPost() {
        test = extent.createTest("Execução do teste de API POST");

        JSONObject myJson = new JSONObject();
        myJson.put("name", "Willgner");
        myJson.put("job", "QA");

        Response response = given().
                contentType("application/json").
                body(myJson.toString()).
                when().
                post(baseURI+"/api/users").
                then().
                extract().
                response();

        assertEquals(201, response.getStatusCode());
        assertThat(response.body().path("name"), containsString("Willgner"));
        assertThat(response.body().path("job"), containsString("QA"));

        test.pass("API chamada com sucesso.");
        test.info("Status Code: " + response.getStatusCode());
        test.info("Response Body: " + response.getBody().asString());
    }

    @Test
    public void testDelete() {
        test = extent.createTest("Execução do teste de API DELETE");

        Response response = given().
                when().
                delete(baseURI + "/api/users/1").
                then().
                extract().
                response();

        assertEquals(204, response.getStatusCode());
        test.pass("API chamada com sucesso.");
        test.info("Status Code: " + response.getStatusCode());
        test.info("Response Body: " + response.getBody().asString());

    }

    @Test
    public void testPut() {
        test = extent.createTest("Execução do teste de API PUT");

        JSONObject myJson = new JSONObject();
        myJson.put("updatedAt", "String Alterada");

        Response response = given().
                when().
                contentType("application/json").
                body(myJson.toString()).
                put(baseURI+"/api/users/1").
                then().
                extract().
                response();

        assertEquals(200, response.getStatusCode());
        assertThat(response.getBody().asString(), containsString("updatedAt"));
        test.pass("API chamada com sucesso.");
        test.info("Status Code: " + response.getStatusCode());
        test.info("Response Body: " + response.getBody().asString());

    }

    @AfterAll
    public static void tearDown() {
        // Salvar o relatório
        extent.flush();
    }
}

