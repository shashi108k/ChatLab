package org.lab.util;

import org.lab.dto.ResponseMessageDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// @ControllerAdvice tells your spring application that this class will do the exception handling for your application.
// @RestController will make it a controller and let this class render the response.
@RestControllerAdvice
public class GlobalExceptionHandler {

	/*
	 * Here, handleBaseException(BaseException e) :: will catch all the
	 * exceptions for classes BaseException, CustomException1 and
	 * CustomException2.
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	// Use @ExceptionHandler annotation to define the class of Exception it will
	// catch. (A Base class will catch all the Inherited and extended classes)
	@ExceptionHandler(value = BaseException.class)
	public String handleBaseException(BaseException e) {
		return e.getMessage();
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = ValidationException.class)
	public ResponseMessageDTO handleValidationException(ValidationException e) {
		if(StringUtils.isEmpty(e.getMessage().trim())){
			return new ResponseMessageDTO(HttpStatus.BAD_REQUEST, Constant.ErrorCode.BAD_REQUEST);  // text msg response : Please enter valid input parameters.
		} else {
			return new ResponseMessageDTO(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	/*
	 * handleException(Exception e) :: will handle all the exceptions that are
	 * childs of Exception class.
	 */
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ResponseMessageDTO> handleException(Exception e) {
		
		ResponseMessageDTO response = new ResponseMessageDTO(HttpStatus.BAD_REQUEST, e.getMessage());
		
		// response.setStackTrace(new StackTraceElement[0]);							// when it was extending Exception
		
		return new ResponseEntity<ResponseMessageDTO>(response, HttpStatus.BAD_REQUEST); // It will return ResponseMessageDTO but with Http status 400
		
		//return response;     // It will return ResponseMessageDTO but with Http status 200
		//return e.getMessage();     // ex (text msg response) : Could not commit JPA transaction; nested exception is javax.persistence.RollbackException: Error while committing the transaction
		//return Constant.ErrorCode.BAD_REQUEST;
	}

	/*
	 * Note :: If BaseException or its child exception is thrown then
	 * handleBaseException() will catch this exception and not the
	 * handleException() method
	 */

}
