package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import contents.Item;

/**
 * Servlet implementation class showProductsServlet
 */
@WebServlet("/products")
public class showProductsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private itemController itemController = new itemController();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public showProductsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Item> itemsList = itemController.selectAllItems();
		request.setAttribute("itemsList", itemsList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/products.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
