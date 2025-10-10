package helper;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

import java.io.File;

import static io.restassured.RestAssured.given;

public class ApiHelper {
    // Singleton Patern

    // Bu patern sayesinde cox yerde eyni obyekti rahıtlıqla cagıra bilecik
    private static ApiHelper instance;
    private RequestSpecification requestSpecification;
    private String baseUrl;

    private ApiHelper() {
        requestSpecification = given();
    }

    public static ApiHelper getInstance() {
        if (instance == null) {
            instance = new ApiHelper();
        }
        return instance;
    }

    public RequestSpecification getRequestSpecification() {
        return requestSpecification;
    }

    public void resetRequestSpecification() {
        requestSpecification = given();
        if (baseUrl != null) {
            RestAssured.baseURI = baseUrl;
        }
    }

    // ========== YENİ METODLAR ==========

    /**
     * Base URL təyin etmək
     */
    public void setBaseUrl(String url) {
        this.baseUrl = url;
        RestAssured.baseURI = url;
        System.out.println(" Base URL: " + url);
    }

    /**
     * Endpoint əlavə etmək
     */
    public void addEndpoint(String endpoint) {
        requestSpecification = requestSpecification.basePath(endpoint);
        System.out.println(" Endpoint: " + endpoint);
    }

    /**
     * Header əlavə etmək
     */
    public void addHeader(String key, String value) {
        requestSpecification = requestSpecification.header(key, value);
        System.out.println(" Header: " + key + " = " + value);
    }

    /**
     * Body-ə JSON file əlavə etmək
     */
    public void addBodyFromFile(String fileName) {
        File file = new File("src/test/resources/" + fileName);
        if (!file.exists()) {
            throw new RuntimeException(" File tapılmadı: " + fileName);
        }
        requestSpecification = requestSpecification
                .header("Content-Type", "application/json")
                .body(file);
        System.out.println(" Body file: " + fileName);
    }

    /**
     * Body-ə JSON string əlavə etmək
     */
    public void addBodyAsJson(String jsonBody) {
        requestSpecification = requestSpecification
                .header("Content-Type", "application/json")
                .body(jsonBody);
        System.out.println("JSON body əlavə edildi");
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}