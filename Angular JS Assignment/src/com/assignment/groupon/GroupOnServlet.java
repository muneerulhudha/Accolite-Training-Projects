package com.assignment.groupon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import org.json.JSONArray;
//import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class GroupOnServlet
 */
@WebServlet("/GroupOnServlets")
public class GroupOnServlet extends HttpServlet {
	public GroupOnServlet() {

	}

	protected Statement connect(Connection conn) {

		Statement stmt = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager
					.getConnection(
							"jdbc:sqlserver://localhost:1433;databaseName=croupondb",
							"muneer", "1234567");
			stmt = conn.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return stmt;
	}

	private void close(Statement stmt, Connection conn) {
		try {
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static String getRequestContent(HttpServletRequest request)
			throws IOException {

		String body = null;
		StringBuilder sb = new StringBuilder();
		BufferedReader bufferedReader = null;

		try {
			InputStream inputStream = request.getInputStream();
			if (inputStream != null) {
				bufferedReader = new BufferedReader(new InputStreamReader(
						inputStream));
				char[] charBuffer = new char[128];
				int bytesRead = -1;
				while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
					sb.append(charBuffer, 0, bytesRead);
				}
			} else {
				sb.append("");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException ex) {
					throw ex;
				}
			}
		}
		body = sb.toString();
		System.out.println("body: " + body);
		return body;
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String st = getRequestContent(request);
		int fromRequest = -1;
		JSONObject json = null;
		try {
			json = new JSONObject(st);
			System.out.println("Initalised");
			fromRequest = Integer.parseInt(json.getString("hiddenValue"));
			System.out.println(fromRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Statement statement = null;
		ResultSet rs = null;
		Connection conn = null;
		JSONObject obj = null;
		switch (fromRequest) {
		case 0:
			statement = connect(conn);
			System.out.println("Case 0");
			try {
				rs = statement
						.executeQuery("select UType from RegisterUser where Username='"
								+ json.get("userName")
								+ "' and UPassword='"
								+ json.get("password") + "'");
				if (rs.next()) {
					String type = rs.getString(1);
					System.out.println("Type " + type);
					obj = new JSONObject();
					obj.put("userType", type);
					obj.put("result", "true");
					PrintWriter out = response.getWriter();
					out.write(obj.toString());

				} else {
					obj = new JSONObject();
					obj.put("result", "false");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			close(statement, conn);
			break;
		case 1:
			statement = connect(conn);
			try {
				statement.executeUpdate("insert into RegisterUser values( '"
						+ json.get("username") + "'" + ",'" + json.get("email")
						+ "'" + ",'" + json.get("firstname") + "'" + ",'"
						+ json.get("lastname") + "'" + ",'" + json.get("sex")
						+ "'" + ",'" + json.get("password") + "'" + ",'"
						+ json.get("userType") + "'" + ")");
				System.out.println("Inserted ");
			} catch (Exception e) {
				e.printStackTrace();
			}
			close(statement, conn);
			break;
		case 2:
			statement = connect(conn);
			try{
				statement.execute("insert into Offers Values('"
				+json.get("offerUser")+"','"
				+json.get("offerName")+"','"
				+json.get("offerDescription")+"',"
				+Integer.parseInt((String) json.get("offerCount"))+",'"
				+json.get("offerStartDate")+"','"
				+json.get("offerEndDate")+"','"
				+json.get("offerCategory")+"',"
				+json.get("offerPrice")
				+")" );
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			close(statement, conn);
			break;
		case 3:
			statement = connect(conn);
			try{
				rs = statement.executeQuery("select * from Offers where OfferOwner='"
				+json.get("user")+"'");
				
				JSONArray array=new JSONArray();
				String temp;
				while(rs.next())
				{
					obj=new JSONObject();
					temp=rs.getString(2);
					obj.put("OfferName",temp);
					temp=rs.getString(3);
					obj.put("OfferDesc",temp);
					
					temp=rs.getString(4);
					obj.put("Participants",temp);
					temp=rs.getString(5);
					obj.put("StartDate",temp);
					temp=rs.getString(6);
					obj.put("EndDate",temp);
					temp=rs.getString(7);
					obj.put("OfferCategory",temp);
					temp=rs.getString(8);
					obj.put("Price",temp);
					array.put(obj);
					//rs.next();
				}
				System.out.println(" Array: "+array.toString());
				PrintWriter out = response.getWriter();
				out.write(array.toString());
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			close(statement, conn);
			break;
		case 4:
			statement = connect(conn);
			
			
			try{
				statement.executeUpdate("delete from Offers where OfferName='"
				+json.get("offerName")+"'");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			close(statement, conn);
			break;
		case 5:
			statement = connect(conn);
			try
			{
				rs=statement.executeQuery("select * from SignupOffers where OfferName='"
				+json.get("OfferName")+"'");
				JSONArray array=new JSONArray();
				while(rs.next())
				{
					String temp= rs.getString(1);
					obj=new JSONObject();
					obj.put("UserName",temp);
					temp= rs.getString(3);
					obj.put("Participants",temp);
					temp= rs.getString(4);
					obj.put("Date",temp);
					
					array.put(obj);
				}
				System.out.println(" Array: "+array.toString());
				PrintWriter out = response.getWriter();
				out.write(array.toString());
				
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			close(statement, conn);
			break;
		case 6:
			statement=connect(conn);
			
			try{
				rs=statement.executeQuery("select Participants from Offers where OfferName='"
				+json.get("OfferName")+"'");
				if(rs.next())
				{
					obj=new JSONObject();
					String temp=rs.getString(1);
					obj.put("Remaining",temp);
					PrintWriter out = response.getWriter();
					out.write(obj.toString());
					
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			close(statement, conn);
			break;
		case 7:
			statement = connect(conn);
			try{
				rs = statement.executeQuery("select * from Offers where Participants > 0");
				JSONArray array=new JSONArray();
				String temp;
				while(rs.next())
				{
					obj=new JSONObject();
					temp=rs.getString(2);
					obj.put("OfferName",temp);
					temp=rs.getString(3);
					obj.put("OfferDesc",temp);
					
					temp=rs.getString(4);
					obj.put("Participants",temp);
					temp=rs.getString(5);
					obj.put("StartDate",temp);
					temp=rs.getString(6);
					obj.put("EndDate",temp);
					temp=rs.getString(7);
					obj.put("OfferCategory",temp);
					temp=rs.getString(8);
					obj.put("Price",temp);
					array.put(obj);
					//rs.next();
				}
				System.out.println(" Array: "+array.toString());
				PrintWriter out = response.getWriter();
				out.write(array.toString());
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			close(statement, conn);
			break;
		case 8:
			statement = connect(conn);
			try{
				rs = statement.executeQuery("select * from SignupOffers where UserName='"
				+json.get("UserName")+"'");
				JSONArray array=new JSONArray();
				String temp;
				while(rs.next())
				{
					obj=new JSONObject();
					temp=rs.getString(2);
					obj.put("OfferName",temp);
					temp=rs.getString(3);
					obj.put("Participants",temp);
					temp=rs.getString(4);
					obj.put("SignupDate",temp);
					temp=rs.getString(5);
					obj.put("Price",temp);
					
					array.put(obj);
					//rs.next();
				}
				System.out.println(" Array: "+array.toString());
				PrintWriter out = response.getWriter();
				out.write(array.toString());System.out.println(" Array: "+array.toString());
				
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			close(statement, conn);
			break;
		case 9:
			statement=connect(conn);
			try{
				rs=statement.executeQuery("select * from SignupOffers where UserName='"
			+json.get("UserName")+"' and OfferName='"
			+json.get("OfferName")+"'");
				
				
				obj=new JSONObject();
				if(rs.next())
				{
					obj.put("result","false");
				}
				else
				{
					close(statement,conn);
					statement=connect(conn);
					obj.put("result","true");
					statement.executeUpdate("insert into SignupOffers values('"
					+json.get("UserName")+"','"
					+json.get("OfferName")+"',"
					+json.get("Participants")
					+",'"+json.get("SignDate")+"',"
					+Double.parseDouble((String)json.get("Price"))+")");
					close(statement,conn);
					statement=connect(conn);
					
					statement.executeUpdate("update Offers set Participants=(Participants-"
							+json.get("Participants")+") "
									+ "where OfferName='"+json.get("OfferName")+"'");
				}
				PrintWriter out = response.getWriter();
				out.write(obj.toString());
				System.out.println(" Obj: "+obj.toString());
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			close(statement, conn);
			break;
		default:

		}

	}

}
