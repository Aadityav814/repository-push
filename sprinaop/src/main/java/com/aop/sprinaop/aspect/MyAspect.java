package com.aop.sprinaop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class MyAspect {
	@Before(value = "execution(* com.aop.sprinaop.services.PaymentImpl.makepayment())")
	public void printbefore() {
		
		System.out.println("payment start");
	}

	
	
}
