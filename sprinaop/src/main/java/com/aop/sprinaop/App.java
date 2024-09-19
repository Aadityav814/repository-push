package com.aop.sprinaop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.aop.sprinaop.services.Payment;
import com.aop.sprinaop.services.PaymentImpl;




/**
 * Hello world!
 *
 */


/**
 *
 * 
 * Aspect oriented programming 
 * */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        
        ApplicationContext context = new ClassPathXmlApplicationContext("com/aop/sprinaop/config.xml");
        
        // Retrieve the 'payment' bean
        Payment paymentObj = context.getBean("payment",Payment.class);
        
        
        paymentObj.makepayment();
    }
    
    
    
    
    
    // Call methods on the paymentObj, assuming PaymentImpl implements Payment
   
}
