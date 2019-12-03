package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.PreparedStatement;

/**
 * Servlet implementation class LoginServ
 */
@WebServlet("/LoginServ")
public class LoginServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public static final String Credential_String = "jdbc:mysql://google/HW3?cloudSqlInstance=white-inscriber-255423:us-central1:sql-db-1&socketFactory=com.google.cloud.sql.mysql.SocketFactory&useSSL=false&user=scarlett&password=password";
    static Connection Connection = null; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServ() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html"); 
		String next = "/Index.jsp";
		Connector con = (Connector)request.getSession().getAttribute("Connector");
		if(con == null) {
			con = new Connector(Credential_String);
			request.getSession().setAttribute("Connector", con);
		}
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		PrintWriter out = response.getWriter();
		if(name == null || password == null || name.trim().length() == 0 || password.trim().length() == 0) {
			out.print("Please provide all information.");
			getServletContext().getRequestDispatcher("/Login.jsp").include(request, response);
			out.close();
		}
		else {
			String resp = con.verifyUser(name, password);
			if(resp.contentEquals("Success")) {
				request.getSession().setAttribute("name", name);
				request.setAttribute("logout", "false");
				request.getSession().setAttribute("logout", "false");
				//getServletContext().getRequestDispatcher(next).forward(arg0, arg1);;
				System.out.println("serv:"+con.type);
				request.getSession().setAttribute("type", con.type);
				getServletContext().getRequestDispatcher(next).forward(request, response);
				
			}
			else next = "/Login.jsp";
			//out.print(resp);			
			//getServletContext().getRequestDispatcher(next).include(request, response);
			getServletContext().getRequestDispatcher(next);
			out.close();
		}
	}
		
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}


}
