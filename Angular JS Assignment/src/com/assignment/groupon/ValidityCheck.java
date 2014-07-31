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

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class ValidityCheck
 */
@WebServlet("/Validity")
public class ValidityCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ValidityCheck() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected Statement connect() {
		Connection conn = null;
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

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
		JSONObject json;
		String type = null;
		Statement st = null;
		Connection conn = null;
		ResultSet rs = null;
		JSONObject obj = new JSONObject();
		PrintWriter out = response.getWriter();
		try {
			json = new JSONObject(body);
			type = (String) json.get("Email");
			System.out.println("Type :" + type);
			st = connect();
			rs = st.executeQuery("Select Username from RegisterUser where Email='"
					+ type + "'");
			if (rs.next()) {
				try {
					obj.put("result", "false");
				} catch (JSONException e) {
					e.printStackTrace();
				}
				System.out.println(obj.toString());
				out.write(obj.toString());

			} else {
				try {
					obj.put("result", "true");
				} catch (JSONException e) {
					e.printStackTrace();
				}
				System.out.println(obj.toString());
				out.write(obj.toString());

			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// bool status=false;

		// try {
		// JSONObject requestData = new JSONObject(body);
		// System.out.println("Initalised");
		// System.out.println("username: "+requestData.get("field"));
		// JSONObject obj=new JSONObject();
		// obj.put("username","pass");
		// PrintWriter out=response.getWriter();
		// out.print(obj);
		// ///System.out.println("check : "+requestData.get("username"));
		// //fromRequest=Integer.parseInt(requestData.getString("hiddenValue"));
		// //System.out.println(fromRequest);
		// //System.out.println("Form name:  " +
		// requestData.get("userType")+" value "+requestData.get("hiddenValue"));
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

	}

}
