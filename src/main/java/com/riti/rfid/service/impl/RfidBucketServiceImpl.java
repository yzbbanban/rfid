package com.riti.rfid.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.riti.rfid.util.JsonParseUtils;
import com.riti.rfid.dao.RfidBucketDAO;
import com.riti.rfid.dao.RfidProductDAO;
import com.riti.rfid.entity.Bucket;
import com.riti.rfid.service.RfidBucketService;
import com.riti.rfid.service.exception.RfidOrderException;

@Service
public class RfidBucketServiceImpl implements RfidBucketService {
	@Resource
	RfidBucketDAO rfidBucketDAO;
	@Resource
	RfidProductDAO rfidProductDAO;

	@Resource
	private JsonParseUtils jsonParseUtils;

	public String addBucketListTask(String orderJs) {
		System.out.println(orderJs);
		List<Bucket> rfidOrders = jsonParseUtils.getOrderObjList(orderJs, ArrayList.class, Bucket.class);
		System.out.println(rfidOrders);
		int status = rfidOrders.get(0).getBucket_address();
		System.out.println("---> " + status);
		switch (status) {
		case 0:// 新桶
			int exceptionCount = 0;
			String nullMsg="";//空桶
			for (int i = 0; i < rfidOrders.size(); i++) {

				// System.out.println(rfidOrders.size());
				Bucket bucket = rfidOrders.get(i);
				bucket.setCreate_time(new Date());
				int row = 0;
				String s = rfidBucketDAO.getBucketStatusTask(bucket.getBucket_code());
				if (bucket.getManufactor_id() == 0) {// 回收
					String depot_code = rfidBucketDAO.getDepotTask(bucket);
					// System.out.println("depot_code---->" + depot_code);
					bucket.setDepot_code(depot_code);
					// bucket.setCustomer_id(-1);// bucket客户置空
					// bucket.setProduct_code("1");// bucket产品表置空
					String bucketAddress = rfidBucketDAO.getBucketAddressTask(bucket.getBucket_code());
					int bd = Integer.parseInt(bucketAddress);
					if(bd>=3){//在客户区可回收
						if ("1".equals(s)) {
							// 已有
							row = rfidBucketDAO.updateOrderListMa(bucket);// 更新吨桶bucket数据
						} else {
							// 报废或没有
						}
					}else{
						nullMsg="或有桶不该回收";
					}
				} else {

					if (s == null || "null".equals(s)) {
						row = rfidBucketDAO.addOrderListTask(bucket);// 插入吨桶bucket数据
					} else if ("1".equals(s)) {
						// 已有
					} else {
						// 报废
					}
				}

				System.out.println("rfidBucketDAO-----> " + bucket);
				int w = rfidBucketDAO.updateRecyOrderListTask(bucket);// 插入record表数据
				System.out.println(row + "," + w);
				if (row != 1 || w != 1) {// 有一条不成功则失败
					exceptionCount++;
				}

			}
			if (exceptionCount > 0) {
				throw new RfidOrderException("提交失败" + exceptionCount + "笔数据，数据重复"+nullMsg);
			}

			break;
		case 1:// 产品
			int exceptionCount2 = 0;
			boolean isProductBind = false;
			// 先遍历循环是否桶已绑定产品，绑定则全部不更新，返回数据为未成功的数据
			// 用于记录错误数据的 list
			List<Bucket> buckets = new ArrayList<Bucket>();
			for (int i = 0; i < rfidOrders.size(); i++) {

				// System.out.println(rfidOrders.size());
				Bucket bucket = rfidOrders.get(i);
				// 查询桶是否绑定正确
				String productCode = bucket.getProduct_code();
				String productCodeResult = rfidBucketDAO.findBucketCodeByName(bucket.getBucket_code());
				if (productCodeResult != null // 为 null 说明没绑定，可进入绑定
						&& !productCodeResult.equals(productCode)) {// 若有绑定的产品，且不与绑定的相同，抛出异常
					isProductBind = true;
					buckets.add(bucket);
				}

			}
			if (isProductBind) {
				throw new RfidOrderException(jsonParseUtils.getJsonParse(buckets));
			}
			String msg = "";
			for (int i = 0; i < rfidOrders.size(); i++) {

				// System.out.println(rfidOrders.size());
				Bucket bucket = rfidOrders.get(i);
				// 查询桶状态
				String s = rfidBucketDAO.getBucketStatusTask(bucket.getBucket_code());
				bucket.setCreate_time(new Date());
				System.out.println("------>" + bucket);
				int r = 0;
				int row = 0;
				int w = 0;
				String bucketAddress = rfidBucketDAO.getBucketAddressTask(bucket.getBucket_code());
				int bd = Integer.parseInt(bucketAddress);
				if (bd >= 1) {// 已出库则不能再此出库
					System.out.println("重复产品区上传");
					msg = "或重复产品区上传";
				} else {
					if ("1".equals(s)) {
						// 已有
						r = rfidBucketDAO.updateOrderListTask(bucket);// 更新吨桶数据位置状态
						row = rfidBucketDAO.updateProOrderListTask(bucket);
						w = rfidBucketDAO.updateRecyOrderListTask(bucket);// 插入record表数据
					} else {
						// 报废或没有

					}
				}

				if (row != 1 || r != 1 || w != 1) {// 有一条不成功则失败
					exceptionCount2++;
				}

			}
			if (exceptionCount2 > 0) {
				throw new RfidOrderException("提交失败" + exceptionCount2 + "笔数据，数据重复" + msg);
			}
			break;
		case 2:// 客户 （不用）
			int exceptionCount3 = 0;

			for (int i = 0; i < rfidOrders.size(); i++) {

				System.out.println(rfidOrders.size());
				Bucket bucket = rfidOrders.get(i);
				String s = rfidBucketDAO.getBucketStatusTask(bucket.getBucket_code());
				bucket.setCreate_time(new Date());
				int r = 0;
				int row = 0;
				int w = 0;

				if ("1".equals(s)) {
					// 已有
					r = rfidBucketDAO.updateOrderListTask(bucket);// 更新吨桶数据位置状态
					System.out.println("客户-----> " + bucket);

					row = rfidBucketDAO.updateCustOrderListTask(bucket);// 插入客户吨桶关联
					w = rfidBucketDAO.updateRecyOrderListTask(bucket);// 插入record表数据
				} else {
					// 报废或没有
				}

				if (row != 1 || r != 1 || w != 1) {// 有一条不成功则失败
					exceptionCount3++;
				}

			}
			int id = rfidOrders.get(0).getCustomer_id();
			// 更新产品的吨桶数量
			int row = rfidBucketDAO.setOrderCustomerCountTask(id);
			if (row != 1) {
				// throw new RfidOrderException("提交有误，请重新上传");
			}
			if (exceptionCount3 > 0) {
				throw new RfidOrderException("提交失败" + exceptionCount3 + "笔数据，数据重复");
			}
			break;
		case 3:// 在途
			int exceptionCount4 = 0;
			boolean isCustmerBind = false;
			// 先遍历循环是否桶已绑定客户，绑定则全部不更新，返回数据为未成功的数据
			// 用于记录错误数据的 list
			List<Bucket> bucketList = new ArrayList<Bucket>();
			for (int i = 0; i < rfidOrders.size(); i++) {

				System.out.println(rfidOrders.size());
				Bucket bucket = rfidOrders.get(i);
				int custormer = bucket.getCustomer_id();
				int custormerResult = rfidBucketDAO.findCustormerByName(bucket.getBucket_code());
				System.out.println("cust: " + custormer + " : " + custormerResult);
				if (custormerResult != 0 && custormer != custormerResult) {
					isCustmerBind = true;
					bucketList.add(bucket);
				}

			}

			if (isCustmerBind) {
				throw new RfidOrderException(jsonParseUtils.getJsonParse(bucketList));
			}
			String cusMsg = "";
			for (int i = 0; i < rfidOrders.size(); i++) {
				System.out.println(rfidOrders.size());
				Bucket bucket = rfidOrders.get(i);
				bucket.setCreate_time(new Date());
				String depot_code = rfidBucketDAO.getDepotTask(bucket);
				bucket.setDepot_code(depot_code);
				String s = rfidBucketDAO.getBucketStatusTask(bucket.getBucket_code());
				String productCode = rfidBucketDAO.getProductTask(bucket.getBucket_code());
				bucket.setProduct_code(productCode);
				System.out.println("产品-----> " + bucket);
				int r = 0;
				int o = 0;
				int w = 0;
				String bucketStatus = rfidBucketDAO.getBucketStatusTask(bucket.getBucket_code());
				String bucketAddress = rfidBucketDAO.getBucketAddressTask(bucket.getBucket_code());
				int bd = Integer.parseInt(bucketAddress);

				if (bd >= 2) {// 已出库则不能再此出库
					System.out.println("重复客户区上传");
					cusMsg = "或重复客户区上传";
				} else if (bd == 0) {// 空桶区
					System.out.println("或吨桶错误，还在空桶区");
					cusMsg = "或吨桶错误，还在空桶区";
				} else {
					if ("1".equals(s)) {
						// 已有
						o = rfidBucketDAO.updateCustOrderListTask(bucket);// 插入客户吨桶关联
						r = rfidBucketDAO.updateOrderListTask(bucket);// 更新吨桶数据位置状态
						w = rfidBucketDAO.updateRecyOrderListTask(bucket);
					} else {
						// 报废或没有

					}
				}

				if (o != 1 || w != 1 || r != 1) {// 有一条不成功则失败
					exceptionCount4++;
				}

			}
			if (exceptionCount4 > 0) {
				throw new RfidOrderException("提交失败" + exceptionCount4 + "笔数据，数据重复" + cusMsg);
			}
			break;
		}

		return "插入成功";
	}

}
