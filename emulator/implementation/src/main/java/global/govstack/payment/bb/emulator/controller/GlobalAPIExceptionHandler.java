package global.govstack.payment.bb.emulator.controller;

import global.govstack.payment.bb.emulator.service.exception.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalAPIExceptionHandler {

  @ExceptionHandler({ServiceException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  ResponseEntity<Object> handle(ServiceException e) {
    return ResponseEntity.badRequest().body(e.getResponse());
  }
}
