package com.riti.rfid.service;

import java.util.List;

import com.riti.rfid.entity.Product;
import com.riti.rfid.entity.RfidUser;

public interface RfidProductService {


	/**
	 * 查询用户信息到数据库
	 * 
	 * @param rfidUser
	 * @return
	 */
	List<Product> getRfidProductTask(String id);


}
