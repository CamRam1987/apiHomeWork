 package Requests;

import io.restassured.response.Response;
import org.testng.Assert;
import utils.SetProperties;

import static io.restassured.RestAssured.given;

public class GetRequests extends SetProperties {
    private String token_path="authentication/token/new";
    private String listDetails="/list/";
    PostRequests postRequests;
    public GetRequests() {
        super();
    }

    public String getToken(){
    Response response = given()
            .queryParam("api_key", getApi_key())
            .when()
            .get(getUrl_host()+token_path)
            .then()
            .statusCode(200)
            .log()
            .body()
            .extract()
            .response();
        Assert.assertEquals("true",response.jsonPath().getString("success"));
        return response.jsonPath().getString("request_token");
    }
    public void getListDetails(int listID){
    Response response=given()
            .queryParam("api_key",getApi_key())
            .when()
            .get(getUrl_host()+listDetails+listID)
            .then()
            .statusCode(200)
            .log()
            .body()
            .extract()
            .response();
        Assert.assertEquals("camilo.alberto.ramirez",response.jsonPath().getString("created_by"));

    }
}
