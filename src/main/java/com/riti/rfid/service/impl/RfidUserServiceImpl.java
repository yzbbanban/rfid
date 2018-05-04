package com.riti.rfid.service.impl;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.riti.rfid.dao.RfidUserDAO;
import com.riti.rfid.entity.RfidUser;
import com.riti.rfid.service.RfidUserService;
import com.riti.rfid.service.exception.RfidUserException;

@Service
public class RfidUserServiceImpl implements RfidUserService {

	@Resource
	RfidUserDAO rfidUserDAO;

	public List<RfidUser> getRfidUserTask(String id) {
		List<RfidUser> rusList = rfidUserDAO.getRfidUserListByComp(id);
		if (rusList != null && rusList.size() > 0) {
			return rusList;
		} else {
			throw new RfidUserException("无数据请添加");
		}
	}

}
