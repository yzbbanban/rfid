package com.riti.rfid.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.riti.rfid.dao.UserDAO;
import com.riti.rfid.entity.User;


@Repository("userDAO")
public class UserDAOMybatiesImpl implements UserDAO {
	@Resource(name = "sst")
	private SqlSessionTemplate sst;

	/**
	 * 根据名字查找用户
	 */
	public User findUserByName(String name) {
		return sst.selectOne("com.riti.cool.dao.UserDAO.findUserByName", name);
	}


	/**
	 * 根据用户id查询用户
	 */
	public User findUserById(String userId) {
		return sst.selectOne("com.riti.cool.dao.UserDAO.findUserById", userId);
	}


	public List<User> findUsers() {
		return sst.selectList("com.riti.cool.dao.UserDAO.findUser");
	}
	
	public int updateUserById(User user) {
		return sst.update("com.riti.cool.dao.UserDAO.updateUserById",user);
	}

}
