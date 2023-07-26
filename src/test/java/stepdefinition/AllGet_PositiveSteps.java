package stepdefinition;

import static io.restassured.RestAssured.given;
import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import utilities.API_enum;
import utilities.ExcelWriter;
import utilities.Utils;

public class AllGet_PositiveSteps extends Utils {

	ResponseSpecification res1;
	RequestSpecification req1;
	Response response;

	public static String userRoleid_env,uid,roleid,rolestatus;
	ExcelWriter xlwrite = new ExcelWriter("./src/test/resources/Response_data.xlsx");
	@Given("User creates GET Request for the LMS API endpoint")
	public void user_creates_get_request_for_the_lms_api_endpoint() throws IOException {
		req1 = given().spec(requestSpecification());
	}



	@When("User calls {string} with {string} HTTPS Request for get")
	public void user_calls_with_https_request_for_get(String resource, String method)
	{
		API_enum resource_api = API_enum.valueOf(resource);
		if (method.equalsIgnoreCase("getAll"))
		{
		response = req1.get(resource_api.getresource());
		}
		else if (method.equalsIgnoreCase("GetstudentID"))
		{
		response = req1.get(resource_api.getresource()+"U9335");
		}
		else if (method.equalsIgnoreCase("GetByUSerID"))
		{
		response = req1.get(resource_api.getresource()+ PostRequestSteps.userId_env);
		}
		else if (method.equalsIgnoreCase("getByBatchID"))
		{
		response = req1.get(resource_api.getresource()+ PostRequestSteps.bacthId_env);
		}
		
		else if (method.equalsIgnoreCase("GetAssignID"))
		{
		response = req1.get(resource_api.getresource()+ PostRequestSteps.AssignId_env);
		}
		else if (method.equalsIgnoreCase("getRoles"))
		{
		response = req1.get(resource_api.getresource());
		}
		else if (method.equalsIgnoreCase("allStaff"))
		{
		response = req1.get(resource_api.getresource());
		}
		else if (method.equalsIgnoreCase("getProgID"))
		{
		response = req1.get(resource_api.getresource()+PostRequestSteps.progID_env);
		}
	}

	@Then("User receives  status code200 ok with message")
	public void user_receives_status_code200_ok_with_message()  
	{
		response.then().assertThat().header("Content-Type","application/json")
		.statusCode(200).log().all();
	}
	
	@Then("Store data from user Get")
	public void store_data_from_user_get() throws IOException 
	{
		
		userRoleid_env= getJsonPath(response, "userRoleId");
		uid=getJsonPath(response, "userId");
		roleid=getJsonPath(response, "roleId");
		rolestatus=getJsonPath(response, "userRoleStatus");
		
		xlwrite.setcelldata("Response_userget",0,0,"testcasesName");
		xlwrite.setcelldata("Response_userget",0,1,"userRoleId");
		xlwrite.setcelldata("Response_userget",0,2,"userId");
		xlwrite.setcelldata("Response_userget",0,3,"roleId");
		xlwrite.setcelldata("Response_userget",0,4,"userRoleStatus");
		
		
		xlwrite.setcelldata("Response_userget",1,0,"u_get");
		xlwrite.setcelldata("Response_userget",1,1,userRoleid_env);
		xlwrite.setcelldata("Response_userget",1,2,uid);
		xlwrite.setcelldata("Response_userget",1,3,roleid);
		xlwrite.setcelldata("Response_userget",1,4,rolestatus);
	    
	}
	

	
	
}
