package com.riti.rfid.service;

import java.util.List;

import com.riti.rfid.entity.RfidUser;

public interface RfidUserService {


	/**
	 * 查询用户信息到数据库
	 * 
	 * @param rfidUser
	 * @return
	 */
	List<RfidUser> getRfidUserTask(String id);


}
