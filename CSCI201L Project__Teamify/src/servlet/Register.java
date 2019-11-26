package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutServ
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String next = "/HomePage.jsp";
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String type = request.getParameter("userType");
		String description = request.getParameter("description");
		Connector con = (Connector)request.getSession().getAttribute("connector");
		String resp = con.createUser(username, password, email, type, description);
		PrintWriter pw = response.getWriter();
		if(!resp.contentEquals("Success")) {
			pw.print(resp);
			next = "/Register.jsp";
		}
		getServletContext().getRequestDispatcher(next).forward(request, response);
	}
}
