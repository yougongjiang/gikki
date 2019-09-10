package com.hpe.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hpe.pojo.Notice;
import com.hpe.pojo.Types;
import com.hpe.service.INoticeService;
import com.hpe.service.impl.NoticeServiceImpl;

/**
 * Servlet implementation class NoticeServlet
 */
@WebServlet("/noticeServlet")
public class NoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private INoticeService noticeService=new NoticeServiceImpl();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeServlet() {
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
		if(action.equals("findAll")){
			//查询所有
			findAll(request, response);
		}else if(action.equals("add")){
			addNotice(request, response);
		}else if(action.equals("delete")){
			deleteNotice(request, response);
		}else if(action.equals("findByName")){
			findByName(request, response);
		}
	}

	protected void findByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  //1 获取所有类别
		  String name=request.getParameter("name");
		  Notice notice=noticeService.getNoticeByName(name);
		  //2 将查询结果放到request域中
		  request.setAttribute("notice", notice);
		  //3 转发给jsp页面
		  request.getRequestDispatcher("qiantai/notice.jsp").forward(request, response);

		 }

	
	protected void deleteNotice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		int result = -1;
		//获取订单id
		String noticeidStr = request.getParameter("noticeid");
		int noticeid = -1;
		if(noticeidStr != null){
			noticeid = Integer.parseInt(noticeidStr);
		}
		
		result = noticeService.delete(noticeid);
		if(result >= 1){
			out.write("<script>"
					+"alert('删除成功！');"
					+"window.location.href='"+request.getContextPath()+"/noticeServlet?action=findAll';"
					+"</script>");
		}else{
			out.write("<script>"
					+"alert('删除失败！');"
					+"window.location.href='"+request.getContextPath()+"/noticeServlet?action=findAll';"
					+"</script>");
		}
	}
	
	
	protected void addNotice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		
		//获取name
		String name=request.getParameter("name");
		String content=request.getParameter("content");
		Notice notice=new Notice();
		notice.setName(name);
		notice.setContent(content);
		notice.setTimes(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		int result=noticeService.addNotice(notice);
		if(result==1){
			session.removeAttribute("admin");
			out.write("<script>"
					+"alert('恭喜你,添加成功');"
					+"window.location.href='"+request.getContextPath()+"/noticeServlet?action=findAll';"
					+"</script>");
		}else{
			if(result==-1){
				out.write("<script>"
						+"alert('公告名重复');"
						+"window.location.href='"+request.getContextPath()+"/admin/notice_add.jsp';"
						+"</script>");
			}else{
				out.write("<script>"
						+"alert('添加失败');"
						+"window.location.href='"+request.getContextPath()+"/admin/notice_add.jsp';"
						+"</script>");
			}
		}
	}
	
	protected void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Notice> notice=noticeService.getNoticeAll();
		request.setAttribute("notice", notice);
		request.getRequestDispatcher("/admin/notice.jsp").forward(request, response);
	}
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
