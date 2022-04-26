package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Payment {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/pafproject?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertPay(String PAYCName, String PAYAccNO, String PAYDate, String PAYAmount) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into payservice(`PAYID`,`PAYCName`,`PAYAccNO`,`PAYDate`,`PAYAmount`)" + " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, PAYCName);
			preparedStmt.setString(3, PAYAccNO);
			preparedStmt.setString(4, PAYDate);
			preparedStmt.setString(5, PAYAmount);

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the Payment.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readPay() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Payment ID</th><th>Customer Name</th><th>Account No</th><th>Date</th><th>Total Amount</th></tr>";
			String query = "select * from payservice";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String PAYID = Integer.toString(rs.getInt("PAYID"));
				String PAYCName = rs.getString("PAYCName");
				String PAYAccNO = rs.getString("PAYAccNO");
				String PAYDate = rs.getString("PAYDate");
				String PAYAmount = rs.getString("PAYAmount");

				output += "<tr><td>" + PAYID + "</td>";
				output += "<td>" + PAYCName + "</td>";
				output += "<td>" + PAYAccNO + "</td>";
				output += "<td>" + PAYDate + "</td>";
				output += "<td>" + PAYAmount + "</td>";
			}
			con.close();

			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Payment.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updatePay(String PAYID, String PAYCName, String PAYAccNO, String PAYDate, String PAYAmount) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE payservice SET PAYCName=?,PAYAccNO=?,PAYDate=?,PAYAmount=? WHERE PAYID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, PAYCName);
			preparedStmt.setString(2, PAYAccNO);
			preparedStmt.setString(3, PAYDate);
			preparedStmt.setString(4, PAYAmount);
			preparedStmt.setInt(5, Integer.parseInt(PAYID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the Payment.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deletePay(String PAYID) {

		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from payservice where PAYID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(PAYID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the Payment.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
