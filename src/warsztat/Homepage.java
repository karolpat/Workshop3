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
import javax.servlet.http.HttpSession;



@WebServlet("/homepage")
public class Homepage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try(Connection conn = DbUtil.getConnection()) {
			
		List<Solution> solutsList = new ArrayList<>(Arrays.asList(Solution.loadAll(conn)));
		List<Solution> shortList = solutsList.subList(Math.max(solutsList.size() - 5, 0), solutsList.size());
		
		request.setAttribute("list", shortList);
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
		getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}
