package helper;

import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ApiHelper {
  // Singleton Patern

    // Bu patern sayesinde cox yerde eyni obyekti rahıtlıqla cagıra bilecik
    private static ApiHelper instance;
    private RequestSpecification requestSpecification;

    private ApiHelper() {requestSpecification= given();}


    public static ApiHelper getInstance() {
        if (instance == null) {
            instance = new ApiHelper();
        }
        return instance;
    }

    public RequestSpecification getRequestSpecification() {
        return requestSpecification;
    }

    public void  resetRequestSpecification() {requestSpecification= given();}

}
