package com.riti.rfid.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.riti.rfid.entity.RfidUser;
@Repository("rfidUserDAO")
public interface RfidUserDAO {
	/**
	 * 查询用户信息到数据库
	 * 
	 * @param rfidUser
	 * @return
	 */
	RfidUser getRfidUser(String name);
	/**
	 * 查询用户信息到数据库
	 * 
	 * @param rfidUser
	 * @return
	 */
	List<RfidUser> getRfidUserList(String id);
	/**
	 * 根据公司获取客户列表
	 * @param comp
	 * @return
	 */
	List<RfidUser> getRfidUserListByComp(String comp);
}
