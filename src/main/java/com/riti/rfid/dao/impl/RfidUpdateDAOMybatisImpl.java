package com.riti.rfid.dao.impl;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.riti.rfid.dao.RfidUpdateDAO;
import com.riti.rfid.entity.UpdateMsg;

@Repository("rfidUpdateDAO")
public class RfidUpdateDAOMybatisImpl implements RfidUpdateDAO {
	@Resource(name = "sst")
	private SqlSessionTemplate sst;

	public int setUpdateMsg(UpdateMsg msg) {
		return sst.insert("com.riti.rfid.dao.RfidUpdateDAO.insertUpdateMsg",
				msg);
	}

	public String getUpdateMsg(String version) {
		return sst.selectOne("com.riti.rfid.dao.RfidUpdateDAO.findUpdateMsg",
				version);
	}

}
