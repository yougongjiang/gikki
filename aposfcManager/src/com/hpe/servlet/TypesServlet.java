package com.hpe.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hpe.pojo.Types;
import com.hpe.service.impl.TypesServiceImpl;
import com.hpe.service1.ITypesService;

/**
 * Servlet implementation class TypesServlet
 */
@WebServlet("/typesServlet")
public class TypesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ITypesService typesService=new TypesServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TypesServlet() {
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
		if(action.equals("findAll")){
			//查询所有
			finAll(request,response);
			
		}else if(action.equals("add")){
			addTypes(request,response);
			
		}else if(action.equals("update")){
			update(request,response);
		}else if(action.equals("update")){
			findTypesById(request,response);
		}
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	protected void findTypesById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id=Integer.parseInt(request.getParameter("id"));
		Types type=typesService.getTypesbyId(id);
		request.setAttribute("type",type);
		request.getRequestDispatcher("/admin/type_update.jsp").forward(request, response);
	
	}
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		// TODO Auto-generated method stub
		String name=request.getParameter("name");
		int id=Integer.parseInt(request.getParameter("id"));
		Types types=new Types();
		types.setId(id);
		types.setName(name);
		int result=typesService.updateTypes(types);
		if(result==1){
			//session.removeAttribute("admin");//清除session重新登录
			out.write("<script>"
					+"alert('恭喜你修改成功');"
					+"window.parent.location.href='"+request.getContextPath()+"/typesServlet?action=findAll';"
					+"</script>");
		}else if(result==-1){
			out.write("<script>"
					+"alert('类别名重复');"
					+"window.location.href='"+request.getContextPath()+"/typesServlet?action=findTypeById&&id="+types.getId()+"';"
					+"</script>");
		}else{
			out.write("<script>"
					+"alert('修改失败');"
					+"window.location.href='"+request.getContextPath()+"/admin/type_update.jsp';"
					+"</script>");
		}
	}
	protected void addTypes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		//获取url参数
		String name=request.getParameter("name");
		Types types=new Types();
		types.setName(name);
		//调用添加方法
		int result=typesService.addTypes(types);
		if(result==1){
			out.write("<script>"
					+ "alert('添加成功');"
					+ "window.location.href='"+request.getContextPath()+"/typesServlet?action=findAll';"
					+ "</script>");
		}else{
			if(result==-1){
				out.write("<script>"
						+ "alert('类别名称已存在！请重新输入！！');"
						+ "window.location.href='"+request.getContextPath()+"/admin/type_add.jsp';"
						+ "</script>");
			}else{
				out.write("<script>"
						+ "alert('添加失败！！');"
						+ "window.location.href='"+request.getContextPath()+"/admin/type_add.jsp';"
						+ "</script>");
			}
		}
	}
	protected void finAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//1.获取所有类别
		List<Types> types=typesService.getTypesAll();
		//2.将查询结果放到request域中
		request.setAttribute("types", types);
		//3.转发给jsp界面
		request.getRequestDispatcher("/admin/type.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
