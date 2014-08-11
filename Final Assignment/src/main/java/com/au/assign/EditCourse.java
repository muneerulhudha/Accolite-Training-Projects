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
 * Servlet implementation class EditCourse
 */
@WebServlet("/EditCourse")
public class EditCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(EditCourse.class
			.getName());

	private static String classname = logger.getClass().getName();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditCourse() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		List<Institution> insti = listInstitutions();

		logger.info("Total Institutes: " + insti.size());

		request.setAttribute("institutes", insti);
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("EditCourse.jsp");
		if (dispatcher != null) {
			dispatcher.forward(request, response);
		}

		return;

	}

	@SuppressWarnings("rawtypes")
	private static List listInstitutions() {
		logger.entering(classname, "listInstitutions");

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session sess = sf.openSession();
		List<Institution> instituteList = new ArrayList<Institution>();
		try {
			sess.beginTransaction();
			List insti = sess.createQuery("from Institution").list();
			for (Iterator iterator = insti.iterator(); iterator.hasNext();) {
				Institution insti1 = (Institution) iterator.next();
				logger.info("Title :" + insti1.getTitle());
				logger.info("Description :" + insti1.getDescription());
				instituteList.add(insti1);
			}

		} catch (Exception e) {
			logger.log(Level.SEVERE, "Failed to fetch institutions", e);
		} finally {
			sess.close();
		}

		logger.exiting(classname, "listInstitutions");
		return instituteList;
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
