package com.riti.rfid.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.riti.rfid.dao.RfidProductDAO;
import com.riti.rfid.dao.RfidUserDAO;
import com.riti.rfid.entity.Product;
import com.riti.rfid.entity.RfidUser;

@Repository("rfidProductDAO")
public class RfidProductDAOMybatisImpl implements RfidProductDAO{
	@Resource(name = "sst")
	private SqlSessionTemplate sst;

	public RfidUser getRfidProduct(String name) {
		return sst.selectOne("com.riti.rfid.dao.RfidProductDAO.findRfidProductByName",name);
	}
	public List<Product> getRfidProductList(String id) {
		return sst.selectList("com.riti.rfid.dao.RfidProductDAO.findRfidProductById",id);
	}

	public List<Product> getRfidProductListByComp(String comp) {
		return sst.selectList("com.riti.rfid.dao.RfidProductDAO.findRfidProductByComp",comp);
	}
	public int getRfidProductByCode(String productCode) {
		return sst.selectOne("com.riti.rfid.dao.RfidProductDAO.findRfidProductByCode",productCode);
	}

}
