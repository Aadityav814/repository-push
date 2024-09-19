package com.aop.sprinaop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class MyAspect {
	@Before(value = "")
	public void printbefore() {
		
		System.out.println("payment start");
	}

	
	
}
