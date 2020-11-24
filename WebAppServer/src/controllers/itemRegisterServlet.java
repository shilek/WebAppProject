package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import contents.Item;

/**
 * Servlet implementation class itemServlet
 */
@WebServlet("/itemRegister")
public class itemRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private siteController siteController = new siteController();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public itemRegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/itemRegister.jsp");
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String quantity = request.getParameter("quantity");
		String price = request.getParameter("price");
		String image = request.getParameter("image");
		String category = request.getParameter("category");
		
		Item item = new Item(name, Integer.parseInt(quantity), Double.parseDouble(price), image, category);
		
		try {
			siteController.registerItem(item);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/index.jsp");
		dispatcher.forward(request, response);
	}
}
