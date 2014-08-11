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
 * Servlet implementation class ExportXML
 */
@WebServlet("/ExportXML")
public class ExportXML extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static final Logger logger = Logger.getLogger(ExportXML.class.getName());

	private static String classname = logger.getClass().getName();
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public ExportXML() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		generateXML();
		
		StringBuilder sb = new StringBuilder();
		File xmlFile = new File("E:/testfile.xml");
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
			e.printStackTrace();
		}
		finally{
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
 
        List<Institution> instiList = new ArrayList<Institution>();
        try{
        	sess.beginTransaction();
        	List insti = sess.createQuery("from Institution").list();
        	for(Iterator iterator = insti.iterator() ; iterator.hasNext(); )
        	{
        		Institution institute = (Institution)iterator.next();
        		logger.info("Title :" +institute.getTitle());
        		logger.info("Description :" +institute.getDescription());
        		instiList.add(institute);
        	}
        	
        	JAXBContext context;
            BufferedWriter writer = null;
			writer = new BufferedWriter(new FileWriter(new File("E:/testfile.xml")));
            context = JAXBContext.newInstance(Institutions.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal(new Institutions(instiList), writer);
            writer.close();   	
        
        }
        catch(Exception e)
        {
        	logger.log(Level.SEVERE, "Failed to Generate XML", e);
        }
        finally{
        	sess.close();
        }

        logger.exiting(classname, "generateXML");
	}
	
	/*public void writeXML(List<Institution> instiList)
	{
		logger.entering(classname, "writeXML");
		try {

	        DocumentBuilderFactory dbFact = DocumentBuilderFactory.newInstance();
	        DocumentBuilder build = dbFact.newDocumentBuilder();
	        Document doc = build.newDocument();

	        Element root = doc.createElement("InstituteInformation");
	        doc.appendChild(root);

	       for (Institution institute : instiList) {
	    	   Element Details = doc.createElement("Institute");
		        root.appendChild(Details);
		       
	        	Element name = doc.createElement("InstituteTitle");
	        	name.appendChild(doc.createTextNode(String.valueOf(institute.getTitle())));
	        	Details.appendChild(name);
	        	
	        	Element description = doc.createElement("Description");
	            description.appendChild(doc.createTextNode(String.valueOf(institute.getDescription())));
	            Details.appendChild(description);
	            
	            Element location = doc.createElement("Location");
	            location.appendChild(doc.createTextNode(String.valueOf(institute.getLocation())));
	            Details.appendChild(location);
	            
	            Element branches = doc.createElement("Branches");
	            branches.appendChild(doc.createTextNode(String.valueOf(institute.getBranches())));
	            Details.appendChild(branches);	            
	        }
	        TransformerFactory tFactory = TransformerFactory.newInstance();
	        Transformer aTransformer = tFactory.newTransformer();

	        // format the XML nicely
	        aTransformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");

	        aTransformer.setOutputProperty(
	                "{http://xml.apache.org/xslt}indent-amount", "4");
	        aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        DOMSource source = new DOMSource(doc);
	        try {
	        	
	            FileWriter fos = new FileWriter("E:/Outputtest.xml");
	            StreamResult result = new StreamResult(fos);
	            aTransformer.transform(source, result);

	        } catch (IOException e) {

	            logger.log(Level.SEVERE, "Failed to write xml", e);
	        }

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		logger.exiting(classname, "writeXML");
	}*/

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
