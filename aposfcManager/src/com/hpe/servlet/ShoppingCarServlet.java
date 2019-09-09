package com.hpe.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.channels.SeekableByteChannel;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.filters.AddDefaultCharsetFilter;

import com.hpe.pojo.Menus;
import com.hpe.pojo.ShoppingCar;
import com.hpe.pojo.ShoppingCar;
import com.hpe.service1.IMenusService;
import com.hpe.service.impl.MenusServiceImpl;
import com.mysql.fabric.xmlrpc.base.Array;

/**
 * Servlet implementation class ShoppingCartServlet
 */
@WebServlet("/shoppingCartServlet")
public class ShoppingCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     private IMenusService menusService=new MenusServiceImpl();  
     
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShoppingCarServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("utf-8");
	    response.setContentType("text/html);charset=utf-8");
	    String action=request.getParameter("action");
	    if(action.equals("add")){
	    	addCar(request,response);
	    }else if(action.equals("delAll")){
	    	delAll(request,response);
	    }else if(action.equals("delCar")){
	    	delCar(request,response);
	    }else if(action.equals("updateCart")){
			updateCar(request, response);
		}
	}
	protected void updateCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		//获取购物车
	     HttpSession session=request.getSession();
	     ArrayList<ShoppingCar> carList=(ArrayList<ShoppingCar>) session.getAttribute("shoppingCart");
		//获取请求参数
	     int menuid=Integer.parseInt(request.getParameter("menuid"));
	     int menusum=Integer.parseInt(request.getParameter("menusum"));
	     //根据ID修改购物车
	     for(ShoppingCar cart:carList){
	    	 //判断ID是否相同，如果相同修改购物车数量
	    	 if(cart.getMenuid()==menuid){
	    		 //修改购物车数量
	    		 cart.setSum(menusum);
	    		 out.write("<script>"
		 					+ "alert('修改成功');"
		 					+ "window.location.href='"+request.getContextPath()+"/qiantai/shoppingcar.jsp';"
		 					+ "</script>");
	    		 break;
	    	 }
	     }
	     session.setAttribute("shoppingCart", carList);
	}
	protected void delAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
	    //清空购物车
	    session.removeAttribute("shoppingCart");
	    response.sendRedirect(request.getContextPath()+"/qiantai/index.jsp");
	}

	protected void delCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取餐品id
		int menuid=Integer.parseInt(request.getParameter("menuid"));
		//获取购物车
		HttpSession session=request.getSession();
		List<ShoppingCar> carList=(List<ShoppingCar>) session.getAttribute("shoppingCar");
		//标识购物车是否存在
		//循环
		for(ShoppingCar car:carList){
			if(car.getMenuid()==menuid){
				carList.remove(car);
				break;
			}
		}
		String curpage=request.getParameter("pageIndex");
		//把购物车添加到session域中
				session.setAttribute("shoppingCart", carList);
				response.sendRedirect(request.getContentType()+"/qiantai/index.jsp");
	}
	
	private void addCar(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		//获取餐品id
		int menuid=Integer.parseInt(request.getParameter("menuid"));
		//获取购物车
		HttpSession session=request.getSession();
		List<ShoppingCar> carList=(List<ShoppingCar>) session.getAttribute("shoppingCar");
		//标识购物车是否存在
		boolean flag=false;
		//判断购物车是否存在
		if(carList==null){
			//创建购物车
			carList=new ArrayList<ShoppingCar>();
		}else{
			//购物车存在，判断是否有该菜品，如果有数量加1
			for(ShoppingCar cart:carList){
				if(cart.getMenuid()==menuid){
					cart.setSum(cart.getSum()+1);
					flag=true;
					break;
				}
			}
		}
		//创建一个新的菜品
		if(!flag){
			//创建购物车实例
			ShoppingCar car=new ShoppingCar();
			//根据id查询
			Menus menus= menusService.getMenuById(menuid);
			car.getMenuid();
			car.setName(menus.getName());
			car.setSum(1);
			car.setPrice(Float.parseFloat(menus.getPrice()));
			//把菜品添加到List中
			carList.add(car);
		}
		//把购物车添加到session域中
		session.setAttribute("shoppingCart", carList);
		response.sendRedirect(request.getContextPath()+"/qiantai/index.jsp");
}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
