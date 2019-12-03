package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SearchServ
 */
@WebServlet("/SearchServ")
public class SearchServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServ() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] tags = request.getParameterValues("json[]");
		String category = (String)request.getAttribute("category");
		Connector con = (Connector)request.getSession().getAttribute("Connector");
		if(con == null) {
			con = new Connector(null);
			request.getSession().setAttribute("Connector", con);
		}
		if(category.contentEquals("user")) {
			List<User> users = con.getUserByTag(tags);
			request.setAttribute("userResult", users);
		}
		else if(category.contentEquals("project")) {
			List<Project> projects = con.getProjectByTag(tags);
			request.setAttribute("projectResult", projects);
		}
		getServletContext().getRequestDispatcher("SearchResults.html").forward(request, response);
	}

}
