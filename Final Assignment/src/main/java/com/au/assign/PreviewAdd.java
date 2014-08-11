package com.au.assign;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PreviewAdd
 */
@WebServlet("/PreviewAdd")
public class PreviewAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	private static final Logger logger = Logger.getLogger(PreviewAdd.class.getName());
	
	public static String classname = logger.getClass().getName();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PreviewAdd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		logger.info("Inside Preview add");
		
		request.setAttribute("title", request.getParameter("title"));
		request.setAttribute("description", request.getParameter("description"));
		request.setAttribute("location", request.getParameter("location"));
		request.setAttribute("branches", request.getParameter("branches"));
		request.setAttribute("imageurl", request.getParameter("imageurl"));
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("PreviewAdd.jsp");
		if (dispatcher != null) {
			dispatcher.forward(request, response);
		}
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
