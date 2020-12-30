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
import contents.CartItem;

/**
 * Servlet implementation class shoppingCartServlet
 */
@WebServlet("/cart")
public class shoppingCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private siteController siteController = new siteController();
	private List<CartItem> cart = new ArrayList<CartItem>();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public shoppingCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		System.out.println(action);
		if(action != null) {
		if(action.equals("ordernow")) {
			if(session.getAttribute("cart") == null) {
				cart = new ArrayList<CartItem>();
				cart.add(new CartItem(siteController.selectItem(Integer.parseInt(request.getParameter("id"))), 1));
				session.setAttribute("cart", cart);
			} else {
				cart = (List<CartItem>)session.getAttribute("cart");
				int index = isExisting(Integer.parseInt(request.getParameter("id")), cart);
				if(index == -1)
				cart.add(new CartItem(siteController.selectItem(Integer.parseInt(request.getParameter("id"))), 1));
				else {
					cart.get(index).setQuantity(cart.get(index).getQuantity() + 1);
				}
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/cart.jsp");
			dispatcher.forward(request, response);
		} else if(action.equals("delete")){
			cart = (List<CartItem>)session.getAttribute("cart");
			int index = isExisting(Integer.parseInt(request.getParameter("id")), cart);
			cart.remove(index);
			session.setAttribute("cart", cart);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/cart.jsp");
			dispatcher.forward(request, response);
		}
		}
		else {
		cart = (List<CartItem>)session.getAttribute("cart");
		session.setAttribute("cart", cart);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/cart.jsp");
		dispatcher.forward(request, response);
		}
	}
	
	private int isExisting(int id, List<CartItem> cart) {
		for(int i=0; i<cart.size(); i++) {
			if(cart.get(i).getItem().getId() == id) {
				return i;
			}
		}
		return -1;
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
