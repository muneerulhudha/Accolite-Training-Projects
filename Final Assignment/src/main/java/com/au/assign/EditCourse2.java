package com.au.assign;

import java.io.IOException;
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

/**
 * Servlet implementation class EditCourse2
 */
@WebServlet("/EditCourse2")
public class EditCourse2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static final Logger logger = Logger.getLogger(EditCourse1.class.getName());
	
	private static String classname = logger.getClass().getName();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditCourse2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		logger.info("Inside "+ classname);
		logger.info("Course: "+ request.getParameter("courseName"));
		logger.info("Description:"+request.getParameter("description"));
		logger.info("Duratin :"+ request.getParameter("duration"));
		logger.info("Admission: "+request.getParameter("admissionProcess"));
		logger.info("eligibilit"+request.getParameter("eligibilityCriteria"));
		
		editCourse(request.getParameter("courseName"),request.getParameter("description"),request.getParameter("duration"),request.getParameter("admissionProcess"),request.getParameter("eligibilityCriteria"));
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("CourseInstructor.jsp");
        if (dispatcher != null){
			dispatcher.forward(request, response);
		}
        return;
		
	}

	public void editCourse(String courseName,String description,String duration,String admissionProcess,String eligibilityCriteria)
	{
		logger.entering(classname, "editCourse");
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
        Session sess = sf.openSession();
        try{
        	sess.beginTransaction();
        	Course course = (Course)sess.get(Course.class,courseName);
      
        	logger.info("Description :"+ course.getCourseDescription());
      
        	course.setCourseDescription(description);
        	course.setDuration(Integer.parseInt(duration));
        	course.setAdmissionProcess(admissionProcess);
        	course.setEligibilityCriteria(Float.parseFloat(eligibilityCriteria));
        	sess.update(course);
        	sess.getTransaction().commit();
        	
        	
        }
        catch(Exception e)
        {
        	logger.log(Level.SEVERE, "Edit Course Failed", e);
        }
        finally
        {
        	sess.close();
        }
        logger.exiting(classname, "editCourse");
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
