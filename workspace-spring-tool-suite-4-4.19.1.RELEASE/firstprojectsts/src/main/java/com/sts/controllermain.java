package com.sts;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class controllermain {
	@RequestMapping("/add")
	@ResponseBody
	public int handletest() {
		System.out.println("Something");
		
		int a=10, b=20;
		
		int c=a+b;
		
		return c;
	}

}
 