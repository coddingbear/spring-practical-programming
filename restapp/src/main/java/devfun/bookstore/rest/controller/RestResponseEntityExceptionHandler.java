package devfun.bookstore.rest.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import devfun.bookstore.rest.domain.RestError;
import devfun.bookstore.rest.exception.ResourceNotFoundException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {

	@ExceptionHandler(value = { ResourceNotFoundException.class })
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ResponseBody
	public RestError handleResourceNotFound(ResourceNotFoundException ex) {
		RestError error = new RestError(404, "해당 자원을 찾을 수 없습니다.");
		return error;
	}
	/* ResponseEntity 클래스를 이용하여 구현하기
	@ExceptionHandler(value = {ResourceNotFoundException.class})
	protected ResponseEntity<RestError> handleResourceNotFound(
			ResourceNotFoundException ex, WebRequest request) {
		RestError error = new RestError(404, "해당 자원을 찾을 수 없습니다.");
		return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}
	*/
}
