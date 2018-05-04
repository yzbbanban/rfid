package com.riti.rfid.service;


public interface RfidUpdateService {


	/**
	 * 查询更新的数据
	 * 
	 * @param rfidUser
	 * @return
	 */
	String getRfidUpdateMsgTask(String version);

	/**
	 * 插入更新的数据
	 * 
	 * @param rfidUser
	 * @return
	 */
	String setRfidUpdateMsgTask(String version);

}
