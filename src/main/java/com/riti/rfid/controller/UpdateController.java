package com.riti.rfid.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.riti.rfid.service.RfidUpdateService;
import com.riti.rfid.util.ResultCode;

@Controller
@RequestMapping("/rfidUpdate")
public class UpdateController {

	@Resource
	RfidUpdateService service;
	
	// 更新
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public ResultCode<String>  update(String version) {
		String msg=service.getRfidUpdateMsgTask(version);
		return new ResultCode(msg, msg);
	}

	// 写入更新
	@RequestMapping(value = "/addUpdate", method = RequestMethod.POST)
	@ResponseBody
	public ResultCode<String> addUpdate(String version) {
		System.out.println(version);
		String msg=service.setRfidUpdateMsgTask(version);
		return new ResultCode(msg, msg);
	}

}
