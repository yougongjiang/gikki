package com.hpe.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hpe.pojo.OrdersInfo;
import com.hpe.service.impl.OrderServiceImpl;
import com.hpe.service1.IOrderService;
import com.hpe.util.Page;

/**
 * Servlet implementation class OrdersServlet
 */
@WebServlet("/ordersServlet")
public class OrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private IOrderService orderservice=new OrderServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrdersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//请求转码
		request.setCharacterEncoding("utf-8");
		//响应转码
		response.setContentType("text/html;charset=utf-8");
		//获取请求参数的action
		String action=request.getParameter("action");
		//判断action的值，决定调用什么方法
		if(action.equals("search")){
			search(request, response);
		}else if (action.equals("all")) {
			allOrders(request, response);
		} else if (action.equals("delete")) {
			deleteOrder(request, response);
		} else if (action.equals("change")) {
			changeDelivery(request, response);
		} else if (action.equals("getMoneyAll")) {
			getMoneyAll(request, response);
		}
	}
	protected void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid=request.getParameter("userid");//用户编号
		String menuname=request.getParameter("menuname");//菜品名
		String date=request.getParameter("date");//时间
		String curPage=request.getParameter("curPage");
		int curPageStr=0;
		if(curPage==null||curPage.equals("")){
			curPageStr=1;
		}else{
			curPageStr=Integer.parseInt(curPage);
			
		}
		if(userid==null||userid.equals("")){
			userid="0";
		}
		//传入页码
		Page page=new Page();
		page.setCurPage(curPageStr);
		//传入搜索条件
		OrdersInfo ordersInfo=new OrdersInfo();
		ordersInfo.setUserid(Integer.parseInt(userid));
		ordersInfo.setMenuname(menuname);
		ordersInfo.setDate(date);
		ordersInfo.setDelivery(-1);
		//调用方法
		page=orderservice.getOrderSearch(page,ordersInfo);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/admin/order_search.jsp").forward(request, response);
	}	protected void getMoneyAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		GetMoneyAll get = new GetMoneyAll();
		get.setTimes(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		// 1.获取所有
		List<GetMoneyAll> list = orderService.getMoneyAll(get.getTimes());
		double money = 0;
		for(GetMoneyAll m : list){
			money += m.getTotal();
		}
		
		// 2.放到request域中
		request.setAttribute("money", money);
		request.setAttribute("list", list);
		// 3.转发给jsp界面
		request.getRequestDispatcher("/admin/order_statistic.jsp").forward(request, response);
	}

	protected void changeDelivery(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		// 获取订单编号
		int id = Integer.parseInt(request.getParameter("id"));
		String curPage = request.getParameter("curPage");// 当前页码
		// 调用service修改方法
		int result = orderService.changeDelivery(id);
		if (result == 1) {
			out.write("<script>" + "alert('派送成功！');" + "window.location.href='" + request.getContextPath()
					+ "/ordersServlet?action=all&&curPage=" + curPage + "';" + "</script>");

		} else {
			out.write("<script>" + "alert('派送失败！');" + "window.location.href='" + request.getContextPath()
					+ "/ordersServlet?action=all';" + "</script>");

		}
	}

	protected void deleteOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		// 获取订单编号
		int id = Integer.parseInt(request.getParameter("id"));
		String curPage = request.getParameter("curPage");// 当前页码
		// 调用service修改方法
		int result = orderService.deleteOrder(id);
		if (result == 1) {
			out.write("<script>" + "alert('删除成功！');" + "window.location.href='" + request.getContextPath()
					+ "/ordersServlet?action=all&&curPage=" + curPage + "';" + "</script>");

		} else {
			out.write("<script>" + "alert('删除失败！');" + "window.location.href='" + request.getContextPath()
					+ "/ordersServlet?action=all';" + "</script>");

		}

	}

	protected void allOrders(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String curPage = request.getParameter("curPage");// 当前页码
		int curPageStr = 0;
		if (curPage == null || curPage.equals("")) {
			curPageStr = 1;
		} else {
			curPageStr = Integer.parseInt(curPage);
		}
		// 创建page
		Page page = new Page();
		page.setCurPage(curPageStr);
		page.setPageNumber(6);
		// 调用service查询方法
		page = orderService.AllOrders(page);
		// 放到request域中
		request.setAttribute("page", page);
		request.getRequestDispatcher("/admin/order.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
