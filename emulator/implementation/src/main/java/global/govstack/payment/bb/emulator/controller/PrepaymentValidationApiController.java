package global.govstack.payment.bb.emulator.controller;

import global.govstack.payment.bb.emulator.dto.InlineResponse200;
import global.govstack.payment.bb.emulator.dto.PrepaymentvalidationBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import global.govstack.payment.bb.emulator.service.CreditInstructionService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;
import jakarta.servlet.http.HttpServletRequest;

@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-06-02T13:05:58.136949+03:00[Europe/Sofia]")
@RestController
public class PrepaymentValidationApiController implements PrepaymentValidationApi {

    private static final Logger log = LoggerFactory.getLogger(PrepaymentValidationApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private CreditInstructionService creditInstructionService;

    @org.springframework.beans.factory.annotation.Autowired
    public PrepaymentValidationApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<InlineResponse200> prepaymentValidationPost(@ApiParam(value = "", required=true ) @Valid @RequestBody PrepaymentvalidationBody body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            InlineResponse200 response = creditInstructionService.prepaymentValidation(body);
            return new ResponseEntity<InlineResponse200>(response, response.getResponseCode() == "00" ? HttpStatus.OK: HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<InlineResponse200>(HttpStatus.NOT_IMPLEMENTED);
    }

}
