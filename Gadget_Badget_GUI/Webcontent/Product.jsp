<%@page import="model.ProductServlet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	
	
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
			<title>Product Management - GadgetBadget</title>
	
		<link href="stylsheet.css" rel="stylesheet" />
		<link rel="stylesheet" href="Views/bootstrap.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
		<script src="Components/jquery-3.5.0.min.js"></script>
		<script src="Components/Product.js"></script>

	</head>
	
	<body>
		<div class="container">
	
			<p class="font-weight-bold">
				<center>
					<h1><b>Product Management - GadgetBadget</b></h1>
				</center>
			</p>
			<br><br>
			
			<fieldset>
	
				<legend><b>Add Product Details</b></legend>
					<form id="PRODUCT" name="PRODUCT" class="border border-light p-5">
						
						<div class="form-outline mb-4">
						    <label class="form-label" for="form6Example3" class="col-sm-2 col-form-label col-form-label-sm">Product Name:</label>
						    <input type="hidden" id="productID" name="productID" value="">
						    <input type="text" id="productName" class="form-control" name="productName" placeholder="Enter product Name..">						    
						</div>
						
						<div class="form-outline mb-4">
						    <label class="form-label" for="form6Example3" class="col-sm-2 col-form-label col-form-label-sm">Product Description:</label>
						    <input type="text" id="productDescription" class="form-control" name="productDescription" placeholder="Enter product Description..">						    
						</div>
						
						<div class="form-outline mb-4">
						    <label class="form-label" for="form6Example3" class="col-sm-2 col-form-label col-form-label-sm">Product Location:</label>
						    <input type="text" id="productLocation" class="form-control" name="productLocation" placeholder="Enter product Location..">						    
						</div>
					
						 
											
						<br> 
						<div id="alertSuccess" class="alert alert-success"></div>
						<div id="alertError" class="alert alert-danger"></div>
						<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary btn-lg btn-block"> 
						
					</form>
				
								
			</fieldset>
			
			<br> 
			
			<div class="container" id="ProductGrid">
				<fieldset>
					<legend><b>View Product Details</b></legend>
					<form method="post" action="Product.jsp" class="table table-striped">
						<%
						ProductServlet viewProduct = new ProductServlet();
											out.print(viewProduct.readProduct());
						%>
					</form>
					<br>
				</fieldset>
			</div>
		</div>
	</body>
</html>