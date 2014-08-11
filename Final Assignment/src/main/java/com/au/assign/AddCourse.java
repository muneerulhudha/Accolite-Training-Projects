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
 * Servlet implementation class AddCourse
 */
@WebServlet("/AddCourse")
public class AddCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(AddCourse.class
			.getName());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddCourse() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session sess = sf.openSession();
		Institution insti = null;
		try {
			sess.beginTransaction();
			insti = (Institution) sess.get(Institution.class,
					request.getParameter("title"));
			sess.getTransaction().commit();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Failed to get Institution Tilte", e);
		} finally {
			sess.close();
		}
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("CourseInstructor.jsp");
		if (null == insti) {
			request.setAttribute("result",
					"Cannot add course! Institution does not exist. Please add the institution.");
		} else {

			addCourse(request.getParameter("name"),
					request.getParameter("description"),
					request.getParameter("duration"),
					request.getParameter("admission"),
					request.getParameter("criteria"), insti);

		}
		if (dispatcher != null) {
			dispatcher.forward(request, response);
		}
		return;

	}

	public void addCourse(String courseName, String courseDesc,
			String duration, String admissionProcess, String eligibility,
			Institution institute) {

		logger.entering(getClass().getName(), "addCourse");

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session sess = sf.openSession();
		sess.beginTransaction();

		Course course = new Course(courseName, courseDesc,
				Integer.parseInt(duration), admissionProcess,
				Float.parseFloat(eligibility), institute);

		logger.info("CourseName: " + course.getCourseName());

		sess.save(course);
		sess.getTransaction().commit();
		sess.close();

		logger.exiting(getClass().getName(), "addCourse");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
