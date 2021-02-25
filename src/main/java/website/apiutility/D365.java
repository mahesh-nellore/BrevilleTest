package website.apiutility;

import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.Status;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import website.base.BaseTest;

public class D365 extends BaseTest {

  public static String authTokenUri = "https://login.microsoftonline.com";
  public static String orderDetailsUri = "https://brevuat.sandbox.operations.dynamics.com/data/SalesOrderHeaders?";
  public static String orderLineDetailsUri = "https://brevuat.sandbox.operations.dynamics.com/data/SalesOrderLines?";
  public static Map < String,
  String > map = new HashMap < String,
  String > ();
  public static void main(String[] args) {
    String ord = "264297";
    getOrderDetails(ord);
    Map < String,
    String > orderDetails = getLineItemDetails();
    System.out.println("Print the Order Details Values: ");
    System.out.println("---------------------------------------");
    orderDetails.forEach((k, v) ->{
      System.out.println(k + " : " + v);
    });
    System.out.println("---------------------------------------");
    double tax_D365 = Double.parseDouble(map.get("lineItemTax_Water Kettles")) + Double.parseDouble(map.get("lineItemTax_Spare Parts"));
    System.out.println(Math.round(tax_D365 * 100.0) / 100.0);
  }

  public static String getAuthToken() {
    RestAssured.baseURI = authTokenUri;
    RequestSpecification request = RestAssured.given();
    Response res = request.headers("Content-Type", "multipart/form-data").multiPart("grant_type", "client_credentials").multiPart("resource", "https://brevuat.sandbox.operations.dynamics.com").multiPart("client_id", "b1bc0415-f2f9-45f2-9248-a11ada02f0c2").multiPart("client_secret", "Cs1/gCiUcQtgxOQ3nBL8483lyasYvpzudAGWGYks/sw=").pathParam("tenant_id", "3bc317b4-2fb3-45e8-9410-9824eeb72e16").post("/{tenant_id}/oauth2/token");
    int statusCode = res.getStatusCode();
    String accessToken = "";
    if (statusCode == 200) {
      logger.log(Status.PASS, "The Get Auth token API call is success and the status code is: " + statusCode);
      logger.log(Status.PASS, "The Auth token API call is success and the response is: " + res.getBody().asString());
      accessToken = res.jsonPath().getString("access_token");
    }
    else logger.log(Status.FAIL, "The Auth token API call is failed and the status code is: " + statusCode);

    return accessToken;

  }

  public static boolean getOrderDetails(String ord) {
    String token = getAuthToken();
    RestAssured.baseURI = orderDetailsUri;
    RequestSpecification request = RestAssured.given();
    Map < String,
    String > queryParam = new HashMap < String,
    String > ();
    queryParam.put("$filter", "CustomerRequisitionNumber eq '" + ord + "' and dataAreaId eq 'bus'");
    queryParam.put("cross-company", "true");
    request.queryParams(queryParam);
    request.headers("Authorization", "Bearer " + token);
    Response res = request.get();
    int statusCode = res.getStatusCode();
    if (statusCode == 200) {
      logger.log(Status.PASS, "The Get Order Details API call is success and the status code is: " + statusCode);
      logger.log(Status.PASS, "The Get Order Details API call is success and the response is: " + res.getBody().asString());
      System.out.println("The Order Details are added to the Map");
      map.put("salesOrderNumber", res.jsonPath().get("value[0].SalesOrderNumber"));
      map.put("customerRequisitionNumber", res.jsonPath().get("value[0].CustomerRequisitionNumber"));
      map.put("customerOrderReferenceNumber", res.jsonPath().get("value[0].CustomersOrderReference"));
      map.put("shippingAddress", res.jsonPath().get("value[0].FormattedDelveryAddress"));
      map.put("carrierId", res.jsonPath().get("value[0].ShippingCarrierId"));
      map.put("carrierService", res.jsonPath().get("value[0].ShippingCarrierServiceId"));
      map.put("deliveryTermsCode", res.jsonPath().get("value[0].DeliveryTermsCode"));
      map.put("Language", res.jsonPath().get("value[0].LanguageId"));
      map.put("customerPaymentMethodName", res.jsonPath().get("value[0].CustomerPaymentMethodName"));
      map.put("requestedShippingDate", res.jsonPath().get("value[0].RequestedShippingDate"));
      map.put("deliveryAddressCity", res.jsonPath().get("value[0].DeliveryAddressCity"));
      map.put("deliveryAddressZipCode", res.jsonPath().get("value[0].DeliveryAddressZipCode"));
      map.put("deliveryAddressStreet", res.jsonPath().get("value[0].DeliveryAddressStreet"));

    } else logger.log(Status.FAIL, " Get order details API call is failed");
    return true;

  }
  public static Map < String,
  String > getLineItemDetails() {
    String token = getAuthToken();
    RestAssured.baseURI = orderLineDetailsUri;
    RequestSpecification request = RestAssured.given();
    Map < String,
    String > queryParam = new HashMap < String,
    String > ();
    queryParam.put("$filter", "SalesOrderNumber eq '" + map.get("salesOrderNumber") + "' and dataAreaId eq 'bus'");
    queryParam.put("cross-company", "true");
    request.queryParams(queryParam);
    request.headers("Authorization", "Bearer " + token);
    Response res = request.get();
    int statusCode = res.getStatusCode();
    if (statusCode == 200) {
      logger.log(Status.PASS, "The Get line item details API call is success and the status code is: " + statusCode);
      logger.log(Status.PASS, "The Get line item API call is success and the response is: " + res.getBody().asString());
      for (int i = 0; i < res.jsonPath().getList("value").size(); i++) {
        String productType = res.jsonPath().get("value[" + i + "].SalesProductCategoryName").toString();
        Object lineTax = res.jsonPath().get("value[" + i + "].AVAEDILineTaxAmtCur");
        String lineTaxValue = String.valueOf(lineTax);
        Object lineDiscount = res.jsonPath().get("value[" + i + "].AVAEDILineDiscount");
        String lineDiscountValue = String.valueOf(lineDiscount);
        map.put("lineAmount_" + productType, res.jsonPath().get("value[" + i + "].AVAEDIPrice").toString());
        map.put("lineItemTax_" + productType, lineTaxValue);
        map.put("lineItemDiscount_" + productType, lineDiscountValue);
        map.put("quantity_" + productType, Integer.toString(res.jsonPath().get("value[" + i + "].OrderedSalesQuantity")));

      }

    } else logger.log(Status.FAIL, "The Get line items order details API call is failed..");
    return map;

  }

}