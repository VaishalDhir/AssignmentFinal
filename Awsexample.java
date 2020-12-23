package com.AssignmentFinal.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.json.JSONArray;
import org.json.JSONObject;

@Path("/aws")
public class Awsexample {

	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	PreparedStatement preparedStatement = null;

	JSONObject mainObj = new JSONObject();
	JSONArray jsonArray = new JSONArray();
	JSONObject childObj = new JSONObject();

	@GET
	@Path("/getInd")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getIndividual() {
		ConnectToSql connection = new ConnectToSql();

		con = connection.getConnection();

		try {
			stmt = con.createStatement();

			rs = stmt.executeQuery("Select * from individual");

			while (rs.next()) {
				childObj = new JSONObject();

				childObj.accumulate("Birth Date", rs.getString("BIRTH_DATE"));
				childObj.accumulate("First Name", rs.getString("FIRST_NAME"));
				childObj.accumulate("Last Name", rs.getString("LAST_NAME"));
				childObj.accumulate("Customer Id", rs.getString("CUST_ID"));

				jsonArray.put(childObj);
			}

			mainObj.put("Individual", jsonArray);
		} catch (SQLException e) {
			System.out.println("SQL Exception : " + e.getMessage());
		} finally {
			try {
				con.close();
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				System.out.println("Finally Block SQL Exception : " + e.getMessage());
			}
		}

		return Response.status(200).entity(mainObj.toString()).build();

	}


	@POST
	@Path("/createInd")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createIndividual(Individual individual) {
		
		ConnectToSql connection = new ConnectToSql();
		con = connection.getConnection();
		
		
		try {
			String query = "INSERT INTO `midterm`.`individual` (`BIRTH_DATE` ,`FIRST_NAME`,`LAST_NAME`) VALUES(?,?,?,);";
		
			preparedStatement = con.prepareStatement(query);

			preparedStatement.setString(1, individual.getBIRTH_DATE());
			preparedStatement.setString(2, individual.getFIRST_NAME());
			preparedStatement.setString(3,individual.getLAST_NAME());
			//preparedStatement.setInt(4,individual.getCUST_ID());
		
		System.out.println("asasasasasasasasas :"+preparedStatement);
			int rowCount = preparedStatement.executeUpdate();
		
			if(rowCount>0) {
				System.out.println("Record inserted Successfully! : "+rowCount);
				
				mainObj.accumulate("Status", 201);
				mainObj.accumulate("Message", "Record Successfully added!");
			} else {
				mainObj.accumulate("Status", 500);
				mainObj.accumulate("Message", "Something went wrong!");
			}
		} catch (SQLException e) {

			mainObj.accumulate("Status", 500);
			mainObj.accumulate("Message", e.getMessage());
		} finally {
			try {
				preparedStatement.close();
				con.close();
			}catch (SQLException e) {
				System.out.println("Finally SQL Exception : "+e.getMessage());
			}
	}
	
	
	return Response.status(201).entity(mainObj.toString()).build();
	
		
		
	}
	
	
	@DELETE
	@Path("/deleteInd/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteIndividual(@PathParam("id") int id,Individual individual) {
		
		ConnectToSql connection = new ConnectToSql();
		con = connection.getConnection();
		
		
		try {
			String query= "DELETE FROM  `midterm`.`individual` WHERE CUST_ID="+id+";";
					/*SET column1 = value1, column2 = value2, ...
					WHERE condition;*/
			/*String query = "INSERT INTO `midterm`.`individual` (`BIRTH_DATE` ,`FIRST_NAME`,`LAST_NAME`) VALUES(?,?,?);";*/
		
			preparedStatement = con.prepareStatement(query);
			preparedStatement.executeUpdate();
		
			/*if(rowCount>0) {
				System.out.println("Record inserted Successfully! : "+rowCount);
				
				mainObj.accumulate("Status", 201);
				mainObj.accumulate("Message", "Record Successfully added!");
			} else {
				mainObj.accumulate("Status", 500);
				mainObj.accumulate("Message", "Something went wrong!");
			}*/
		} catch (SQLException e) {

			mainObj.accumulate("Status", 500);
			mainObj.accumulate("Message", e.getMessage());
		} finally {
			try {
				preparedStatement.close();
				con.close();
			}catch (SQLException e) {
				System.out.println("Finally SQL Exception : "+e.getMessage());
			}
	}
	
	
	return Response.status(201).entity(mainObj.toString()).build();
	
		
		
	}
	
