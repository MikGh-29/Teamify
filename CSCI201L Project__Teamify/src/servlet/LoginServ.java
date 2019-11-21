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
		ArrayList<String> usernames = new ArrayList<String>();
		ArrayList<String> passwords = new ArrayList<String>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection = DriverManager.getConnection(Credential_String);
			
			PreparedStatement prepared = (PreparedStatement) Connection.prepareStatement("SELECT * FROM Users");
			
			ResultSet rs = prepared.executeQuery();
			while(rs.next()) {
				String name = rs.getString("username");
				String password = rs.getString("password");
				//System.out.print(name);
				//System.out.println(password);
				usernames.add(name);
				passwords.add(password);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			response.setContentType("text/html");  
			PrintWriter out = response.getWriter();  
			//request.getRequestDispatcher("Login.jsp").include(request, response);  
          
			String name = request.getParameter("name");  
			String password=request.getParameter("password");  
        
			String nextpage = "/HomePage.jsp";
          
			Boolean nameExist = false;
			String currPassword = "";
			for(int i=0; i<usernames.size(); i++) {
				if(usernames.get(i).equals(name)) {
					nameExist = true;
					currPassword = passwords.get(i);
					break;
				}
			}
			if(name.equals("")||password.equals("")) {
				out.print("Please provide all information.");
    				request.getRequestDispatcher("Login.jsp").include(request, response);
			}
			else if(!nameExist) {
        			out.print("The user does not exist.");
        			request.getRequestDispatcher("Login.jsp").include(request, response);
			}
			else if(!password.equals(currPassword)) {
        			out.print("Incorrect password");
        			request.getRequestDispatcher("Login.jsp").include(request, response);
			} 
			else {
				HttpSession session=request.getSession();  
				session.setAttribute("name", name);  
				request.setAttribute("logout", "false");
				session.setAttribute("logout", "false");
				out.print("Sucessfully login!");
				request.getRequestDispatcher(nextpage).include(request, response);
				out.close(); 
			}
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
