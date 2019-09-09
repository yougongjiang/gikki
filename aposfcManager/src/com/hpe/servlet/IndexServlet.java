package com.hpe.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hpe.service.impl.MenusServiceImpl;
import com.hpe.service1.IMenusService;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/indexServlet")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private IMenusService menusService=new MenusServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
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
		String curPage=request.getParameter("curPageIndex");
		int curPageStr=0;
		if(curPage==null||"".equals(curPage)){
			curPageStr=1;
		}else {
			curPageStr=Integer.parseInt(curPage);
		}
				// 创建page
		Page page = new Page();
		page.setCurPage(curPageStr);
		page.setPageNumber(6);
		// 调用查询方法
		page = menusService.getMenus(page);
		// 放到request中
		request.setAttribute("page", page);
		List<HotSellItem> hotList=hotSellItemService.getHotSell();
		request.setAttribute("hotList", hotList);
		request.getRequestDispatcher("/qiantai/index.jsp").forward(request, response);

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
