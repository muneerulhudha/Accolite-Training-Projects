package com.au.assign;

import java.io.IOException;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Servlet implementation class ImportXML
 */
@WebServlet("/ImportXML")
public class ImportXML extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(ImportXML.class
			.getName());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ImportXML() {
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

		logger.info("Inside ImportXML");
		int choice = Integer.parseInt(request.getParameter("value"));
		logger.info("Choice :" + choice);
		StringBuffer sb = new StringBuffer(request.getParameter("description"));
		JAXBContext jaxbContext;
		if (choice == 1) {

			try {
				jaxbContext = JAXBContext.newInstance(Institutions.class);
				Unmarshaller jaxbUnmarshaller = jaxbContext
						.createUnmarshaller();
				SessionFactory sf = HibernateUtil.getSessionFactory();

				Institutions institutes = (Institutions) jaxbUnmarshaller
						.unmarshal(new StringReader(sb.toString()));
				for (Institution institute : institutes.getInstitutions()) {
					String title = institute.getTitle();
					logger.info(institute.getTitle());
					logger.info(institute.getDescription());
					logger.info(institute.getLocation());
					logger.info(institute.getUrl());
					Session sess = sf.openSession();
					sess.beginTransaction();
					Institution instituteCheck = (Institution) sess.get(
							Institution.class, institute.getTitle());
					if (instituteCheck == null) {
						logger.info("Title :" + institute.getTitle());
						logger.info("Description :"
								+ institute.getDescription());
						sess.save(institute);
						sess.getTransaction().commit();
						sess.close();

					} else {
						Institution instituteUpdate = (Institution) sess.get(
								Institution.class, title);
						logger.info("Already exists updating");
						instituteUpdate.setDescription(institute
								.getDescription());
						instituteUpdate.setBranches(institute.getBranches());
						instituteUpdate.setLocation(institute.getLocation());
						instituteUpdate.setUrl(institute.getUrl());
						sess.update(instituteUpdate);
						sess.getTransaction().commit();
						sess.close();

					}

				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.log(Level.SEVERE, "Failed to import from XML", e);
			}
		} else {
			try {
				jaxbContext = JAXBContext.newInstance(Courses.class);
				Unmarshaller jaxbUnmarshaller = jaxbContext
						.createUnmarshaller();
				SessionFactory sf = HibernateUtil.getSessionFactory();

				Courses courses = (Courses) jaxbUnmarshaller
						.unmarshal(new StringReader(sb.toString()));
				for (Course course : courses.getCourses()) {
					String title = course.getCourseName();
					logger.info(course.getCourseDescription());
					logger.info(course.getCourseName());
					Session sess = sf.openSession();
					sess.beginTransaction();
					Course courseCheck = (Course) sess.get(Course.class,
							course.getCourseName());
					if (courseCheck == null) {
						logger.info("Title :" + course.getCourseName());
						logger.info("Description :"
								+ course.getCourseDescription());
						Institution institute = (Institution) sess
								.get(Institution.class, course.getTitle()
										.getTitle());
						course.setTitle(institute);
						sess.save(course);
						sess.getTransaction().commit();
						sess.close();
					} else {
						Course courseUpdate = (Course) sess.get(Course.class,
								title);
						logger.info("Already exists updating");
						courseUpdate.setCourseDescription(course
								.getCourseDescription());
						courseUpdate.setAdmissionProcess(course
								.getAdmissionProcess());
						courseUpdate.setDuration(course.getDuration());
						courseUpdate.setAdmissionProcess(course
								.getAdmissionProcess());
						courseUpdate.setEligibilityCriteria(course
								.getEligibilityCriteria());
						sess.update(courseUpdate);
						sess.getTransaction().commit();
						sess.close();

					}
				}
			} catch (Exception e) {
				logger.log(Level.SEVERE, "Failed to import XML", e);
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
