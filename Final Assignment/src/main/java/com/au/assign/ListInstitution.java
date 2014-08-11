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
 * Servlet implementation class ListServlet
 */
@WebServlet("/ListInstitution")
public class ListInstitution extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(ListCourses.class
			.getName());

	private static String classname = logger.getClass().getName();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListInstitution() {
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
		// TODO Auto-generated method stub
		logger.info("******* READ *******");
		List<Institution> insti = displayInstitutions();
		logger.info("Total Institutes: " + insti.size());
		request.setAttribute("institutes", insti);
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("InstitutePreview.jsp");
		if (dispatcher != null) {
			dispatcher.forward(request, response);
		}
		return;

	}

	@SuppressWarnings("rawtypes")
	private static List displayInstitutions() {
		logger.entering(classname, "displayInstitutions");
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session sess = sf.openSession();

		List<Institution> instiList = new ArrayList<Institution>();
		try {
			sess.beginTransaction();
			List insti = sess.createQuery("from Institution").list();
			for (Iterator iterator = insti.iterator(); iterator.hasNext();) {
				Institution institute = (Institution) iterator.next();
				logger.info("Title :" + institute.getTitle());
				logger.info("Description :" + institute.getDescription());
				instiList.add(institute);
			}

		} catch (Exception e) {
			logger.log(Level.SEVERE, "Failed to Display Institutions", e);
		} finally {
			sess.close();
		}
		logger.exiting(classname, "displayInstitutions");
		return instiList;

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