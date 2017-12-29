package warsztat;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UsersOfGroup
 */
@WebServlet("/usersOfGroup")
public class UsersOfGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		
		int getGroupId =Integer.parseInt(request.getParameter("id"));
		
		try (Connection conn = DbUtil.getConnection()){
			
			List<Users>	usersList = new ArrayList<>(Arrays.asList(Users.loadByGroupId(conn, getGroupId)));
			request.setAttribute("list", usersList);
			
//			List<Solution> solutsList
			
		}catch(SQLException e) {
			e.getMessage();
		}
		
		getServletContext().getRequestDispatcher("/usersOfGroup.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
