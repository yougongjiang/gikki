package com.hpe.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.hpe.pojo.Menus;
import com.hpe.service.impl.MenusServiceImpl;
import com.hpe.service.impl.TypesServiceImpl;
import com.hpe.service1.IMenusService;
import com.hpe.service1.ITypesService;
import com.hpe.util.Page;
import com.jspsmart.upload.SmartFile;
import com.jspsmart.upload.SmartUpload;
import com.sun.xml.internal.ws.wsdl.writer.document.Types;


/**
 * Servlet implementation class MenusServlet
 */
@WebServlet("/menusServlet")
public class MenusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//private IMenusService menusService=new 
	private IMenusService menusService=new MenusServiceImpl();
    private ITypesService typesService=new TypesServiceImpl();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MenusServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String action=request.getParameter("action");
		if(action.equals("all")){
			MenusPage(request, response);
		}else if(action.equals("add")){
			addMenus(request,response);
		}else if(action.equals("findTypeAll")){
			findTypeAll(request,response);
		}
	}
	protected void MenusPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String curPage=request.getParameter("curPage");
		int curPageStr=0;
		//如果当前页码为空，设置为第一页
		if(curPage==null||"".equals(curPage)){
			curPageStr=1;
		}else {
			curPageStr=Integer.parseInt(curPage);
		}
		//创建page
		Page page=new Page();
		page.setCurPage(curPageStr);
		//调用查询方法
		page=menusService.getMenu(page);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/admin/enus.jsp").forward(request, response);
	}
	protected void findTypeAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List list=typesService.getTypesAll();
		request.setAttribute("types", list);
		request.getRequestDispatcher("/admin/menus_add.jsp").forward(request, response);
	}
	
	protected void addMenus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		
		//1.创建对象
		SmartUpload smartUpload=new SmartUpload();
		//2.执行长传初始化
		smartUpload.initialize(this.getServletConfig(),request, response);
		try {
			//3.执行文件上传
			smartUpload.upload();
			//4.获取参数
			String name = smartUpload.getRequest().getParameter("name");
			String burden = smartUpload.getRequest().getParameter("burden");
			String price = smartUpload.getRequest().getParameter("price");
			String price1 = smartUpload.getRequest().getParameter("price1");
			String brief = smartUpload.getRequest().getParameter("brief");
			String typeId = smartUpload.getRequest().getParameter("typeid");
			//获取长传文件
			SmartFile file = smartUpload.getFiles().getFile(0);
			//获取文件名
			String imgpath = "img/" + file.getFieldName();
			Menus menus = new Menus(name, typeId, burden, brief, price, price1, imgpath);
			int result = menusService.addMenu(menus);
			if (result == 1) {
				//5.执行保存
				smartUpload.save("/img");
				out.write("<script>" + "alert('恭喜你,添加成功');" + "window.location.href='" + request.getContextPath()
						+ "/menusServlet?action=all';" + "</script>");
			} else {
				if (result == -1) {
					out.write("<script>" + "alert('类别名重复');" + "window.location.href='" + request.getContextPath()
							+ "/menusServlet?action=findTypeAll';" + "</script>");
				} else {
					out.write("<script>" + "alert('添加失败');" + "window.location.href='" + request.getContextPath()
							+ "/menusServlet?action=findTypeAll';" + "</script>");
				}
			} 
		} catch (Exception e) {
			e.printStackTrace();
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
