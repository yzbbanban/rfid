package com.riti.rfid.dao;


import org.springframework.stereotype.Repository;

import com.riti.rfid.entity.UpdateMsg;

@Repository("rfidUpdateDAO")
public interface RfidUpdateDAO {
	/**
	 * 插入更新信息
	 * 
	 * @param rfidUser
	 * @return
	 */
	int setUpdateMsg(UpdateMsg msg);
	/**
	 * 获取更新信息
	 * 
	 * @param rfidUser
	 * @return
	 */
	String getUpdateMsg(String version);
}
