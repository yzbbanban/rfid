package test.rfid;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.riti.rfid.dao.RfidBucketDAO;
import com.riti.rfid.dao.RfidExceptionDAO;
import com.riti.rfid.dao.RfidManufactorDAO;
import com.riti.rfid.dao.RfidProductDAO;
import com.riti.rfid.dao.RfidUpdateDAO;
import com.riti.rfid.dao.RfidUserDAO;
import com.riti.rfid.dao.UserDAO;
import com.riti.rfid.entity.Bucket;
import com.riti.rfid.entity.Manufacture;
import com.riti.rfid.entity.Product;
import com.riti.rfid.entity.RfidException;
import com.riti.rfid.entity.RfidUser;
import com.riti.rfid.entity.UpdateMsg;
import com.riti.rfid.entity.User;

import test.TestBase;

public class TestRfidUserCase extends TestBase {
	private RfidManufactorDAO dao;
	private UserDAO userDAO;
	private RfidUserDAO rfidUserDAO;
	private RfidProductDAO rfidProductDAO;
	private RfidBucketDAO rfidBucketDAO;
	private RfidExceptionDAO rfidExceptionDAO;
	private RfidUpdateDAO rfidUpdateDao;
	@Before
	public void init() {
		dao = super.getContext().getBean("rfidManufactorDAO",
				RfidManufactorDAO.class);
		userDAO = super.getContext().getBean("userDAO", UserDAO.class);
		rfidUserDAO = super.getContext().getBean("rfidUserDAO",
				RfidUserDAO.class);
		rfidProductDAO = super.getContext().getBean("rfidProductDAO",
				RfidProductDAO.class);
		rfidBucketDAO = super.getContext().getBean("rfidBucketDAO",
				RfidBucketDAO.class);
		rfidExceptionDAO= super.getContext().getBean("rfidExceptionDAO",
				RfidExceptionDAO.class);
		rfidUpdateDao= super.getContext().getBean("rfidUpdateDAO",
				RfidUpdateDAO.class);
	}

	@Test
	public void test1() {
		System.out.println("sss1");
		String id = "1";
		List<Manufacture> rfid = dao.getRfidManufactorListByComp(id);
		System.out.println(rfid);
	}

	@Test
	public void test2() {
		System.out.println("sss2");
		User user = userDAO.findUserByName("admin@think.com");
		System.out.println(user);
	}

	@Test
	public void test3() {
		System.out.println("sss3");
		List<RfidUser> user = rfidUserDAO.getRfidUserListByComp("1");
		System.out.println(user);
	}

	@Test
	public void test4() {
		System.out.println("sss4");
		List<Product> products = rfidProductDAO.getRfidProductListByComp("1");
		System.out.println(products);
	}

	@Test
	public void test5() {
		System.out.println("sss52222");
		Bucket bucket = new Bucket();
		bucket.setAdmin_id(1);
		bucket.setBucket_address(0);
		bucket.setBucket_code("ban111111");
		bucket.setCreate_time(new Date());
		bucket.setStatus(1);
		bucket.setDepot_code("sss");
		bucket.setManufactor_id(1);
		int row = rfidBucketDAO.addOrderListTask(bucket);
		System.out.println(row);
	}

	@Test
	public void test6() {
		System.out.println("sss6");
		Bucket bucket = new Bucket();
		bucket.setAdmin_id(2);
		bucket.setBucket_address(0);
		bucket.setBucket_code("ban111111");
		bucket.setCreate_time(new Date());
		bucket.setStatus(1);
		bucket.setDepot_code("sss");
		bucket.setManufactor_id(1);
		//置空
		bucket.setCustomer_id(-1);
		bucket.setProduct_code("CC");
		int row = rfidBucketDAO.updateOrderListTask(bucket);
		System.out.println(row);
	}

	@Test
	public void test7() {
		System.out.println("sss7");

		int row = rfidBucketDAO.setOrderCustomerCountTask(3);
		System.out.println(row);
	}
	
	@Test
	public void test8() {
		System.out.println("sss8");
		Bucket bucket = new Bucket();
		bucket.setAdmin_id(1);
		bucket.setBucket_address(0);
		bucket.setBucket_code("a1111");
		bucket.setCreate_time(new Date());
		bucket.setStatus(1);
		bucket.setDepot_code("sss");
		bucket.setManufactor_id(1);
		int row = rfidBucketDAO.updateCustOrderListTask(bucket);
		System.out.println(row);
	}
	
	@Test
	public void test9(){
		RfidException rfidException=new RfidException();
		rfidException.setException("sss");
		rfidException.setException_createTime(new Date());
		rfidExceptionDAO.addOrderListTask(rfidException);
	}
	@Test
	public void test10(){
		UpdateMsg u=new UpdateMsg();
		u.setVersion("1.1.0");
		u.setCreateTime(new Date());
		int i=rfidUpdateDao.setUpdateMsg(u);
		
		String version=rfidUpdateDao.getUpdateMsg("");
		System.out.println(version);
	}
	
	@Test
	public void test11(){
		String b="2";
		String bucket=rfidBucketDAO.getProductTask(b);
		System.out.println("buc: "+bucket);
	}
	
	
	@Test
	public void test12(){
		String b="2";
		String bucket=rfidBucketDAO.getBucketStatusTask(b);
		System.out.println("buc: "+bucket);
	}

}
