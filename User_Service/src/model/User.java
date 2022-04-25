package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/new","root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertUser(String User_First_Name, String User_Last_Name, String User_Address, String User_Account_No, String User_Contact_No, String User_Email, String User_NIC) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into user(`UserID`,`User_First_Name`,`User_Last_Name`,`User_Address`,`User_Account_No`,`User_Contact_No`,`User_Email`,`User_NIC`)" + " values (?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, User_First_Name);
			preparedStmt.setString(3, User_Last_Name);
			preparedStmt.setString(4, User_Address);
			preparedStmt.setString(5, User_Account_No);
			preparedStmt.setString(6, User_Contact_No);
			preparedStmt.setString(7, User_Email);
			preparedStmt.setString(8, User_NIC);

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the User.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readUser() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>User ID</th><th>User First Name</th><th>User Last Name</th><th>User Address</th><th>User Account No</th><th>User Contact No</th><th>User Email</th><th>User NIC</th></tr>";
			String query = "select * from user";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String UserID = Integer.toString(rs.getInt("UserID"));
				String User_First_Name = rs.getString("User_First_Name");
				String User_Last_Name = rs.getString("User_Last_Name");
				String User_Address = rs.getString("User_Address");
				String User_Account_No = rs.getString("User_Account_No");
				String User_Contact_No = rs.getString("User_Contact_No");
				String User_Email = rs.getString("User_Email");
				String User_NIC = rs.getString("User_NIC");

				output += "<tr><td>" + UserID + "</td>";
				output += "<td>" + User_First_Name + "</td>";
				output += "<td>" + User_Last_Name + "</td>";
				output += "<td>" + User_Address + "</td>";
				output += "<td>" + User_Account_No + "</td>";
				output += "<td>" + User_Contact_No + "</td>";
				output += "<td>" + User_Email + "</td>";
				output += "<td>" + User_NIC + "</td>";
			}
			con.close();

			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the User.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateUser(String UserID, String User_First_Name, String User_Last_Name, String User_Address, String User_Account_No, String User_Contact_No, String User_Email, String User_NIC) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE user SET User_First_Name=?,User_Last_Name=?,User_Address=?,User_Account_No=?,User_Contact_No=?,User_Email=?,User_NIC=? WHERE UserID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, User_First_Name);
			preparedStmt.setString(2, User_Last_Name);
			preparedStmt.setString(3, User_Address);
			preparedStmt.setString(4, User_Account_No);
			preparedStmt.setString(5, User_Contact_No);
			preparedStmt.setString(6, User_Email);
			preparedStmt.setString(7, User_NIC);
			preparedStmt.setInt(8, Integer.parseInt(UserID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the User.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteUser(String UserID) {

		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from user where UserID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(UserID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the User.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
