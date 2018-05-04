package com.riti.rfid.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.riti.rfid.dao.RfidManufactorDAO;
import com.riti.rfid.entity.Manufacture;

@Repository("rfidManufactorDAO")
public class RfidManufactorDAOMybatisImpl implements RfidManufactorDAO {
	@Resource(name = "sst")
	private SqlSessionTemplate sst;

	public Manufacture getRfidManufactor(String name) {
		return sst.selectOne(
				"com.riti.rfid.dao.RfidManufactorDAO.findRfidManufactorByName", name);
	}

	public List<Manufacture> getRfidManufactorList(String id) {

		return sst.selectList("com.riti.rfid.dao.RfidManufactorDAO.findRfidManufactorById",
				id);
	}
	public List<Manufacture> getRfidManufactorListByComp(String comp) {

		return sst.selectList("com.riti.rfid.dao.RfidManufactorDAO.findRfidManufactorByComp",
				comp);
	}

}
