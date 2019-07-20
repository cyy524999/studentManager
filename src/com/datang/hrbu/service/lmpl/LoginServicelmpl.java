package com.datang.hrbu.service.lmpl;

import com.datang.hrb.dao.UserDao;
import com.datang.hrb.vo.User;
import com.datang.hrbu.service.LoginService;

public class LoginServicelmpl   implements LoginService{

	@Override
	public String login(User user) {
		UserDao userDao =new UserDao();
		String password=userDao.getUsersByUsername(user.getUsername());
		if(password.equals(user.getPassword())) {
			return "login_success.jsp";
		}else {
			return "login_fail.jsp";
		}
		
		

	}

}
