package website.apiutility;

import java.util.HashMap;
import java.util.Map;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import website.apiactions.Email;
import website.base.BaseTest;

public class VerifyEmailAPIs extends BaseTest {

  public static void main(String[] args) {
    String accessTokenUri = "https://mybreville--full.my.salesforce.com/services/oauth2/token?";
    String userIdUri = "https://mybreville--full.my.salesforce.com/services/data/v49.0/query/?";
    String verifyEmailUri = "https://full-mybreville.cs79.force.com/BrevilleCustomerCommunity/services/data/v48.0/sobjects/User/";
    String accessToken = getAccessToken(accessTokenUri);
    String email = "de.user02@yopmail.com";
    String userID = getUserID(userIdUri, email, accessToken);
    doVerifyEmail(verifyEmailUri, accessToken, userID);
  }

  public static String getAccessToken(String uri) {
    RestAssured.baseURI = uri;
    RequestSpecification request = RestAssured.given();
    Map < String,
    String > map = new HashMap < String,
    String > ();
    map.put("grant_type", "password");
    map.put("client_id", "3MVG98im9TK34CUU_AvwaugYPyH8o9GcjScMIC684sxOhT6y_95sDMoZKoD8W2voHU4tFfyOFVnRQrq1HTmqI");
    map.put("client_secret", "5CB7A12F3BBC7162818E922C39CE3BD989D10DED6610919E32C7110465D335B3");
    map.put("username", "breville_nirman_team@nirman.io.full");
    map.put("password", "why.fun-12");
    Response res = request.queryParams(map).body("").post();
    String accessToken = res.jsonPath().getString("access_token");
    System.out.println(accessToken);
    return accessToken;
  }

  public static String getUserID(String uri, String email, String token) {
    RestAssured.baseURI = uri;
    RequestSpecification request = RestAssured.given();
    Map < String,
    String > map = new HashMap < String,
    String > ();
    map.put("q", "SELECT id from user where email='" + email + "'");
    request.queryParams(map);
    request.headers("Authorization", "Bearer " + token);
    request.headers("Content-Type", "application/json");
    Response res = request.get();
    System.out.println(res.jsonPath().getString("records[0].Id"));
    return res.jsonPath().getString("records[0].Id");

  }

  public static int doVerifyEmail(String uri, String token, String userID) {
    RestAssured.baseURI = uri + userID;
    RequestSpecification request = RestAssured.given();
    request.headers("Authorization", "Bearer " + token);
    request.headers("Content-Type", "application/json");
    Email email = new Email();
    email.setCommunity_Email_Verified__c("true");
    Response res = request.body(email).patch();
    System.out.println(res.getStatusCode());
    return res.getStatusCode();
  }
}