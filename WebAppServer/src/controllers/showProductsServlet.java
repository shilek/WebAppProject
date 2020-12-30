package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import contents.Category;
import contents.Item;

/**
 * Servlet implementation class showProductsServlet
 */
@WebServlet("/products")
public class showProductsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private siteController siteController = new siteController();
       
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
		List<Item> itemsList = new ArrayList<>();
		List<Category> categories = siteController.selectAllCategories();
		String id = request.getParameter("id");
		String observe = request.getParameter("observe");
		String category = request.getParameter("category");
		request.setAttribute("categories", categories);
		if(category != null) {
			itemsList = siteController.selectItemsFromCategory(Integer.parseInt(category));
			request.setAttribute("itemsList", itemsList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/products.jsp");
			dispatcher.forward(request, response);
		} else {
		itemsList = siteController.selectAllItems();
		request.setAttribute("itemsList", itemsList);
		if(id != null) {
			int item_id = Integer.parseInt(id);
			request.setAttribute("selectedItem", siteController.selectItem(item_id));
			HttpSession session = request.getSession();
			if(session.getAttribute("loggedUser") != null){
				int account_id = siteController.getUserId((String)session.getAttribute("loggedUser"));
				if(siteController.isItemObserved(account_id, item_id)) {
					session.setAttribute("observed", true);
				} else {
					session.setAttribute("observed", false);
				}
				if(observe != null) {
					if (observe.equals("no")) {
						siteController.stopObservingItem(account_id, item_id);
						response.sendRedirect("products?id="+item_id);
					} else {
						siteController.observeItem(account_id, item_id);
						response.sendRedirect("products?id="+item_id);
					}
				} else {
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/productInfo.jsp");
					dispatcher.forward(request, response);
				}	
			} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/productInfo.jsp");
			dispatcher.forward(request, response);
			}
		} else {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/products.jsp");
		dispatcher.forward(request, response);
		}
	}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