	//Update Query
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/updateInd/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateIndividual(@PathParam("id") int id, Individual individual)
	{
		ConnectToSql connection = new ConnectToSql();
		con = connection.getConnection();
		String query = "UPDATE individual SET `BIRTH_DATE` = ?, `FIRST_NAME` =?, `LAST_NAME` =? WHERE `CUST_ID` = ?;";
		Status status = Status.OK;
		try
		{
		
			preparedStatement = con.prepareStatement(query);
			
			preparedStatement.setString(1, individual.getBIRTH_DATE());
			preparedStatement.setString(2, individual.getFIRST_NAME());
			preparedStatement.setString(3,individual.getLAST_NAME());
			preparedStatement.setInt(4,id);
			System.out.println("dhehhehehehhehehhehe :"+preparedStatement);
			int rowcount=preparedStatement.executeUpdate();
			
			if(rowcount>0)
			{
				mainObj.accumulate("Status",status);
				mainObj.accumulate("Message", "data successfully updated");
			}
			else
			{
				status=Status.NOT_MODIFIED;
				mainObj.accumulate("Status", status);
				mainObj.accumulate("Message", "data NOT UPDATED");
			}
			
		
		}catch (SQLException e) {
			status=Status.NOT_MODIFIED;
			e.printStackTrace();
			mainObj.accumulate("Status" ,status);
			mainObj.accumulate("Message" , "Something went wrong!");
		}
		return Response.status(status).entity(mainObj.toString()).build();
		
	}
	
	@GET
	@Path("/getpro")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProduct() {
		ConnectToSql connection = new ConnectToSql();

		con = connection.getConnection();

		try {
			stmt = con.createStatement();

			rs = stmt.executeQuery("Select * from product");

			while (rs.next()) {
				childObj = new JSONObject();

				childObj.accumulate("Product CD", rs.getString("PRODUCT_CD"));
				childObj.accumulate("Date Offered", rs.getString("DATE_OFFERED"));
				childObj.accumulate("Date Retired", rs.getString("DATE_RETIRED"));
				childObj.accumulate("Name", rs.getString("NAME"));
				childObj.accumulate("Product Type CD", rs.getString("PRODUCT_TYPE_CD"));

				jsonArray.put(childObj);
			}

			mainObj.put("Product", jsonArray);
		} catch (SQLException e) {
			System.out.println("SQL Exception : " + e.getMessage());
		} finally {
			try {
				con.close();
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				System.out.println("Finally Block SQL Exception : " + e.getMessage());
			}
		}

		return Response.status(200).entity(mainObj.toString()).build();

	}
	
	@DELETE
	@Path("/deletepro")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteProduct(@PathParam("cd") int cd,Product product) {
		
		ConnectToSql connection = new ConnectToSql();
		con = connection.getConnection();
		
		
		try {
			String query= "DELETE FROM  `midterm`.`product` WHERE PRODUCT_CD="+cd  +";";
					/*SET column1 = value1, column2 = value2, ...
					WHERE condition;*/
			/*String query = "INSERT INTO `midterm`.`individual` (`BIRTH_DATE` ,`FIRST_NAME`,`LAST_NAME`) VALUES(?,?,?);";*/
		
			preparedStatement = con.prepareStatement(query);
			preparedStatement.executeUpdate();
		
			/*if(rowCount>0) {
				System.out.println("Record inserted Successfully! : "+rowCount);
				
				mainObj.accumulate("Status", 201);
				mainObj.accumulate("Message", "Record Successfully added!");
			} else {
				mainObj.accumulate("Status", 500);
				mainObj.accumulate("Message", "Something went wrong!");
			}*/
		} catch (SQLException e) {

			mainObj.accumulate("Status", 500);
			mainObj.accumulate("Message", e.getMessage());
		} finally {
			try {
				preparedStatement.close();
				con.close();
			}catch (SQLException e) {
				System.out.println("Finally SQL Exception : "+e.getMessage());
			}
	}
	
	
	return Response.status(201).entity(mainObj.toString()).build();
	
		
		
	}

