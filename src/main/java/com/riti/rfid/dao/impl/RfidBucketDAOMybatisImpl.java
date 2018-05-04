package com.riti.rfid.dao.impl;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.riti.rfid.dao.RfidBucketDAO;
import com.riti.rfid.entity.Bucket;

@Repository("rfidBucketDAO")
public class RfidBucketDAOMybatisImpl implements RfidBucketDAO {
	@Resource(name = "sst")
	private SqlSessionTemplate sst;

	public int addOrderListTask(Bucket bucket) {
		return sst.insert("com.riti.rfid.dao.RfidBucketDAO.addOrderListTask", bucket);
	}

	public int updateProOrderListTask(Bucket bucket) {
		return sst.insert("com.riti.rfid.dao.RfidBucketDAO.updateProOrderListTask", bucket);
	}

	public int updateCustOrderListTask(Bucket bucket) {
		return sst.insert("com.riti.rfid.dao.RfidBucketDAO.updateCustOrderListTask", bucket);
	}

	public int updateRecyOrderListTask(Bucket bucket) {
		return sst.insert("com.riti.rfid.dao.RfidBucketDAO.updateRecyOrderListTask", bucket);
	}

	public int updateOrderListTask(Bucket bucket) {
		return sst.update("com.riti.rfid.dao.RfidBucketDAO.updateOrderListTask", bucket);
	}

	public String getDepotTask(Bucket bucket) {
		return sst.selectOne("com.riti.rfid.dao.RfidBucketDAO.getDepotTask", bucket);
	}

	public int setOrderCustomerCountTask(int id) {
		return sst.insert("com.riti.rfid.dao.RfidBucketDAO.setOrderCustomerCountTask", id);
	}

	public int updateOrderListMa(Bucket bucket) {
		return sst.update("com.riti.rfid.dao.RfidBucketDAO.updateOrderListMa", bucket);
	}

	public String getProductTask(String bucket) {
		return sst.selectOne("com.riti.rfid.dao.RfidBucketDAO.getProductTask", bucket);
	}

	public String getBucketStatusTask(String bucket) {
		return sst.selectOne("com.riti.rfid.dao.RfidBucketDAO.getBucketStatusTask", bucket);
	}

	public String findBucketCodeByName(String bucket_code) {
		return sst.selectOne("com.riti.rfid.dao.RfidBucketDAO.findBucketCodeByName", bucket_code);
	}

	public int findCustormerByName(String bucket_code) {
		return sst.selectOne("com.riti.rfid.dao.RfidBucketDAO.findCustormerByName", bucket_code);
	}

	public String getBucketAddressTask(String bucket_code) {
		return sst.selectOne("com.riti.rfid.dao.RfidBucketDAO.getBucketAddressTask", bucket_code);
	}
}
