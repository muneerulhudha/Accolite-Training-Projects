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
 * Servlet implementation class EditInstitution2
 */
@WebServlet("/EditInstitution2")
public class EditInstitution2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger
			.getLogger(EditInstitution2.class.getName());

	private static String classname = logger.getClass().getName();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditInstitution2() {
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

		logger.info("Title :" + request.getParameter("title"));

		editInstitution(request.getParameter("title"),
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

	public void editInstitution(String title, String description,
			String location, String branches, String url) {
		logger.entering(classname, "editInstitutions");
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session sess = sf.openSession();
		try {
			sess.beginTransaction();
			Institution insti = (Institution) sess
					.get(Institution.class, title);
			logger.info("Desc " + insti.getDescription());
			insti.setDescription(description);
			insti.setLocation(location);
			insti.setBranches(branches);
			insti.setUrl(url);
			sess.update(insti);
			sess.getTransaction().commit();

		} catch (Exception e) {
			logger.log(Level.SEVERE, "Failed to Update Institution", e);
		} finally {
			sess.close();
		}
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
