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
@WebServlet("/register")
public class registerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private siteController siteController = new siteController();
    @SuppressWarnings("null")
	public String error;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/register.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String passwordTest = request.getParameter("passwordTest");
		if(password.equals(passwordTest)) {
		try {
			if (siteController.checkEmail(email) == true) {
				siteController.registerUser(email, password);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/index.jsp");
				dispatcher.forward(request, response);
			}
			else {
				error = "E-mail already exists.";
				request.setAttribute("registerError", error);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/registerError.jsp");
				dispatcher.forward(request, response);
			}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
			error = "Passwords do not match.";
			request.setAttribute("registerError", error);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/registerError.jsp");
			dispatcher.forward(request, response);
		}
	}

}