	//Officer calls
	@GET
	@Path("/getofficer")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOfficer() {
		ConnectToSql connection = new ConnectToSql();

		con = connection.getConnection();

		try {
			stmt = con.createStatement();

			rs = stmt.executeQuery("Select * from officer");

			while (rs.next()) {
				childObj = new JSONObject();

				childObj.accumulate("OFFICER ID", rs.getString("OFFICER_ID"));
				childObj.accumulate("END DATE", rs.getString("END_DATE"));
				childObj.accumulate("First Name", rs.getString("FIRST_NAME"));
				childObj.accumulate("Last Name", rs.getString("LAST_NAME"));
				childObj.accumulate("Start Date", rs.getString("START_DATE"));
				childObj.accumulate("TITLE", rs.getString("TITLE"));
				childObj.accumulate("Customer Id", rs.getString("CUST_ID"));

				jsonArray.put(childObj);
			}

			mainObj.put("Officer", jsonArray);
		} catch (SQLException e) {
			System.out.println("SQL Exception : " + e.getMessage());
		} finally {
			try {
				con.close();
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				System.out.println("Finally Block SQL Exception : " + e.getMessage());
			}
		}

		return Response.status(200).entity(mainObj.toString()).build();

	}
	
	//Delete Officer
	@DELETE
	@Path("/deleteofficer/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteOfficer(@PathParam("id") int id,Officer officer) {
		
		ConnectToSql connection = new ConnectToSql();
		con = connection.getConnection();
		
		
		try {
			String query= "DELETE FROM  `midterm`.`officer` WHERE CUST_ID="+id+";";
					/*SET column1 = value1, column2 = value2, ...
					WHERE condition;*/
			/*String query = "INSERT INTO `midterm`.`individual` (`BIRTH_DATE` ,`FIRST_NAME`,`LAST_NAME`) VALUES(?,?,?);";*/
		
			preparedStatement = con.prepareStatement(query);
			preparedStatement.executeUpdate();
		
			/*if(rowCount>0) {
				System.out.println("Record inserted Successfully! : "+rowCount);
				
				mainObj.accumulate("Status", 201);
				mainObj.accumulate("Message", "Record Successfully added!");
			} else {
				mainObj.accumulate("Status", 500);
				mainObj.accumulate("Message", "Something went wrong!");
			}*/
		} catch (SQLException e) {

			mainObj.accumulate("Status", 500);
			mainObj.accumulate("Message", e.getMessage());
		} finally {
			try {
				preparedStatement.close();
				con.close();
			}catch (SQLException e) {
				System.out.println("Finally SQL Exception : "+e.getMessage());
			}
	}
	
	
	return Response.status(201).entity(mainObj.toString()).build();
	
		
		
	}
	
