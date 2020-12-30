package controllers; 

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		if(action == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/account.jsp");
			dispatcher.forward(request, response);
		} else if (action.equals("buy")){
			session.setAttribute("save", "yes");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/buyStep2.jsp");
		dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String action = request.getParameter("action");
		try {
			HttpSession session = request.getSession();
			if(request.getParameter("logout") != null) {
				session.removeAttribute("loggedUser");
				session.removeAttribute("userName");
				session.removeAttribute("userSurname");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/index.jsp");
				dispatcher.forward(request, response);
			}
			else {
			if (siteController.checkLogin(email, password) == true) {
				session.setAttribute("loggedUser", email);
				session.setAttribute("userName", siteController.getUser(email).getName());
				session.setAttribute("userSurname", siteController.getUser(email).getSurname());
				if(action != null) {
				if(action.equals("buy")) {
					session.setAttribute("save", "yes");
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/buyStep2.jsp");
					dispatcher.forward(request, response);
				}
				}
				else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/index.jsp");
				dispatcher.forward(request, response);
				}
				}
				
			else {
				error = "Wrong email or password.";
				request.setAttribute("loginError", error);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/accountError.jsp");
				dispatcher.forward(request, response);
			}
			}
			} catch (Exception e) {
				e.printStackTrace();
			}
		
	}

}
