package com.riti.rfid.dao.impl;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.riti.rfid.dao.RfidExceptionDAO;
import com.riti.rfid.entity.RfidException;

@Repository("rfidExceptionDAO")
public class RfidExceptionDAOMybatisImpl implements RfidExceptionDAO{
	@Resource(name = "sst")
	private SqlSessionTemplate sst;
	
	public int addOrderListTask(RfidException rfidException) {
		return sst.insert("com.riti.rfid.dao.RfidExceptionDAO.addException", rfidException);
	}

}
