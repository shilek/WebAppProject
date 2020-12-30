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
 * Servlet implementation class buyServlet
 */
@WebServlet("/buy")
public class buyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private siteController sitecontroller = new siteController();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public buyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		if(action.equals("step1")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/buyStep1.jsp");
			dispatcher.forward(request, response);
		}
		if(action.equals("noAccount")) {
			HttpSession session = request.getSession();
			session.setAttribute("save", "no");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/buyStep2.jsp");
			dispatcher.forward(request, response);
		}
		}
			

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		if(action.equals("step3")) {
			HttpSession session = request.getSession();
			List<String> order = new ArrayList<>();
			order.add(request.getParameter("name"));
			order.add(request.getParameter("surname"));
			order.add(request.getParameter("email"));
			order.add(request.getParameter("address"));
			order.add(request.getParameter("city"));
			session.setAttribute("order", order);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/buyStep3.jsp");
			dispatcher.forward(request, response);
		}
		if(action.equals("lastStep")) {
			HttpSession session = request.getSession();
			List<String> orderInfo = (List<String>)session.getAttribute("order");
			List<CartItem> cart = (List<CartItem>)session.getAttribute("cart");
			int id = sitecontroller.getMaxOrdersId()+1;
			for(int i=0; i < cart.size(); i++) {
			sitecontroller.insertOrder(id, orderInfo.get(0), orderInfo.get(1), orderInfo.get(2), orderInfo.get(3), orderInfo.get(4), cart.get(i).getQuantity(), cart.get(i).getItem().getId());
			}
			String msg = buildEmail(orderInfo, cart);
			Mailer.send(orderInfo.get(2), "Zamowienie", msg);
			if(session.getAttribute("save").equals("yes")) {
				sitecontroller.insertBoughtItems(sitecontroller.getUserId(session.getAttribute("loggedUser").toString()), id);
			}
			for(int i=0; i < cart.size(); i++) {
				if(sitecontroller.selectItem(cart.get(i).getItem().getId()).getQuantity() - cart.get(i).getQuantity() <= 0) {
					sitecontroller.sendEmailsItemOut(cart.get(i).getItem().getId());
				}
				sitecontroller.setItemQuantity(cart.get(i).getItem().getId(), sitecontroller.selectItem(cart.get(i).getItem().getId()).getQuantity(), cart.get(i).getQuantity());
			}
			session.removeAttribute("cart");
			session.removeAttribute("order");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/buyStep4.jsp");
			dispatcher.forward(request, response);
		}
	}

	private String buildEmail(List<String> orderInfo, List<CartItem> cart) {
		StringBuilder stringBuilder = new StringBuilder();
		float sum=0;
		stringBuilder.append("Zamowienie nastepujacych przedmiotow: \n");
		for(int i=0; i < cart.size(); i++) {
			stringBuilder.append(cart.get(i).getItem().getName()+" w ilosci "+cart.get(i).getQuantity()+", cena: "+cart.get(i).getQuantity()*cart.get(i).getItem().getPrice()+"\n");
			sum+=cart.get(i).getQuantity()*cart.get(i).getItem().getPrice();
		}
		stringBuilder.append("Cena ostateczna: "+sum+"\n\n");
		stringBuilder.append("Szczegoly wysylki:\n");
		stringBuilder.append(orderInfo.get(0)+"\n");
		stringBuilder.append(orderInfo.get(1)+"\n");
		stringBuilder.append(orderInfo.get(2)+"\n");
		stringBuilder.append(orderInfo.get(3)+"\n");
		stringBuilder.append(orderInfo.get(4)+"\n\n");
		stringBuilder.append("Dziekujemy za zamowienie.");
		
		return stringBuilder.toString();
	}
}
