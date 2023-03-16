package com.star.wlh.user.controller;

import com.star.wlh.user.annotation.MonitorAnnotation;
import com.star.wlh.user.annotation.MonitorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/monitor")
public class MonitorController {
	private static final Logger logger = LoggerFactory.getLogger(MonitorController.class);

	@RequestMapping("test")
	@MonitorAnnotation(name = "test",value = MonitorType.COUNT)
	public void test(Integer pageSize,Integer pageNumber){
		logger.info("pageSize{},pageNumber:{}" , pageSize,pageNumber);
	}
}
