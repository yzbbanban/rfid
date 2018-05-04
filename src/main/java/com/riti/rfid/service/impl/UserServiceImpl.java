package com.riti.rfid.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.riti.rfid.dao.UserDAO;
import com.riti.rfid.entity.User;
import com.riti.rfid.service.UserService;
import com.riti.rfid.service.exception.PasswordException;
import com.riti.rfid.service.exception.UserNotFoundException;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Resource
	private UserDAO userDao;
	// 加密用
	@Value("#{jdbc.salt}")
	private String salt;

	// 登录
	public User login(String name, String password)
			throws UserNotFoundException, PasswordException {
		if (password == null || password.trim().isEmpty()) {
			throw new PasswordException("密码空");
		}
		if (name == null || name.trim().isEmpty()) {
			throw new UserNotFoundException("用户名空");
		}
		User user = userDao.findUserByName(name.trim());
		System.out.println(user);
		if (user == null) {
			throw new UserNotFoundException("用户名错误");
		}
		//密码加密比对
		String pwd = DigestUtils.md5Hex(password.trim()+salt+user.getSalt());
		System.out.println("password: " + pwd);
		if (pwd.equals(user.getPassword())) {
			return user;
		}
		throw new PasswordException("密码错误");
	}

	public List<User> getUserInfo() {
		List<User> users=userDao.findUsers();
		if(users!=null && users.size()>0){
			return users;
		}
		return null;
	}

	public int updateUser(User user) {
		int row=userDao.updateUserById(user);
		return row;
	}

}
