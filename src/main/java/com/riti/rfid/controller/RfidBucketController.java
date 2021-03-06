package com.riti.rfid.controller;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.riti.rfid.consts.ServiceResult;
import com.riti.rfid.service.RfidBucketService;
import com.riti.rfid.service.exception.RfidOrderException;
import com.riti.rfid.util.ResultCode;
/**
 * 
 * @author brander
 *
 */
@Controller
@RequestMapping("/rfidOrder")
public class RfidBucketController {

	@Resource
	private RfidBucketService service;

	/**
	 * 添加标识号列表
	 * 
	 * @param orderJs
	 * @return
	 */
	@RequestMapping(value = "/addRfidOrderList", method = RequestMethod.POST)
	@ResponseBody
	public ResultCode<String> addOrderList(String orderJs) {
		// System.out.println(orderJs);
		String result = service.addBucketListTask(orderJs);
		System.out.println(result);
		return new ResultCode(result, "提交成功");
	}

	

	// 添加错误
	@ExceptionHandler(RfidOrderException.class)
	@ResponseBody
	public ResultCode<String> handleMessage(RfidOrderException e) {
//		e.printStackTrace();
		return new ResultCode(ServiceResult.GET_MESSAGE_FALSE.getIndex(), e);
	}

}
