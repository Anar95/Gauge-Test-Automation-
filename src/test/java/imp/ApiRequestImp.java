package imp;

import com.thoughtworks.gauge.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.notNullValue;

public class ApiRequestImp {
    private  static Response response;
    private  String  endpoint;

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

    @Step("Json Cavabinda <key> deyeri <value> olmalıdır")
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

    @Step("Header <headerKey> deyeri <expectedValue> olmalidir")
    public void headerValueShouldBe(String headerKey, String expectedValue) {
        response.then().header(headerKey, notNullValue());
    }

    @Step("Json Cavabinda <key> deyeri integer <expectedValue> beraberdir")
    public void jsonKeyEqualsInteger(String key, int expectedValue) {
        response.then().body(key, equalTo(expectedValue));

    }

    @Step("Json Cavabinda <key> deyeri boolean <expectedValue> beraberdir")
    public void jsonKeyEqualsBoolen(String key, boolean expectedValue) {
        response.then().body(key, equalTo(expectedValue));

    }

    @Step("Respons cavab müddeti <maxMillis> saniyeden az olmalıdır")
    public void responsTimeLessThanSeconds(double maxMillis) {

        long responseTime = response.time();
        double seconds = responseTime / 1000.0;
        System.out.println("Response time: " + seconds);
        if (responseTime > maxMillis) {
            throw new AssertionError("Response time is greater than " + maxMillis + "ms");
        }


    }

}
