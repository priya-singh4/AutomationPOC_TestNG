package reusable;

import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Arrays;
@Aspect
public class ExceptionHandlingAspect implements UncaughtExceptionHandler {
	private static final Logger logger =LoggerFactory.getLogger(ExceptionHandlingAspect.class.getName());

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        if (e instanceof ClassCastException) {
            handleClassCastException((ClassCastException) e);
        } else {
            // Handle other types of exceptions or log them as needed
        	logger.info(Arrays.toString(e.getStackTrace()));
        }
    }

    private void handleClassCastException(ClassCastException ex) {
        logger.info("Caught ClassCastException globally: {} ", ex.getMessage());
        // Add your handling logic here if needed
    }

	


}

