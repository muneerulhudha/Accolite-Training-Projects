package com.au.assign;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Servlet implementation class ExportXMLCourse
 */
@WebServlet("/ExportXMLCourse")
public class ExportXMLCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final Logger logger= Logger.getLogger(ExportXMLCourse.class.getName());
	
	private static String classname = logger.getClass().getName();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExportXMLCourse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		generateXML();
		StringBuilder sb = new StringBuilder();
		File xmlFile = new File("E:/TestCourse.xml");
		BufferedReader bufferedReader = null;
		String temp;
		try{
			bufferedReader = new BufferedReader(new FileReader(xmlFile));
			while(null != (temp = bufferedReader.readLine()))
			{
				sb.append("\r");
				sb.append(temp);
			}
			
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE, "Buffered reader failed", e);
		}finally
		{
			bufferedReader.close();
		}
		request.setAttribute("xmlFile",sb);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("xmlContents.jsp");
        if (dispatcher != null){
			dispatcher.forward(request, response);
		}
        return;
	}
	
	@SuppressWarnings("rawtypes")
	public void generateXML()
	{
		logger.entering(classname, "generateXML");
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
        Session sess = sf.openSession();
         
        List<Course> cList = new ArrayList<Course>();
        try{
        	sess.beginTransaction();
        	List courses = sess.createQuery("from Course").list();
        	for(Iterator iterator = courses.iterator() ; iterator.hasNext(); )
        	{
        		Course course = (Course)iterator.next();
        		logger.info("Title :" +course.getCourseName());
        		logger.info("descr " +course.getCourseDescription());
        		cList.add(course);
        	}
        	JAXBContext context;
            BufferedWriter writer = null;
			writer = new BufferedWriter(new FileWriter(new File("E:/TestCourse.xml")));
            context = JAXBContext.newInstance(Courses.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal(new Courses(cList), writer);
            writer.close();   	
        }
        catch(Exception e)
        {
        	logger.log(Level.SEVERE, "Failed to generate Course XML", e);
        }
        finally{
        	sess.close();
        }
        logger.exiting(classname, "generateXML");
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
