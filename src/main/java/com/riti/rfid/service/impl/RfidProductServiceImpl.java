package com.riti.rfid.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.riti.rfid.dao.RfidProductDAO;
import com.riti.rfid.entity.Product;
import com.riti.rfid.service.RfidProductService;
import com.riti.rfid.service.exception.RfidUserException;

@Service
public class RfidProductServiceImpl implements RfidProductService{

	@Resource
	RfidProductDAO rfidProductDAO;

	public List<Product> getRfidProductTask(String id) {
		List<Product> rusList = rfidProductDAO.getRfidProductListByComp(id);
		if (rusList != null && rusList.size() > 0) {
			return rusList;
		} else {
			throw new RfidUserException("无数据请添加");
		}
	}

}
