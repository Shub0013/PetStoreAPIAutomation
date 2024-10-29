package api.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.Routes;
import api.endpoints.UserEndpoints;
import api.payload.Users;
import api.utilities.DataProviders;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserDataDrivenTest 
{
@Test(priority = 1,dataProvider ="Data", dataProviderClass = DataProviders.class)
	public void testDDPostUsers(String userId,String userName, String fName,String lName,String userEmail, String password, String phone  )
	{
	Users userPayload = new Users();
	userPayload.setId(Integer.parseInt(userId));
	userPayload.setUsername(userName);
	userPayload.setFirstName(fName);
	userPayload.setLastName(lName);
	userPayload.setEmail(userEmail);
	userPayload.setPassword(password);
	userPayload.setPhone(phone);
	
	Response response=UserEndpoints.CreateUser(userPayload);
	response.then().log().all()	;
	Assert.assertEquals(response.getStatusCode(), 200);
	
	}
@Test(priority =2,dataProvider ="UserNames", dataProviderClass = DataProviders.class)
public void testDDGetUsersByName(String userName) 
{


	Response response=UserEndpoints.GetUser(userName);
	response.then().log().all()	;
	Assert.assertEquals(response.getStatusCode(), 200);
	
}
@Test(priority = 3,dataProvider ="UserNames", dataProviderClass = DataProviders.class)
public void testDDUpdateExistingUsers(String userName) 
{
    Users userPayload = new Users();
	userPayload.setFirstName("Kumar");
	userPayload.setLastName("Shubham");
	userPayload.setEmail("abc@gmail.com");

	Response response=UserEndpoints.UpdateUser(userName, userPayload);
	response.then().log().all()	;
	Assert.assertEquals(response.getStatusCode(), 200);
	
	//retrieing updated user
	Response responseAfterUpdate=UserEndpoints.GetUser(userName);
	responseAfterUpdate.then().log().all()	;
	Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
	
	
}
}
