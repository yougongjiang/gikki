package com.hpe.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.jni.User;

import com.hpe.pojo.Admin;
import com.hpe.pojo.Users;
import com.hpe.service.impl.AdminService;
import com.hpe.service.impl.UsersServiceImpl;
import com.hpe.service1.AdminServiceImpl;
import com.hpe.service1.UsersService;

/**
 * Servlet implementation class UsersServlet
 */
@WebServlet("/usersServlet")
public class UsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsersService usersService=new UsersServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		//请求转码
		request.setCharacterEncoding("utf-8");
		//响应转码
		response.setContentType("text/html;charset=utf-8");
		//获取请求参数的action
		String action=request.getParameter("action");
		//判断action的值，决定调用什么方法
		if(action.equals("login")){
			login(request,response);
		}else if(action.equals("update")){
			update(request, response);
		}else if (action.equals("logout")) {
			// 退出
			logout(request, response);
		}else if(action.equals("update")){
			update(request, response);
		}
	}
	肖晓(1531310759) 2019/9/9 11:27:16
protected void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		String age = request.getParameter("age");
		String realname = request.getParameter("realname");
		String card = request.getParameter("card");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String code = request.getParameter("code");
		String sex = request.getParameter("sex");
		
		Users user0=usersService.findByName(name);
		String pwd0=user0.getPwd();//保留原密码
		Users user = new Users(name, pwd, realname, sex, age, card, address, phone, email, code);
		
		int result = usersService.update(user);
		if (result == 1) {
			if(pwd0.equals(pwd)){
				out.write("<script>" + "alert('恭喜您,修改成功!');" + "window.location.href='" + request.getContextPath()
				+ "/qiantai/center.jsp';" + "</script>");
			}else{
				out.write("<script>" + "alert('密码有变动，请重新登录');" + "window.location.href='" + request.getContextPath()
				+ "/qiantai/login.jsp';" + "</script>");
			}
			
		} else if (result == -1) {
			out.write("<script>" + "alert('对不起,修改未成功!');" + "window.location.href='"
					+ request.getContextPath() + "/qiantai/center.jsp';" + "</script>");
		} 

	}

//注册
	protected void reg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		
		//获取注册信息
		String name=request.getParameter("name");
		String pwd=request.getParameter("pwd");
		String qpwd=request.getParameter("qpwd");
		String realname=request.getParameter("realname");
		String sex=request.getParameter("sex");
		String age=request.getParameter("age");
		String card=request.getParameter("card");
		String address=request.getParameter("address");
		String phone=request.getParameter("phone");
		String email=request.getParameter("email");
		String code=request.getParameter("code");
		
		Users user=new Users(name,qpwd,realname,sex,age,card,address,phone,email,code);
		int result=usersService.reg(user);
		if(result==1){
			out.write("<script>"
					+"alert('注册成功!');"
					+"window.location.href='"+request.getContextPath()+"/qiantai/login.jsp';"
					+"</script>");
			}else if(result==-1){
				out.write("<script>"
					    +"alert('用户名重复!');"
						+"window.location.href='"+request.getContextPath()+"/qiantai/reg.jsp';"
						+"</script>");
			}else{
				out.write("<script>"
						+"alert('注册失败!');"
						+"window.location.href='"+request.getContextPath()+"/qiantai/reg.jsp';"
						+"</script>");
				}
	}

肖晓(1531310759) 2019/9/9 11:27:34
else if(action.equals("reg")){
			reg(request, response);
		}


	protected void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		// 清除session，跳转登录页
		session.removeAttribute("user");
		out.write("<script>" + "alert('恭喜你,退出成功');" + "window.location.href='" + request.getContextPath()
				+ "/shoppingCartServlet?action=delAll';" + "</script>");

	}
	protected void login(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		String name=request.getParameter("name");
		String pwd=request.getParameter("pwd");
		Users users=usersService.login(name,pwd);
		if(users!=null){
			session.setAttribute("users",users);
			out.write("<script>"
					+"alert('恭喜你登陆成功');"
					+"window.location.href='"+request.getContextPath()+"/admin/main.jsp';"
					+"</script>");
		}else{
			out.write("<script>"
					+"alert('登陆失败');"
					+"window.location.href='"+request.getContextPath()+"/admin/index.jsp';"
					+"</script>");
		}
	}
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		Users user = new Users();
		String names=request.getParameter("names");
		String pass=request.getParameter("pwd");
		String age=request.getParameter("age");
		String realname=request.getParameter("realname");
		String card=request.getParameter("card");
		String address=request.getParameter("address");
		String phone=request.getParameter("phone");
		String email=request.getParameter("email");
		String code=request.getParameter("code");
		String sex=request.getParameter("sex");
		user.setName(names);
		user.setPwd(pass);
		user.setAge(age);
		user.setRealname(realname);
		user.setCard(card);
		user.setAddress(address);
		user.setPhone(phone);
		user.setEmail(email);
		user.setCode(code);
		user.setSex(sex);
		int flag = usersService.update(user);
		PrintWriter out = response.getWriter();
		if (flag == 1) {
			session.removeAttribute("user");
			out.write("<script>alert('恭喜您,修改成功!');window.location.href='" + request.getContextPath() + "/qiantai/index.jsp';</script>");
		} else {
			out.write("<script>alert('对不起,修改未成功!');window.location.href='" + request.getContextPath() + "/qiantai/center.jsp';</script>");
		}
	}
}
