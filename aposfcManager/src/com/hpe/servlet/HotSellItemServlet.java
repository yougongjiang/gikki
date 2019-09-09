package com.hpe.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hpe.pojo.HotSellItem;
import com.hpe.service.IHotSellItemService;
import com.hpe.service.impl.HotSellItemServiceImpl;

/**
 * Servlet implementation class HotSellItemServlet
 */
@WebServlet("/hotSellItemServlet")
public class HotSellItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IHotSellItemService hotSellItemService=new HotSellItemServiceImpl();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HotSellItemServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String action=request.getParameter("action");
		if(action.equals("getHot")){
			getHot(request, response);
		}
	}
	
	
	protected void getHot(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<HotSellItem> hotList=hotSellItemService.getHotSell();
		request.setAttribute("hotList", hotList);
		request.getRequestDispatcher("/qiantai/index.jsp").forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
