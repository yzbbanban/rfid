package com.riti.rfid.service;

import java.util.List;

import com.riti.rfid.entity.Manufacture;

public interface RfidManufactorService {

	/**
	 * 查询用户信息到数据库
	 * 
	 * @param rfidUser
	 * @return
	 */
	List<Manufacture> getRfidManufactorTask(String id);


}
