package com.hpe.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hpe.pojo.OrdersInfo;
import com.hpe.pojo.Users;
import com.hpe.service.IOrdersQTService;
import com.hpe.service.impl.OrdersQTServiceImpl;
import com.hpe.util.Page;

/**
 * Servlet implementation class OrdersQTServlet
 */
public class OrdersQTServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IOrderService ordersqtService = new OrderServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrdersQTServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("type/html;charset=utf-8");
		search(request,response);
	}

	protected void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String menuname = request.getParameter("menuname");
		String date = request.getParameter("date");
		String delivery = request.getParameter("delivery");
		int deliveryStr = -1;
		
		String curPage = request.getParameter("curPage"); //当前页码
		int curPageStr = 0;
		
		if(curPage == null || curPage.equals("")) {
			curPageStr = 1;
		} else {
			curPageStr = Integer.parseInt(curPage);
		}
		
		if(delivery != null){
			if(delivery.equals("1")){
				deliveryStr = 1;
			} else if(delivery.equals("0")){
				deliveryStr = 0;
			}
		}
		
		//传入页码
		Page page = new Page();
		page.setCurPage(curPageStr);
		
		//获取session来得到userid
		int userid = -1;
		HttpSession session = request.getSession();
		Users users = (Users) session.getAttribute("users");
		if(users != null){
			userid = users.getId();
		}
		
		//传入搜素条件
		OrdersInfo ordersInfo = new OrdersInfo();
		ordersInfo.setUsersid(userid);
		ordersInfo.setMenuname(menuname);
		ordersInfo.setDate(date);
		ordersInfo.setDelivery(deliveryStr);
		
		//调用方法
		page = ordersqtService.getOrderSearch(page, ordersInfo);
		request.setAttribute("page", page);
		request.setAttribute("ordersInfo", ordersInfo);
		request.getRequestDispatcher("/qiantai/order.jsp").forward(request,response);
		
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
