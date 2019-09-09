package com.hpe.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import com.hpe.pojo.ShoppingCar;
import com.hpe.pojo.Users;
import com.hpe.service.impl.OrderServiceImpl;
import com.hpe.service1.IOrderService;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/userServlet")
public class UserOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      private IOrderService orderService =new OrderServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserOrderServlet() {
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
		if(action.equals("addOrder")){
			addOrder(request,response);
			
		}
	}
	protected void addOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		//获取用户信息
		Users user=(Users)session.getAttribute("user");
		//获取购物车
		List<ShoppingCar> list=(List<ShoppingCar>) session.getAttribute("shoppingCar");
		if(user!=null){
			boolean res;
			try {
				res = orderService.addOrder(user.getId(), list);
				if(res){
					//清空购物车
					session.removeAttribute("shoppingCar");
					//session.setAttribute("shoppingCar");
					out.write("<script>"
							+"alert('订单提交成功');"
							+"window.location.href='"+request.getContextPath()+"/qiantai/index.jsp';"//转到我的订单 现在还没写
							+"</script>");
				}else{
					out.write("<script>"
							+"alert('订单提交失败');"
							+"window.location.href='"+request.getContextPath()+"/qiantai/index.jsp';"
							+"</script>");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			out.write("<script>"
					+"alert('请登录后在提交订单');"
					+"window.location.href='"+request.getContextPath()+"/qiantai/index.jsp';"
					+"</script>");
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
