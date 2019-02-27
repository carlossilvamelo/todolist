package com.api.todolist.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.http.HttpServletRequest;
import org.jboss.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RuntimeExceptionHandler {

	static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	static final Logger LOG = Logger.getLogger(RuntimeExceptionHandler.class);

	@ExceptionHandler({ ResourceNotFoundException.class, Exception.class })
	public ResponseEntity<StandardError> exceptionsHandler(Exception e, HttpServletRequest request) {

		LOG.error(String.format("Request '%s' threw the exception %s", request.getRequestURI(), e.toString()));
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError standardError = new StandardError(LocalDateTime.now().format(FORMATTER), status.value(),
				"Something went wrong!", e.getMessage(), request.getRequestURI());

		return ResponseEntity.status(status).body(standardError);
	}

}
