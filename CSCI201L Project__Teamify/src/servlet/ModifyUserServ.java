package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProfileServ
 */
@WebServlet("/ModifyUserServ")
public class ModifyUserServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyUserServ() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = (String)request.getSession().getAttribute("name");
		String[] oldTags = (String[])request.getSession().getAttribute("Tags");
		String[] newTags = (String[])request.getAttribute("newTags");
		List<String> cre = new ArrayList<String>();
		List<String> del = new ArrayList<String>();
		Connector con = (Connector)request.getSession().getAttribute("Connector");
		int i = 0, j = 0;
		while(i < oldTags.length && j < newTags.length) {
			int temp = oldTags[i].compareTo(newTags[j]);
			if(temp > 0) {
				cre.add(newTags[j]);
				j++;
			}
			else if(temp < 0) {
				del.add(oldTags[i]);
				i++;
			}
			else {
				i++;
				j++;
			}
		}
		while(i < oldTags.length) {
			del.add(oldTags[i]);
			i++;
		}
		while(j < oldTags.length) {
			cre.add(newTags[j]);
			j++;
		}
		String resp = con.editUserTag(name, cre, del);
		request.getSession().setAttribute("Tags", newTags);
		getServletContext().getRequestDispatcher("Profile.jsp").forward(request, response);
	}

}
