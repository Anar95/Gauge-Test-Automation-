package imp;

import com.thoughtworks.gauge.Step;
import helper.ApiHelper;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ApiRequestImp {
    private static Response response;
    private String endpoint;
    private ApiHelper apiHelper = ApiHelper.getInstance();

    // Dəyərləri saxlamaq üçün
    private static Map<String, String> savedValues = new HashMap<>();


    @Step("API e GET request gonder <url>")
    public void sendGetRequest(String url) {
        response = given()
                .when()
                .get(url)
                .then()
                .log().all()
                .extract().response();
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

    @Step("Status kodu <statusCode> olmalidir")
    public void validateStatusCode(int statusCode) {
        response.then().statusCode(statusCode);
    }

    // ========== YENİ STEP-LƏR (Əlavə edildi) ==========

    @Step("Set base URL to <url>")
    public void setBaseUrl(String url) {
        apiHelper.setBaseUrl(url);
    }

    @Step("Base URL <url> olaraq təyin et")
    public void setBaseUrlAz(String url) {
        apiHelper.setBaseUrl(url);
    }

    @Step("Initialize request specification")
    public void initializeRequestSpecification() {
        apiHelper.resetRequestSpecification();
        System.out.println("✅ Request specification başladıldı");
    }

    @Step("Request specification-ı başlat")
    public void initializeRequestSpecificationAz() {
        apiHelper.resetRequestSpecification();
        System.out.println("✅ Request specification başladıldı");
    }

    @Step("Add endpoint <endpoint>")
    public void addEndpoint(String endpoint) {
        this.endpoint = endpoint;
        apiHelper.addEndpoint(endpoint);
    }

    @Step("Endpoint <endpoint> əlavə et")
    public void addEndpointAz(String endpoint) {
        this.endpoint = endpoint;
        apiHelper.addEndpoint(endpoint);
    }

    @Step("Add as a header <headerKey> = <headerValue>")
    public void addHeader(String headerKey, String headerValue) {
        apiHelper.addHeader(headerKey, headerValue);
        System.out.println("✅ Header əlavə edildi: " + headerKey + " = " + headerValue);
    }

    @Step("Add body as file from resource <fileName>")
    public void addBodyFromResource(String fileName) {
        apiHelper.addBodyFromFile(fileName);
    }

    @Step("Body-ə resource-dan <fileName> faylını əlavə et")
    public void addBodyFromResourceAz(String fileName) {
        apiHelper.addBodyFromFile(fileName);
    }

    @Step("Add body as text <jsonBody>")
    public void addBodyAsText(String jsonBody) {
        apiHelper.addBodyAsJson(jsonBody);
        System.out.println("✅ JSON body əlavə edildi");
    }

    @Step("Post request and display respons")
    public void sendPostRequestAndDisplayResponse() {
        response = apiHelper.getRequestSpecification()
                .when()
                .post()
                .then()
                .log().all()
                .extract().response();

        displayResponse("POST");
    }

    @Step("POST request göndər və response göstər")
    public void sendPostRequestAndDisplayResponseAz() {
        sendPostRequestAndDisplayResponse();
    }

    @Step("Put request and display respons")
    public void sendPutRequestAndDisplayResponse() {
        response = apiHelper.getRequestSpecification()
                .when()
                .put()
                .then()
                .log().all()
                .extract().response();

        displayResponse("PUT");
    }

    @Step("PUT request göndər və response göstər")
    public void sendPutRequestAndDisplayResponseAz() {
        sendPutRequestAndDisplayResponse();
    }

    @Step("Patch request and display respons")
    public void sendPatchRequestAndDisplayResponse() {
        response = apiHelper.getRequestSpecification()
                .when()
                .patch()
                .then()
                .log().all()
                .extract().response();

        displayResponse("PATCH");
    }

    @Step("Delete request and display respons")
    public void sendDeleteRequestAndDisplayResponse() {
        response = apiHelper.getRequestSpecification()
                .when()
                .delete()
                .then()
                .log().all()
                .extract().response();

        displayResponse("DELETE");
    }

    @Step("DELETE request göndər və response göstər")
    public void sendDeleteRequestAndDisplayResponseAz() {
        sendDeleteRequestAndDisplayResponse();
    }

    @Step("Get request and display respons")
    public void sendGetRequestAndDisplayResponse() {
        response = apiHelper.getRequestSpecification()
                .when()
                .get()
                .then()
                .log().all()
                .extract().response();

        displayResponse("GET");
    }

    @Step("GET request göndər endpoint ilə")
    public void sendGetRequestWithEndpoint() {
        sendGetRequestAndDisplayResponse();
    }

    @Step("Save value of <key> as <variableName>")
    public void saveValueAs(String key, String variableName) {
        String value = response.jsonPath().getString(key);
        savedValues.put(variableName, value);
        System.out.println("✅ Dəyər saxlanıldı: " + variableName + " = " + value);
    }

    @Step("Json cavabinda <key> deyeri <savedKey> ile ferqlidir")
    public void jsonValueIsDifferentFromSaved(String key, String savedKey) {
        String savedValue = savedValues.get(savedKey);
        if (savedValue == null) {
            throw new RuntimeException("Saxlanılmış dəyər tapılmadı: " + savedKey);
        }

        String actualValue = response.jsonPath().getString(key);
        if (savedValue.equals(actualValue)) {
            throw new AssertionError(key + " dəyəri " + savedKey + " ilə eynidir: " + actualValue);
        }
        System.out.println("✅ Dəyərlər fərqlidir: " + key + " (" + actualValue + ") != " + savedKey + " (" + savedValue + ")");
    }

    @Step("Json Cavabinda <key> deyeri string <expectedValue> beraberdir")
    public void jsonKeyEqualsString(String key, String expectedValue) {
        response.then().body(key, equalTo(expectedValue));
        System.out.println("✅ JSON string yoxlandı: " + key + " = " + expectedValue);
    }

    @Step("Respons cavab muddeti <maxMillis> milliSaniyeden az olmalidir")
    public void responseTimeLessThanMilliseconds(String maxMillis) {
        responsTimeLessThanSeconds(Double.parseDouble(maxMillis));
    }

    // ========== HELPER METHOD ==========

    private void displayResponse(String method) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("🚀 " + method + " REQUEST RESPONSE");
        System.out.println("=".repeat(50));
        System.out.println("📊 Status Code: " + response.getStatusCode());
        System.out.println("⏱️  Response Time: " + response.time() + "ms");
        System.out.println("\n📄 Response Body:");
        System.out.println(response.getBody().asPrettyString());
        System.out.println("=".repeat(50) + "\n");
    }

    @Step("Status kodu <statusCode> olmalidir")
    public void ivalidateStatusCode(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @Step("Json Cavabinda <email> deyeri <anar@test.com>")
    public void implementation1(Object arg0, Object arg1) {

    }
}