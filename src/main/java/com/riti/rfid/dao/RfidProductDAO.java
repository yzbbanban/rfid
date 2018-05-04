package com.riti.rfid.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.riti.rfid.entity.Product;
@Repository("rfidProductDAO")
public interface RfidProductDAO {

	/**
	 * 查询用户信息到数据库
	 * 
	 * @param rfidUser
	 * @return
	 */
	List<Product> getRfidProductList(String id);
	/**
	 * 根据公司查询产品信息
	 * @param comp
	 * @return
	 */
	List<Product> getRfidProductListByComp(String comp);
	
	/**
	 * 根据产品 code查询数量
	 * @param productCode
	 * @return
	 */
	int getRfidProductByCode(String productCode);
}
