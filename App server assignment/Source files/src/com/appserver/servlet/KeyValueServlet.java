package com.appserver.servlet;

import java.io.IOException;
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

@WebServlet("/KeyValueServlet")
public class KeyValueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String url="jdbc:sqlserver://localhost:1433;databaseName=KeyValueStore";
	private Connection conn = null;
	private Statement stmt = null;
	

	private void connectDB(){
		try
		{		
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(this.url,"muneer","1234567");
			
			stmt = conn.createStatement();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void closeDBConnection(){
		
        try {
           if (stmt != null) stmt.close();
           if (conn != null) conn.close();
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
		
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			response.setContentType("text/HTML");
			request.getSession(true);
			
			PrintWriter out = response.getWriter();
			int queryno = Integer.parseInt(request.getParameter("query"));
			
			connectDB();
			
			out.println("<html><head><link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\"></head><body><h2>Running on Tomcat instance3</h2><br />");
			
			switch(queryno)
			{
				case 1:
					try
					{
						if(null != request.getParameter("key") && null != request.getParameter("value"))
						{
							ResultSet rs = null;
							rs=stmt.executeQuery("select item_key from dbo.keymap where item_key='"+request.getParameter("key")+"'");
						    if(rs.next())
						    {
						    	stmt.executeUpdate("update dbo.keymap set item_value='"+request.getParameter("value")+"' where item_key='"+request.getParameter("key")+"'");
						    	out.println("<p>A keyValue pair already exists for the given key. Hence, Updated.</p><br /> <a href=\"index.html\">Go home</a></body></html>");
						    }
						    else
						    {
						    	stmt.executeUpdate("insert into dbo.keymap values( '"+ request.getParameter("key") +"','"+ request.getParameter("value") +"')");
						    	out.println("<p>Successfully Inserted</p><br /> <a href=\"index.html\">Go home</a></body></html>");
						    }
						}
						else
						{
							out.println("<p> Null value cannot be inserted </p> <br /><a href=\"Query1.html\">Go Back</a></body></html>");
						}
					}
					catch(Exception e){
						e.printStackTrace();
					}
					out.close();
					break;
					
				case 2:
					try{	
						response.setHeader("Cache-Control", "max-age=7200");

						ResultSet rs = stmt.executeQuery("Select * from dbo.keymap");
						
						out.println("<p>");
						
						while(rs.next())
						{
							out.println(rs.getString(1));
							out.println("<br />");
						}
						
						out.println("</p>");
						out.println("<a href=\"index.html\">Go home</a>");
						out.println("</body></html>");
					}
					catch(Exception e){
						e.printStackTrace();
					}
					out.close();
					break;
					
				case 3:
					try {
						response.setHeader("Cache-Control", "max-age=7200");

						ResultSet rs = stmt.executeQuery("Select * from dbo.keymap order by "+ request.getParameter("sortby"));
						
						out.println("<p>");
						
						while(rs.next())
						{
							out.println(rs.getString(1));
							out.println(rs.getString(2));
							
							out.println("<br />");
						}
						
						out.println("</p>");
						out.println("<a href=\"index.html\">Go home</a>");
						out.println("</body></html>");
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					out.close();
					break;
					
				case 4:
					try {
						stmt.executeUpdate("delete from dbo.keymap where item_key ='"+ request.getParameter("key") +"'");
						out.println("<p>Successfully Deleted</p><br /> <a href=\"index.html\">Go home</a></body></html>");	
					} catch (Exception e) {
						e.printStackTrace();
					}
					out.close();
					break;
					
				case 5:
					try {
						ResultSet rs= stmt.executeQuery("select COUNT(item_key) from dbo.keymap");

						out.println("<p>");
						if(rs.next())
						{
							out.println(rs.getString(1));
						}
						out.println("</p><br /><a href=\"index.html\">Go home</a></body></html>");
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					out.close();
					break;
					
				case 6:
					try {
						response.setHeader("Cache-Control", "max-age=3600");

						ResultSet rs = stmt.executeQuery("Select item_value from dbo.keymap where item_key='"+ request.getParameter("key")+"'");
						
						out.println("<p>");
						if(rs.next())
							out.println(rs.getString(1));
						else
							out.println("Invalid Key!!");
						
						out.println("</p>");
						out.println("<a href=\"index.html\">Go home</a>");
						out.println("</body></html>");
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					out.close();
					break;
					

				default:
					out.println("<html><body>Error:Default tag executed</body></html>");
					out.close();
					break;
			}
			closeDBConnection();
	}
}