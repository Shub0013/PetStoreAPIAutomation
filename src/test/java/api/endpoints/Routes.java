package api.endpoints;

//Pet store Url= https://petstore.swagger.io/
//Base URL: https://petstore.swagger.io/v2
//get user url= https://petstore.swagger.io/v2/user/{username}
//Create user url= https://petstore.swagger.io/v2/user
//Update user url= https://petstore.swagger.io/v2/user/{username}
//delete user url=https://petstore.swagger.io/v2/user/{username}



public class Routes 
{

	public static String BaseUrl="https://petstore.swagger.io/v2";
	//Url for User Module
	public static String GetUrl= BaseUrl+"/user/{username}";
	public static String PostUrl= BaseUrl+"/user";
	public static String UpdateUrl= BaseUrl+"/user/{username}";
	public static String DeleteUrl= BaseUrl+"/user/{username}";
	
	//Simillarly we will be creating for other modules like store and pet
}
