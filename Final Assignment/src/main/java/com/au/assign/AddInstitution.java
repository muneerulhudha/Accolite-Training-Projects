package com.au.assign;

import java.io.IOException;
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
 * Servlet implementation class InsertInstitute
 */
@WebServlet("/AddInstitution")
public class AddInstitution extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(AddInstitution.class
			.getName());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddInstitution() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		addInstitute(request.getParameter("title"),
				request.getParameter("description"),
				request.getParameter("location"),
				request.getParameter("branches"),
				request.getParameter("imageurl"));

		RequestDispatcher dispatcher = request
				.getRequestDispatcher("CourseInstructor.jsp");
		if (dispatcher != null) {
			dispatcher.forward(request, response);
		}
		return;

	}

	public void addInstitute(String title, String description, String location,
			String branches, String url) {

		logger.entering(getClass().getName(), "addInstitute");

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session sess = sf.openSession();
		sess.beginTransaction();

		Institution insti = new Institution(title, description, location,
				branches, url);

		logger.info("Title : " + insti.getTitle());
		logger.info("Desc : " + insti.getDescription());
		logger.info("Location : " + insti.getLocation());
		logger.info("Branches : " + insti.getBranches());
		logger.info("URL : " + insti.getUrl());

		sess.save(insti);
		sess.getTransaction().commit();
		sess.close();

		logger.exiting(getClass().getName(), "addInstitute");
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