package com.datang.hrbu.com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.datang.hrb.dao.ConnectionUtil;
import com.datang.hrb.vo.User;
import com.datang.hrbu.service.LoginService;
import com.datang.hrbu.service.lmpl.LoginServicelmpl;

public class GlobalController extends HttpServlet {
	private Map<String, String> userMap = new HashMap<String, String>();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("1");
		/*
		 * resp.setContentType("text/html;charset=UTF-8"); PrintWriter
		 * pw=resp.getWriter(); pw.write(""); pw.flush(); pw.close();
		 */
		resp.sendRedirect("ok.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		// System.out.println("2");
		String uri = req.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/") + 1, uri.indexOf("."));// 前包后开
		if (action.equals("register")) {
			/*
			 * userMap.put(username, password); resp.sendRedirect("register_success.jsp");
			 */
			Connection conn=ConnectionUtil.getConnection();
			PreparedStatement ps=null;
			try {
				ps=conn.prepareStatement("insert into user(username,password) values(?,?)");
				ps.setString(1, username);
				ps.setString(2, password);
				int i=ps.executeUpdate();
				if(i==1) {
					resp.sendRedirect("register_ok.jsp");
				}else {
					resp.sendRedirect("register_fail.jsp");
				}
			} catch (SQLException e) {
				resp.sendRedirect("register_fail.jsp");
				e.printStackTrace();
			}finally{
				if(ps!=null) {
					try {
						ps.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} else if (action.equals("login")) {// 登录
			User user=new User();
			user.setUsername(username);
			user.setPassword(password);
			LoginService loginService=new LoginServicelmpl();
			if(loginService.login(user)=="login_success.jsp") {
				HttpSession session=req.getSession();
				session.setAttribute("username", username);
				resp.sendRedirect("login_success.jsp");
			}else {
				resp.sendRedirect("login_fail.jsp");
			}			
			// 根据用户名获取密码
			//Connection conn=ConnectionUtil.getConnection();
			//PreparedStatement ps=null;
			//获取session对象
			//HttpSession session=req.getSession();
			
			/*try {
				ps=conn.prepareStatement("select * from user where username=?");
				ps.setString(1, username);
				ResultSet rs=ps.executeQuery();
				if(rs!=null&&rs.next()) {
					String dbpwd=rs.getString(2);
					if(password.equals(dbpwd)) {*/
						
						//在登陆成功后，使用session存储用户名，以供在页面上展示用户名
			/*
			 * session.setAttribute("username", username); PreparedStatement ps_second=null;
			 * ps_second=conn.prepareStatement("select * from user"); ResultSet rsList
			 * =ps_second.executeQuery(); List<User> userList= new ArrayList<User>();
			 * while(rsList.next()) { User user=new User();
			 * user.setUsername(rsList.getString(1)); user.setAge(rsList.getInt(3));
			 * userList.add(user); } session.setAttribute("userList", userList);
			 * resp.sendRedirect("NewLogin_ok.jsp");
			 * 
			 * }else { resp.sendRedirect("login_fail.jsp"); } }else {
			 * resp.sendRedirect("login_fail.jsp"); } } catch (SQLException e) {
			 * resp.sendRedirect("login_fail.jsp"); e.printStackTrace(); }finally {
			 * if(ps!=null) { try { ps.close(); } catch (SQLException e) {
			 * e.printStackTrace(); } } }
			 */
		}
			/*String existPassword = userMap.get(username);
			if (existPassword==null) {//密码为空，用户不存在
				resp.sendRedirect("login_fail.jsp");
			}
			else {
				if (existPassword.equals(password)) {//密码对一致
					resp.sendRedirect("login_success.jsp");
				} else {//密码错误
					resp.sendRedirect("login_fail.jsp");
				}
			}
		} else {
			// 错误的请求
		}*/
	

}}