	//Update Officer
	//Update Query
		@PUT
		@Consumes(MediaType.APPLICATION_JSON)
		@Path("/updateofficer/{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response updateOfficer(@PathParam("id") int id, Officer officer)
		{
			ConnectToSql connection = new ConnectToSql();
			con = connection.getConnection();
			String query = "UPDATE officer SET `OFFICER_ID` = ?,`END_DATE` = ?, `FIRST_NAME` = ?, `LAST_NAME` =?, `START_DATE` = ?, `TITLE` =? WHERE `CUST_ID` = ?;";
			Status status = Status.OK;
			try
			{
			
				preparedStatement = con.prepareStatement(query);
				
				preparedStatement.setString(1, officer.getOFFICER_ID());
				preparedStatement.setString(2, officer.getEND_DATE());
				
				preparedStatement.setString(3, officer.getFIRST_NAME());
				preparedStatement.setString(4, officer.getLAST_NAME());
				preparedStatement.setString(5,officer.getSTART_DATE());
				preparedStatement.setString(6,officer.getTITLE());
				//preparedStatement.setInt(7	,id);
				System.out.println("dhehhehehehhehehhehe :"+preparedStatement);
				int rowcount=preparedStatement.executeUpdate();
				
				if(rowcount>0)
				{
					mainObj.accumulate("Status",status);
					mainObj.accumulate("Message", "data successfully updated");
				}
				else
				{
					status=Status.NOT_MODIFIED;
					mainObj.accumulate("Status", status);
					mainObj.accumulate("Message", "data NOT UPDATED");
				}
				
			
			}catch (SQLException e) {
				status=Status.NOT_MODIFIED;
				e.printStackTrace();
				mainObj.accumulate("Status" ,status);
				mainObj.accumulate("Message" , "Something went wrong!");
			}
			return Response.status(status).entity(mainObj.toString()).build();
			
		}
		// get customers
		@GET
		@Path("/getcustomer")
		@Produces(MediaType.APPLICATION_JSON)
		public Response getCustomer() {
			ConnectToSql connection = new ConnectToSql();

			con = connection.getConnection();

			try {
				stmt = con.createStatement();

				rs = stmt.executeQuery("Select * from customer");

				while (rs.next()) {
					childObj = new JSONObject();
					childObj.accumulate("Customer Id", rs.getString("CUST_ID"));
					childObj.accumulate("ADDRESS", rs.getString("ADDRESS"));
					childObj.accumulate("City", rs.getString("CITY"));
					childObj.accumulate("Cust type cd", rs.getString("CUST_TYPE_CD"));
					childObj.accumulate("Fed ID", rs.getString("FED_ID"));
					childObj.accumulate("POSTAL_CODE", rs.getString("POSTAL_CODE"));
					childObj.accumulate("STATE", rs.getString("STATE"));
					

					jsonArray.put(childObj);
				}

				mainObj.put("customer", jsonArray);
			} catch (SQLException e) {
				System.out.println("SQL Exception : " + e.getMessage());
			} finally {
				try {
					con.close();
					stmt.close();
					rs.close();
				} catch (SQLException e) {
					System.out.println("Finally Block SQL Exception : " + e.getMessage());
				}
			}

			return Response.status(200).entity(mainObj.toString()).build();

		}
	//Delete Customer
		
		@DELETE
		@Path("/deletecustomer/{id}")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response deleteCustomer(@PathParam("id") int id,Customer customer) {
			
			ConnectToSql connection = new ConnectToSql();
			con = connection.getConnection();
			
			
			try {
				String query= "DELETE FROM  `midterm`.`customer` WHERE CUST_ID="+id+";";
						/*SET column1 = value1, column2 = value2, ...
						WHERE condition;*/
				/*String query = "INSERT INTO `midterm`.`individual` (`BIRTH_DATE` ,`FIRST_NAME`,`LAST_NAME`) VALUES(?,?,?);";*/
			
				preparedStatement = con.prepareStatement(query);
				preparedStatement.executeUpdate();
			
				/*if(rowCount>0) {
					System.out.println("Record inserted Successfully! : "+rowCount);
					
					mainObj.accumulate("Status", 201);
					mainObj.accumulate("Message", "Record Successfully added!");
				} else {
					mainObj.accumulate("Status", 500);
					mainObj.accumulate("Message", "Something went wrong!");
				}*/
			} catch (SQLException e) {

				mainObj.accumulate("Status", 500);
				mainObj.accumulate("Message", e.getMessage());
			} finally {
				try {
					preparedStatement.close();
					con.close();
				}catch (SQLException e) {
					System.out.println("Finally SQL Exception : "+e.getMessage());
				}
		}
		
		
		return Response.status(201).entity(mainObj.toString()).build();
		
			
			
		}
	
}
