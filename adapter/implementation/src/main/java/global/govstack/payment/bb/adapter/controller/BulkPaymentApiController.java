package global.govstack.payment.bb.adapter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import global.govstack.payment.bb.adapter.dto.BulkpaymentBody;
import global.govstack.payment.bb.adapter.dto.PaymentResponseDTO;
import global.govstack.payment.bb.adapter.service.CreditInstructionService;
import io.swagger.annotations.ApiParam;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-06-02T13:05:58.136949+03:00[Europe/Sofia]")
@RestController
public class BulkPaymentApiController implements BulkPaymentApi {

    private static final Logger log = LoggerFactory.getLogger(BulkPaymentApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private CreditInstructionService creditInstructionService;

    @org.springframework.beans.factory.annotation.Autowired
    public BulkPaymentApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public PaymentResponseDTO bulkPaymentPost(@ApiParam(value = "", required=true ) @Valid @RequestBody BulkpaymentBody body) {
        return creditInstructionService.bulkPayment(body);
    }

}
