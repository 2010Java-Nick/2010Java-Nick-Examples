package HotelReservationSpring.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
 * Aspect - Modularization of a Cross Cutting Concern
 * Provides advice for program
 */

@Component
@Aspect  
public class GuestLogging {

	Logger log = LoggerFactory.getLogger(GuestLogging.class);
	
	//Hook - empty method to hold an annotation
	@Pointcut("within(HotelReservationSpring..*Guest*)")
	public void pointcutForGuestMethods() {
	}
	
	//Types of Advice - Before, After, Around, AfterThrowing, AfterReturning
	@AfterReturning(value = "pointcutForGuestMethods()", returning = "returnValue")
	public void logAfterReturningFromGuestMethod(JoinPoint jp, Object returnValue) {
		System.out.println("Returned from Guest Method: " + jp.getSignature());
		if (returnValue != null) {
			System.out.println("Returning value: " + returnValue.toString());
		}
		//log.trace("Returned from Guest Method");
	}
	
	@AfterThrowing(value = "pointcutForGuestMethods()", throwing = "exception")
	public void logAfterThrowingFromGuestMethod(JoinPoint jp, Throwable exception) {
		System.out.println("Exception " + exception + "thrown from " + jp.getSignature());
		exception.printStackTrace();
		log.warn("Exception " + exception + "thrown from " + jp.getSignature(), exception);
		
	}
	
}
