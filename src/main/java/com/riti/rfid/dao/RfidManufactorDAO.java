package com.riti.rfid.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.riti.rfid.entity.Manufacture;
@Repository("rfidManufactorDAO")
public interface RfidManufactorDAO {
	/**
	 * 查询用户信息到数据库
	 * 
	 * @param rfidUser
	 * @return
	 */
	Manufacture getRfidManufactor(String name);
	/**
	 * 查询用户信息到数据库
	 * 
	 * @param rfidUser
	 * @return
	 */
	List<Manufacture> getRfidManufactorList(String id);
	/**
	 * 按照公司查询用户信息
	 * 
	 * @param rfidUser
	 * @return
	 */
	List<Manufacture> getRfidManufactorListByComp(String comp);
}
