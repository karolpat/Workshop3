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
 * Servlet implementation class UsersPage
 */
@WebServlet("/usersPage")
public class UsersPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int getId = Integer.parseInt(request.getParameter("id"));
		
		try (Connection conn = DbUtil.getConnection()){
			
			Users user = Users.loadById(conn, getId);
			request.setAttribute("user", user);
			
			List<Solution> solutList = new ArrayList<>(Arrays.asList(Solution.loadAllByUserId(conn, getId)));
			request.setAttribute("list", solutList);
			
		}catch (SQLException e) {
			e.getMessage();
		}
		
		getServletContext().getRequestDispatcher("/userView.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
	}

}
