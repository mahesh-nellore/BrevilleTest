package website.apiutility;

import java.util.HashMap;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class D365 {

	public static String authTokenUri = "https://login.microsoftonline.com";
	public static String orderDetailsUri = "https://brevuat.sandbox.operations.dynamics.com/data/SalesOrderHeaders?";
	public static String orderLineDetailsUri = "https://brevuat.sandbox.operations.dynamics.com/data/SalesOrderLines?";
	public static Map<String, String> map = new HashMap<String,String>();
	public static void main(String[] args) {
		String ord = "263907";
		getOrderDetails(ord);
		Map<String, String> orderDetails = getLineItemDetails();
		System.out.println("Print the Order Details Values: ");
		System.out.println("---------------------------------------");
		orderDetails.forEach((k, v) ->{
			System.out.println(k+" : "+v);
		});
		System.out.println("---------------------------------------");

	}
	
	public static String getAuthToken() {
		 RestAssured.baseURI = authTokenUri;
		    RequestSpecification request = RestAssured.given();   
		    Response res = request.headers("Content-Type", "multipart/form-data").multiPart("grant_type", "client_credentials")
		    				.multiPart("resource", "https://brevuat.sandbox.operations.dynamics.com")
		    				.multiPart("client_id", "b1bc0415-f2f9-45f2-9248-a11ada02f0c2")
		    				.multiPart("client_secret", "Cs1/gCiUcQtgxOQ3nBL8483lyasYvpzudAGWGYks/sw=")
		    				.pathParam("tenant_id", "3bc317b4-2fb3-45e8-9410-9824eeb72e16").post("/{tenant_id}/oauth2/token");
		    int statusCode = res.getStatusCode();
		    String accessToken ="";
		    if(statusCode == 200) {
		    	accessToken = res.jsonPath().getString("access_token");
		    	System.out.println("The Auth Token API call is Success and the token is: "+ accessToken);
		    }	    	
		    else
		    	System.out.println("The Auth token API call is failed and the status code is: "+statusCode);
		     
		    return accessToken;
		    
	}
	
	public static void getOrderDetails(String ord) {
		String token = getAuthToken();
		 RestAssured.baseURI = orderDetailsUri;
		    RequestSpecification request = RestAssured.given(); 
		    Map < String,
		    String > queryParam = new HashMap < String,
		    String > ();
		    queryParam.put("$filter", "CustomerRequisitionNumber eq '"+ord+"' and dataAreaId eq 'bus'");
		    queryParam.put("cross-company", "true");
		    request.queryParams(queryParam);
		    request.headers("Authorization", "Bearer " + token);
		    Response res = request.get();
		    int statusCode = res.getStatusCode();
		    if(statusCode == 200) {
		    	System.out.println("The Get Order Details API call is success and the status code is: "+statusCode);
		    	System.out.println("The Order Details are added to the Map");
		    	map.put("salesOrderNumber", res.jsonPath().get("value[0].SalesOrderNumber"));
		    	map.put("customerRequisitionNumber", res.jsonPath().get("value[0].CustomerRequisitionNumber"));
		    	map.put("customerOrderReferenceNumber: ", res.jsonPath().get("value[0].CustomersOrderReference"));
		    	map.put("shippingAddress", res.jsonPath().get("value[0].FormattedDelveryAddress"));
		    	map.put("carrierId", res.jsonPath().get("value[0].ShippingCarrierId"));
		    	map.put("carrierService", res.jsonPath().get("value[0].ShippingCarrierServiceId"));
		    	map.put("deliveryTermsCode", res.jsonPath().get("value[0].DeliveryTermsCode"));
		    	map.put("Language", res.jsonPath().get("value[0].LanguageId"));
		    	map.put("customerPaymentMethodName", res.jsonPath().get("value[0].CustomerPaymentMethodName"));
		    	map.put("requestedShippingDate", res.jsonPath().get("value[0].RequestedShippingDate"));	
		    	
		    }
		    
		
	}
	public static Map<String, String> getLineItemDetails() {
		String token = getAuthToken();
		 RestAssured.baseURI = orderLineDetailsUri;
		    RequestSpecification request = RestAssured.given(); 
		    Map < String,
		    String > queryParam = new HashMap < String,
		    String > ();
		    queryParam.put("$filter", "SalesOrderNumber eq '"+map.get("salesOrderNumber")+"' and dataAreaId eq 'bus'");
		    queryParam.put("cross-company", "true");
		    request.queryParams(queryParam);
		    request.headers("Authorization", "Bearer " + token);
		    Response res = request.get();
		    int statusCode = res.getStatusCode();
		    if(statusCode == 200) {
		    	System.out.println("Get line item details API call is Success and the status code is: "+statusCode);
		    	System.out.println("The Line item Details are added to the Map");
		    	map.put("lineAmount", res.jsonPath().get("value[0].AVAEDIPrice").toString());
		    	map.put("lineItemTax", Integer.toString(res.jsonPath().get("value[0].AVAEDILineTaxAmtCur")));
		    	map.put("lineItemDiscount", Integer.toString(res.jsonPath().get("value[0].AVAEDILineDiscount")));
		    	
		    }return map;
		    
		
	}

}