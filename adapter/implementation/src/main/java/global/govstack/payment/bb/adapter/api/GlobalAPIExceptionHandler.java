package global.govstack.payment.bb.adapter.api;

import global.govstack.payment.bb.adapter.dto.InlineResponse200;
import global.govstack.payment.bb.adapter.service.exception.ServiceException;
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
