package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payload.Users;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndpoints {

	public static Response GetUser(String UserName) {
		Response response = given().accept(ContentType.JSON).pathParam("username", UserName).when().get(Routes.GetUrl);

		return response;

	}

	public static Response CreateUser(Users Payload) {
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(Payload).when()
				.post(Routes.PostUrl);

		return response;

	}

	public static Response DeleteUser(String UserName) {
		Response response = given().accept(ContentType.JSON).pathParam("username", UserName).when()
				.delete(Routes.DeleteUrl);

		return response;

	}

	public static Response UpdateUser(String UserName, Users Payload) {
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.pathParam("username", UserName).body(Payload).when().put(Routes.UpdateUrl);

		return response;

	}

}
