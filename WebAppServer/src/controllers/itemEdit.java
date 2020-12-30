package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import contents.Item;

/**
 * Servlet implementation class itemEdit
 */
@WebServlet("/itemEdit")
public class itemEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private siteController siteController = new siteController();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public itemEdit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Item> itemsList = new ArrayList<>();
		itemsList = siteController.selectAllItems();
		request.setAttribute("itemsList", itemsList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/itemEdit.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		if(session.getAttribute("pickedItemToEdit") == null) {
		Pattern p = Pattern.compile("^\\d+");
		Matcher m = p.matcher((String)request.getParameter("pickedItem"));
		m.find();
		int id = Integer.parseInt(m.group());
		session.setAttribute("pickedItemToEdit", siteController.selectItem(id));
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/itemEdit.jsp");
		dispatcher.forward(request, response);
	} else {
		int id = ((Item)session.getAttribute("pickedItemToEdit")).getId();
		String name = request.getParameter("name");
		String quantity = request.getParameter("quantity");
		String price = request.getParameter("price");
		String image = request.getParameter("image");
		String category = request.getParameter("category");
		siteController.updateItem(id, name, Integer.parseInt(quantity), Double.parseDouble(price), image, Integer.parseInt(category));
		if(((Item) session.getAttribute("pickedItemToEdit")).getQuantity() == 0 && ((Item) session.getAttribute("pickedItemToEdit")).getQuantity() != Integer.parseInt(quantity)){
			siteController.sendEmailsItemReady(id);
		}
		if(((Item) session.getAttribute("pickedItemToEdit")).getQuantity() > 0 && Integer.parseInt(quantity) == 0) {
			siteController.sendEmailsItemOut(id);
		}
		session.removeAttribute("pickedItemToEdit");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/itemEdit.jsp");
		dispatcher.forward(request, response);
		
	}
	}
}

