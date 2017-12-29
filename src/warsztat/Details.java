package warsztat;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/details")
public class Details extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			
		int id = Integer.parseInt(request.getParameter("id"));
		
		try (Connection conn = DbUtil.getConnection()){
			
			Solution solut = Solution.loadById(conn, id);
			request.setAttribute("details", solut);
			
		}catch (SQLException e) {
			e.getMessage();
		}
		
		getServletContext().getRequestDispatcher("/details.jsp").forward(request, response);
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
	}

}
