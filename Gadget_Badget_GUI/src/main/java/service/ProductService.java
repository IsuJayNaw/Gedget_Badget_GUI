package service;

import model.ProductServlet;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document; 

@Path("/Products")
public class ProductService {

	ProductServlet productObj = new ProductServlet();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readProducts()
	 {
		return productObj.readProduct(); 
	 }
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 

	public String insertProduct(
			@FormParam("productName") String productName, 
			@FormParam("productDescription") String productDescription, 
			@FormParam("productLocation") String productLocation)
		 
	{ 
		String output = productObj.insertProduct(productName, productDescription, productLocation); 
		return output; 
	}	
	
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 

	public String updateProduct(String productData) 
	{ 
		//Convert the input string to a JSON object 
		 JsonObject productObject = new JsonParser().parse(productData).getAsJsonObject(); 
		//Read the values from the JSON object
		 String productID = productObject.get("productID").getAsString(); 
		 String productName = productObject.get("productName").getAsString(); 
		 String productDescription = productObject.get("productDescription").getAsString(); 
		 String productLocation = productObject.get("productLocation").getAsString();   
		
		 
		 String output = productObj.updateProduct(productID, productName, productDescription, productLocation); 
		 return output; 
	}
	
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 

	public String deleteProduct(String productData) 
	{ 
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse(productData, "", Parser.xmlParser()); 
		 
		//Read the value from the element <itemID>
		 String productID = doc.select("productID").text(); 
		 String output = productObj.deleteProduct(productID); 
		 return output; 
	}
}