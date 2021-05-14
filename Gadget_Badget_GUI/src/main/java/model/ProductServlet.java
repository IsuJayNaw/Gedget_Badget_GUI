package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ProductServlet {

	
	//Connect to the MySQL DB
	private Connection connect() 
	{ 
		Connection con = null; 
		try
		{ 
			Class.forName("com.mysql.jdbc.Driv");  
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test?useTimezone=true&serverTimezone=UTC", "root", ""); 
		} 
		catch (Exception e) 
		{
			e.printStackTrace();} 
		 	return con; 
		} 

		public String insertProduct(String productName, String productDescription, String productLocation) 
		 { 
			 String output = ""; 
			 try
			 { 
				 Connection con = connect(); 
				 if (con == null) 
				 {
					 return "Error while connecting to the database for inserting."; 
				 } 
			 	 	 // create a prepared statement
				 	 String query = "INSERT INTO productsgui(`productID`,`productName`,`productDescription`,`productLocation`)" + " VALUES (?, ?, ?, ?)"; 
					 PreparedStatement preparedStmt = con.prepareStatement(query); 					 
					 
					 // binding values
					 preparedStmt.setInt(1, 0);
					 preparedStmt.setString(2, productName);
					 preparedStmt.setString(3, productDescription);
					 preparedStmt.setString(4, productLocation);
					 				 
					 preparedStmt.execute(); 
					 con.close(); 
					 
					 String newProduct = readProduct(); 
					 output = "{\"status\":\"success\", \"data\": \"" + newProduct + "\"}"; 
			 } 
			 catch (Exception e) 
			 { 
				 output = "{\"status\":\"error\", \"data\": \"Error while inserting the Product.\"}";
				 System.err.println(e.getMessage());
			 } 
		 	return output; 
		 } 

		//Read Investments
		 public String readProduct() 
		 { 
			 String output = ""; 

			 try
			 { 
				 Connection con = connect(); 
				 if (con == null) 
				 {
					 return "Error while connecting to the database for reading."; 
				 } 
				 
				 // Prepare the html table to be displayed
				 output = "<table border='1'><tr><th>Product Name</th>"
				 + "<th>Product Description</th>" + 
				 "<th>Project Location</th>" + 
				 "<th>Update</th><th>Remove</th></tr>"; 
			 
				 
				 String query = "SELECT * FROM productsgui"; 
				 Statement stmt = con.createStatement(); 
				 ResultSet rs = stmt.executeQuery(query); 
				 
				 // iterate through the rows in the result set
				 while (rs.next()) 
				 { 
					 String productID = Integer.toString(rs.getInt("productID")); 
					 String productName = rs.getString("productName"); 
					 String productDescription = rs.getString("productDescription"); 
					 String productLocation = rs.getString("productLocation"); 
					 
					 
					 // Add into the html table
					 output += "<tr><td>" + productName + "</td>"; 
					 output += "<td>" + productDescription + "</td>"; 
					 output += "<td>" + productLocation + "</td>"; 					 
					 
					 
					 // buttons
					 output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
					 + "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-itemid='" 
					 + productID + "'>" + "</td></tr>";
				 } 
				 	 con.close(); 
				 	 // Complete the html table
				 	 output += "</table>"; 
			 } 
			 catch (Exception e) 
			 { 
				 output = "Error while reading the product"; 
				 System.err.println(e.getMessage()); 
			 } 
		 	 return output; 
		 } 
				
		//Update Investments
		public String updateProduct(String productID, String productName, String productDescription, String productLocation)
		{ 
			 String output = ""; 
			 try
			 { 
				 Connection con = connect(); 
				 if (con == null) 
				 {
					 return "Error while connecting to the database for updating."; 
				 } 
				 
				 // create a prepared statement
				 String query = "UPDATE productsgui SET productName=? , productDescription=? , productLocation=?  WHERE productID=?"; 
				 PreparedStatement preparedStmt = con.prepareStatement(query); 
				 
				 // binding values
				 preparedStmt.setString(1, productName); 
				 preparedStmt.setString(2, productDescription); 
				 preparedStmt.setString(3, productLocation); 
				 preparedStmt.setInt(4, Integer.parseInt(productID)); 
				 
				 // execute the statement
				 preparedStmt.execute(); 
				 con.close(); 
				 String newProduct = readProduct(); output = "{\"status\":\"success\", \"data\": \"" + newProduct + "\"}"; 
			 } 
			 catch (Exception e) 
			 { 
				 output = "{\"status\":\"error\", \"data\": \"Error while updating the Product.\"}"; 
				 System.err.println(e.getMessage()); 
			 } 
			 	return output; 
			 } 
		
			//Delete Investments
			 public String deleteProduct(String productID) 
			 { 
				 String output = ""; 
			 try
			 { 
				 Connection con = connect(); 
			 if (con == null) 
			 {
				 return "Error while connecting to the database for deleting."; 
			 } 
			 
			 	 // create a prepared statement
				 String query = "DELETE FROM productsgui WHERE productID=?"; 
				 PreparedStatement preparedStmt = con.prepareStatement(query); 
				 
				 // binding values
				 preparedStmt.setInt(1, Integer.parseInt(productID)); 
				 
				 // execute the statement
				 preparedStmt.execute(); 
				 con.close(); 
				 String newProduct = readProduct(); output = "{\"status\":\"success\", \"data\": \"" + newProduct + "\"}";
			 } 
			 catch (Exception e) 
			 { 
				 output = "{\"status\":\"error\", \"data\": \"Error while deleting the Product.\"}"; 
				 System.err.println(e.getMessage()); 
			 } 
			 return output; 
		} 
} 