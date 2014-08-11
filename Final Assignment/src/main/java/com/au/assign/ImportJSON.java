package com.au.assign;

import java.io.IOException;
import java.lang.reflect.Type;
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
import com.google.gson.reflect.TypeToken;

/**
 * Servlet implementation class ImportJSON
 */
@WebServlet("/ImportJSON")
public class ImportJSON extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(ImportJSON.class.getName());
		
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ImportJSON() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		logger.info(request.getParameter("value"));
		int choice = Integer.parseInt(request.getParameter("value"));
		logger.info("Inside ImportJSON");
		logger.info("Choice " + choice);
		
		
		if(choice == 1)
		{
		Gson gson = new Gson();
		String jsonArray = request.getParameter("description");
		
		
		Type listType = new TypeToken<List<Institution>>() {}.getType();
		List<Institution> instituteList = gson.fromJson(jsonArray, listType);

		List<Institution> institutes = new Gson().fromJson(
				request.getParameter("description"),
				new TypeToken<List<Institution>>() {
				}.getType());
		SessionFactory sf = HibernateUtil.getSessionFactory();

		try {

			for (Iterator iterator = institutes.iterator(); iterator.hasNext();) {
				Session sess = sf.openSession();
				sess.beginTransaction();

				Institution institute = (Institution) iterator.next();
				String title = institute.getTitle();

				Institution instituteCheck = (Institution) sess.get(
						Institution.class, title);
				if (instituteCheck == null) {
					logger.info("Title :" + institute.getTitle());
					logger.info("Description :" + institute.getDescription());
					sess.save(institute);
					sess.getTransaction().commit();
					sess.close();

				} else {
					Institution instituteUpdate = (Institution) sess.get(
							Institution.class, title);
					logger.info("Already exists updating");
					instituteUpdate.setDescription(institute.getDescription());
					instituteUpdate.setBranches(institute.getBranches());
					instituteUpdate.setLocation(institute.getLocation());
					instituteUpdate.setUrl(institute.getUrl());
					sess.update(instituteUpdate);
					sess.getTransaction().commit();
					sess.close();

				}

			}

		} catch (Exception e) {
			logger.log(Level.SEVERE, "Failed to import JSON", e);
		}
		}
		else
		{
			Gson gson = new Gson();
			String jsonArray = request.getParameter("description");
			
			
			Type listType = new TypeToken<List<Course>>() {
			}.getType();
			List<Course> cList = gson.fromJson(jsonArray, listType);

			List<Course> courses = new Gson().fromJson(
					request.getParameter("description"),
					new TypeToken<List<Course>>() {
					}.getType());
			SessionFactory sf = HibernateUtil.getSessionFactory();

			try {

				for (Iterator iterator = courses.iterator(); iterator.hasNext();) {
					Session sess = sf.openSession();
					sess.beginTransaction();

					Course course = (Course) iterator.next();
					String title = course.getCourseName();

					Course courseCheck = (Course) sess.get(
							Course.class, title);
					if (courseCheck == null) {
						logger.info("Title :" + course.getCourseName());
						logger.info("Description :" + course.getCourseDescription());
						sess.save(course);
						sess.getTransaction().commit();
						sess.close();
					} else {
						Course courseUpdate = (Course) sess.get(
								Course.class, title);
						logger.info("Already exists updating");
						courseUpdate.setCourseDescription(course.getCourseDescription());
						courseUpdate.setAdmissionProcess(course.getAdmissionProcess());
						courseUpdate.setDuration(course.getDuration());
						courseUpdate.setAdmissionProcess(course.getAdmissionProcess());
						courseUpdate.setEligibilityCriteria(course.getEligibilityCriteria());
						courseUpdate.setTitle(course.getTitle());
						sess.update(courseUpdate);
						sess.getTransaction().commit();
						sess.close();

					}

				}

			} catch (Exception e) {
				logger.log(Level.SEVERE, "Failed to import JSON", e);
			}
		}
		
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("CourseInstructor.jsp");
		if (dispatcher != null) {
			dispatcher.forward(request, response);
		}
		return;

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
