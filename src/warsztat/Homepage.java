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
		
		String seeAll = request.getParameter("all");
		
		try(Connection conn = DbUtil.getConnection()) {
			
		List<Solution> solutsList = new ArrayList<>(Arrays.asList(Solution.loadWithUsername(conn)));
		List<Solution> shortList = solutsList.subList(Math.max(solutsList.size() - 5, 0), solutsList.size());
//		List<Users> userList = new ArrayList<>();
		
//		for(Solution s : solutsList) {
//			int usrId = s.getUsers_id();
//			Users user = Users.loadById(conn, usrId);
//			userList.add(user);
//		}
		
//		request.setAttribute("usersList", userList);
		
		if(seeAll!=null) {
			request.setAttribute("fullList", solutsList);
			getServletContext().getRequestDispatcher("/fullList.jsp").forward(request, response);
		}else {
			request.setAttribute("list", shortList);	
			getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
		}
		
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
				
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}
 