import com.thoughtworks.gauge.Step;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.not;

public class StepImplementation {

    private static Response response;
    private HashSet<Character> vowels;
    RequestSpecification requestSpec;
    private Map<String, Object> requestBody = new HashMap<>();
    private Map<String, Object> headers = new HashMap<>();

    @Step("Set base URL to <url>")
    public void setBaseURl(String url) {
        RestAssured.baseURI = url;
        System.out.println("Set base URL to " + url);
    }

    @Step("Initialize request specification")
    public void initializeRequestSpec() {
        requestSpec = given();
        headers.clear();
        requestBody.clear();
        System.out.println("Initialize request specification");
    }

    @Step("API e GET request gonder <url>")
    public void sendGetRequest(String url) {
        response = given()
                .when()
                .get(url)
                .then()
                .log().all()
                .extract().response();
    }

    @Step("Status kodu <statusCode> olmalidir")
    public void validateStatusCode(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @Step("Json Cavabinda <key> deyeri <value>")
    public void validJsonKeyValue(String key, String value) {
        try {
            // Əgər value ədəd isə int kimi yoxla
            int intValue = Integer.parseInt(value);
            response.then().body(key, equalTo(intValue));
        } catch (NumberFormatException e) {
            // Əgər ədəd deyilsə, string kimi yoxla
            response.then().body(key, equalTo(value));
        }
    }

    @Step("Json cavabinda <key> deyeri bos olmamalidir")
    public void jsonKeyShouldNotBeEmpty(String key) {
        response.then().body(key, not(isEmptyOrNullString()));
    }

    @Step("Header <headerKey> movcud olmalidir")
    public void headerShouldExist(String headerKey) {
        response.then().header(headerKey, notNullValue());
    }

    @Step("Body icinde <text>  metnini ehtiva etmelidir")
    public void bodyShouldConainsText(String text) {
        response.then().body(containsString(text));
    }

    @Step("Respons cavab muddeti <maxMillis> milliSaniyeden az olmalidir")
    public void responsTimeLessThanMillis(double maxMillis) {
        long responsTime = response.time(); // millisekund ilə
        System.out.println("Respons cavab muddeti " + responsTime + " ms");

        if (responsTime > maxMillis) {
            throw new AssertionError("Cavab cox gec geldi: " + responsTime +
                    " ms. Maksimum icazə verilən: " + maxMillis + " ms");
        }
    }

    @Step("Json cavabinda verilen <key> deyeri ededdir")
    public void jsonKeyShouldBeNumber(String key) {
        response.then().body(key, instanceOf(Number.class));
    }


    @Step("Add endpoint <endpoint>")
    public void addEndpoint(String endpoint) {
        requestSpec.basePath(endpoint);
        System.out.println("Add endpoint " + endpoint);

    }

    @Step("Add as a header <key> = <value>")
    public void addHeader(String key, String value) {
        requestSpec.header(key, value);
        System.out.println("Add header " + key + " = " + value);

    }

    @Step("Add body as file from resource <fileName>")
    public void addBodyFromFile(String fileName) throws IOException {
        String fullPath = "src/test/resources/body/" + fileName;
        //src/test/resources/body/ +  created-user.json
        System.out.println("Oxunan File " + fullPath);

        String body = new String(Files.readAllBytes(Paths.get(fullPath)));
        System.out.println("Body mezmunu " + body);

        requestSpec.body(body).contentType(ContentType.JSON);
    }

    @Step("Post request and display respons")
    public void sendPostRequest() {

        response = requestSpec.when().post()
                .then()
                .log()
                .all()
                .extract().response();

    }

    @Step("Add body as text <bodyText>")
    public void addBodyAsText(String bodyText) {
        requestSpec.body(bodyText).contentType(ContentType.JSON);
        System.out.println("Add body as " + bodyText);

    }

    @Step("Save value of <jsonPath> as <variableName>")
    public void saveJsonValue(String jsonPath, String variableName) {
        String value = response.jsonPath().getString(jsonPath);
        System.setProperty(variableName, value);
        System.out.println("Save value of " + variableName + " as " + value);

    }

    @Step("Json cavabinda <key> deyeri <variable> ile ferqlidir")
    public void jsonKeyNotEqualToVariable(String key, String variable) {
        String saveValue = System.getProperty(variable);
        response.then().body(key, not(saveValue));

    }

    @Step("Put request and display respons")
    public void sendPutRequest() {
        response = requestSpec.when().put()
                .then()
                .log()
                .all()
                .extract().response();

    }

    @Step("Json Cavabinda <key> deyeri string <expectedValue> beraberdir")
    public void jsonKeyEquals(String key, String expectedValue) {
        response.then().body(key, equalTo(expectedValue));

    }

    @Step("Json Cavabinda <key> deyeri integer <expectedValue> beraberdir")
    public void jsonKeyEquals(String key, int expectedValue) {
        response.then().body(key, equalTo(expectedValue));

    }

    @Step("Json Cavabinda <key> deyeri boolean <expectedValue> beraberdir")
    public void jsonKeyEquals(String key, boolean expectedValue) {
        response.then().body(key, equalTo(expectedValue));

    }

    @Step("Patch request and display respons")
    public void sendPatchRequest() {
        response = requestSpec.when().patch()
                .then()
                .log()
                .all()
                .extract().response();

    }

    @Step("Delete request and display respons")
    public void sendDelete() {
         response = requestSpec.when().delete()
                 .then()
                 .log()
                 .all()
                 .extract().response();
    }
}



