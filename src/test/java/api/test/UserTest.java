package api.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.Routes;
import api.endpoints.UserEndpoints;
import api.payload.Users;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserTest 
{
	Faker faker;
	Users userPayload;
	public Logger logger;
	
	@BeforeClass 
	public void setUpData()
	{
		faker= new Faker();
		userPayload = new Users();
		
		userPayload.setId(faker.idNumber().hashCode());//hashcode to generate random id
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		//userPayload.setUserStatus(faker.supe().safeEmailAddress());
		
		logger=LogManager.getLogger(this.getClass());
		
	}
	@Test(priority = 1)
	public void testPostUsers() 
	{

        logger.info("*****************CREATING USER************************");
		Response response=UserEndpoints.CreateUser(userPayload);
		response.then().log().all()	;
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("*****************CREATED USER************************");
	}
	
	@Test(priority =2 )
	public void testGetUsersByName() 
	{

		logger.info("*****************GET USER************************");
		Response response=UserEndpoints.GetUser(userPayload.getUsername());
		response.then().log().all()	;
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	
	@Test(priority = 3)
	public void testUpdateExistingUsers() 
	{
		logger.info("*****************UPDATING USER************************");
        userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());

		Response response=UserEndpoints.UpdateUser(userPayload.getUsername(), userPayload);
		response.then().log().all()	;
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("*****************UPDATED USER************************");
		
		//retrieing updated user
		logger.info("*****************GET UPDATED USER************************");
		Response responseAfterUpdate=UserEndpoints.GetUser(this.userPayload.getUsername());
		responseAfterUpdate.then().log().all()	;
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
		
		
		
	}


}
