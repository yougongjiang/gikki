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
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
