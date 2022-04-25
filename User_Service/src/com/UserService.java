package com;

import model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.google.gson.*;
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/User")
public class UserService {
	User UserObj = new User();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readUser() {
		return UserObj.readUser();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertUser(
			@FormParam("User_First_Name") String User_First_Name,
			@FormParam("User_Last_Name") String User_Last_Name,
			@FormParam("User_Address") String User_Address,
			@FormParam("User_Account_No") String User_Account_No,
			@FormParam("User_Contact_No") String User_Contact_No,
			@FormParam("User_Email") String User_Email,
			@FormParam("User_NIC") String User_NIC) {
		String output = UserObj.insertUser(User_First_Name, User_Last_Name, User_Address, User_Account_No, User_Contact_No, User_Email, User_NIC);
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)

	public String updateUser(String userDetails) {
		
		JsonObject UserObject = new JsonParser().parse(userDetails).getAsJsonObject();
		
		String UserID = UserObject.get("UserID").getAsString();
		String User_First_Name = UserObject.get("User_First_Name").getAsString();
		String User_Last_Name = UserObject.get("User_Last_Name").getAsString();
		String User_Address = UserObject.get("User_Address").getAsString();
		String User_Account_No = UserObject.get("User_Account_No").getAsString();
		String User_Contact_No = UserObject.get("User_Contact_No").getAsString();
		String User_Email = UserObject.get("User_Email").getAsString();
		String User_NIC = UserObject.get("User_NIC").getAsString();
		
		String output = UserObj.updateUser(UserID, User_First_Name, User_Last_Name, User_Address, User_Account_No, User_Contact_No, User_Email, User_NIC);
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteUser(String userDetails) {
		
		Document doc = Jsoup.parse(userDetails, "", Parser.xmlParser());

		String UserID = doc.select("UserID").text();
		String output = UserObj.deleteUser(UserID);
		return output;
		
	}
}
