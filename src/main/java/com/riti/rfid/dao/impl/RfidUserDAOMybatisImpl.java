package com.riti.rfid.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.riti.rfid.dao.RfidUserDAO;
import com.riti.rfid.entity.RfidUser;

@Repository("rfidUserDAO")
public class RfidUserDAOMybatisImpl implements RfidUserDAO {
	@Resource(name = "sst")
	private SqlSessionTemplate sst;

	public List<RfidUser> getRfidUserList(String id) {
		return sst.selectList("com.riti.rfid.dao.RfidUserDAO.findRfidUserById",id);
	}
	public RfidUser getRfidUser(String name) {
		return sst.selectOne("com.riti.rfid.dao.RfidUserDAO.findRfidUserByName",name);
	}
	
	public List<RfidUser> getRfidUserListByComp(String comp) {
		return sst.selectList("com.riti.rfid.dao.RfidUserDAO.findRfidUserByComp",comp);
	}

}
