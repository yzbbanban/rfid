package com.riti.rfid.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.riti.rfid.dao.RfidManufactorDAO;
import com.riti.rfid.entity.Manufacture;
import com.riti.rfid.service.RfidManufactorService;
import com.riti.rfid.service.exception.RfidUserException;

@Service
public class RfidManufactorServiceImpl implements RfidManufactorService {

	@Resource
	RfidManufactorDAO rfidManufactorDAO;

	public List<Manufacture> getRfidManufactorTask(String id) {
		List<Manufacture> rusList = rfidManufactorDAO.getRfidManufactorListByComp(id);
		if (rusList != null && rusList.size() > 0) {
			return rusList;
		} else {
			throw new RfidUserException("无数据请添加");
		}
	}

}
