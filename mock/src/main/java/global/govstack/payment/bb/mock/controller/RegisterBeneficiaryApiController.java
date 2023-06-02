package global.govstack.payment.bb.mock.controller;

import global.govstack.payment.bb.mock.dto.InlineResponse200;
import global.govstack.payment.bb.mock.dto.RegisterbeneficiaryBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import global.govstack.payment.bb.mock.service.BeneficiaryService;
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
public class RegisterBeneficiaryApiController implements RegisterBeneficiaryApi {

    private static final Logger log = LoggerFactory.getLogger(RegisterBeneficiaryApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private BeneficiaryService registerBeneficiaryService;

    @org.springframework.beans.factory.annotation.Autowired
    public RegisterBeneficiaryApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<InlineResponse200> registerBeneficiaryPost(@ApiParam(value = "", required=true ) @Valid @RequestBody RegisterbeneficiaryBody body) {
        /*String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<InlineResponse200>(registerBeneficiaryService.register(body), HttpStatus.OK);
                //return new ResponseEntity<InlineResponse200>(objectMapper.readValue("{\n  \"ResponseCode\" : \"ResponseCode\",\n  \"RequestID\" : \"RequestID\",\n  \"ResponseDescription\" : \"ResponseDescription\"\n}", InlineResponse200.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<InlineResponse200>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<InlineResponse200>(HttpStatus.NOT_IMPLEMENTED);*/
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            InlineResponse200 response = registerBeneficiaryService.register(body);
            return new ResponseEntity<InlineResponse200>(response, response.getResponseCode() == "00" ? HttpStatus.OK: HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<InlineResponse200>(HttpStatus.NOT_IMPLEMENTED);
    }

}
