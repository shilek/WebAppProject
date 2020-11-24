package controllers; 

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class index
 */
@WebServlet("/account")
public class accountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private siteController siteController = new siteController();
    private String error;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public accountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setAttribute("loggedInEmail", siteController.loggedInEmail);
		if(siteController.loggedInEmail != null) request.setAttribute("loggedUser", siteController.getUser(siteController.loggedInEmail));
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/account.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		if(siteController.loggedInEmail == null) {
		try {
			if (siteController.checkLogin(email, password) == true) {
				siteController.loggedInEmail = email;
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/index.jsp");
				dispatcher.forward(request, response);
			}
			else {
				error = "Wrong email or password.";
				request.setAttribute("loginError", error);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/accountError.jsp");
				dispatcher.forward(request, response);
			}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
