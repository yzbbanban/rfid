package com.riti.rfid.dao;


import org.springframework.stereotype.Repository;

import com.riti.rfid.entity.RfidException;
@Repository("rfidExceptionDAO")
public interface RfidExceptionDAO {
	/**
	 * 添加异常信息
	 * 
	 * @param orderJs
	 * @return
	 */
	int addOrderListTask(RfidException rfidException);
	
}
