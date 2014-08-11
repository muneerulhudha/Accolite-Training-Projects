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

/**
 * Servlet implementation class ListCoursesDetailed
 */
@WebServlet("/ListCoursesDetailed")
public class ListCoursesDetailed extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static final Logger logger = Logger.getLogger(ListCourses.class
			.getName());

	private static String classname = logger.getClass().getName();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListCoursesDetailed() {
        super();
        // TODO Auto-generated constructor stub
    }

    @SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

    	logger.info("ID = " + request.getParameter("courseName"));
		List<Course> course = displayCourse(request.getParameter("courseName"));
		logger.info("Total courses: " + course.size());
		
		request.setAttribute("courses", course);
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("CourseView.jsp");
		if (dispatcher != null) {
			dispatcher.forward(request, response);
		}
		return;

	}

	@SuppressWarnings("rawtypes")
	private static List displayCourse(String value) {
		logger.entering(classname, "displayCourse");
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session sess = sf.openSession();
		
		List<Course> cList = new ArrayList<Course>();
		try {
			sess.beginTransaction();
			List courses = sess.createQuery("from Course WHERE Name ='"+value+"'").list();
			for (Iterator iterator = courses.iterator(); iterator.hasNext();) {
				Course course = (Course) iterator.next();
				Institution insti = course.getTitle();
				logger.info(insti.getTitle());
				logger.info("Title :" + course.getTitle());
				logger.info("Description :" + course.getCourseDescription());
				cList.add(course);
			}

		} catch (Exception e) {
			logger.log(Level.SEVERE, "Failed to fetch course detail", e);
		} finally {
			sess.close();
		}
		
		logger.exiting(classname, "displayCourses");
		return cList;

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
