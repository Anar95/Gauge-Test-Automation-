import com.thoughtworks.gauge.Step;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

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
}
