package com.au.assign;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.google.gson.Gson;

/**
 * Servlet implementation class ExportJSONInstitute
 */
@WebServlet("/ExportJSON")
public class ExportJSON extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final Logger logger = Logger.getLogger(ExportJSON.class.getName());
	
	private static String classname= logger.getClass().getName();
	
    /**     * @see HttpServlet#HttpServlet()
     */
    public ExportJSON() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Gson gson = new Gson();

		logger.info("Inside Export json");
		
		int value = Integer.parseInt(request.getParameter("value"));
		if(value == 1)
		{
			logger.info("Choice "+value);
			List<Institution> listInsti = listInstitutions();
			String jsonInstitutions = gson.toJson(listInsti);
			logger.info("Array: "+ jsonInstitutions);
			request.setAttribute("jsonArray",jsonInstitutions);
		    RequestDispatcher dispatcher = request.getRequestDispatcher("jsonResponse.jsp");
		    if (dispatcher != null){
		    	dispatcher.forward(request, response);
			}
		    return;
		  }
		else
		{
			logger.info("Choice "+value);
			List<Course> listCourses = listCourse();
			String jsonCourses = gson.toJson(listCourses);
			logger.info("Array: "+ jsonCourses);
			request.setAttribute("jsonArray",jsonCourses);
		    RequestDispatcher dispatcher = request.getRequestDispatcher("jsonResponse.jsp");
		    if (dispatcher != null){
		    	dispatcher.forward(request, response);
			}
		    return;
		}
		
	}
	
	@SuppressWarnings("rawtypes")
	private static List listCourse()
	{
		logger.entering(classname, "listCourse");
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
        Session sess = sf.openSession();
        
        List<Course> cList = new ArrayList<Course>();
        try{
        	sess.beginTransaction();
        	List courses = sess.createQuery("from Course").list();
        	for(Iterator iterator = courses.iterator() ; iterator.hasNext(); )
        	{
        		Course course = (Course)iterator.next();
        		logger.info("Title :" +course.getTitle());
        		logger.info("Description :" +course.getCourseDescription());
        		cList.add(course);
        	}
        		
        
        }
        catch(Exception e)
        {
        	logger.log(Level.SEVERE, "Failed to list Course", e);
        }
        finally{
        	sess.close();
        }
        
        logger.exiting(classname, "listCourse");
        return cList;
 
	}

	@SuppressWarnings("rawtypes")
	private static List listInstitutions()
	{
		
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
        Session sess = sf.openSession();
        
        List<Institution> instiList = new ArrayList<Institution>();
        try{
        	sess.beginTransaction();
        	List insti = sess.createQuery("from Institution").list();
        	for(Iterator iterator = insti.iterator() ; iterator.hasNext(); )
        	{
        		Institution institute = (Institution)iterator.next();
        		logger.info("Title :" +institute.getTitle());
        		logger.info("Description :" +institute.getDescription());
        		instiList.add(institute);
        	}
        		
        }
        catch(Exception e)
        {
        	logger.log(Level.SEVERE, "Failed to list institutions", e);
        }
        finally{
        	sess.close();
        }
        
        logger.exiting(classname, "listInstitutions");
        return instiList;
 
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
